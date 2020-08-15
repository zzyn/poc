/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable react/static-property-placement */
/* eslint-disable lines-between-class-members */
/* eslint-disable prefer-destructuring */
/* eslint-disable prefer-object-spread */
/* eslint-disable no-param-reassign */
/* eslint-disable no-restricted-syntax */
/* eslint-disable consistent-return */
/* eslint-disable no-else-return */

import { DataSource, DataSourceConfig } from "apollo-datasource";
import { HTTPCache } from "apollo-datasource-rest";
import { Span, FORMAT_HTTP_HEADERS } from "opentracing";
import * as opentracing from "opentracing";

import {
  BodyInit,
  fetch,
  Headers,
  Request,
  RequestInit,
  Response,
  URL,
  URLSearchParams,
  URLSearchParamsInit,
} from "apollo-server-env";
import {
  ApolloError,
  AuthenticationError,
  ForbiddenError,
} from "apollo-server-errors";
import { ValueOrPromise } from "apollo-server-types";

declare module "apollo-server-env/dist/fetch" {
  interface RequestInit {
    cacheOptions?:
      | CacheOptions
      | ((response: Response, request: Request) => CacheOptions | undefined);
  }
}

export type RequestOptions = RequestInit & {
  path: string;
  params: URLSearchParams;
  headers: Headers;
  body?: Body;
};

export interface KTCacheOptions extends CacheOptions {
  cacheable: boolean;
  cacheKey?: string;
  deleteCache?: boolean;
}

export interface CacheOptions {
  ttl?: number;
}

export type Body = BodyInit | object;
export { Request };

export abstract class KTRestDataSource<TContext = any> extends DataSource {
  httpCache!: HTTPCache;
  context!: TContext;
  memoizedResults = new Map<string, Promise<any>>();

  constructor(private httpFetch?: typeof fetch) {
    super();
  }

  initialize(config: DataSourceConfig<TContext>): void {
    this.context = config.context;
    this.httpCache = new HTTPCache(config.cache, this.httpFetch);
  }

  baseURL?: string;

  // By default, we use the full request URL as the cache key.
  // You can override this to remove query parameters or compute a cache key in any way that makes sense.
  // For example, you could use this to take Vary header fields into account.
  // Although we do validate header fields and don't serve responses from cache when they don't match,
  // new reponses overwrite old ones with different vary header fields.
  protected cacheKeyFor(request: Request): string {
    return request.url;
  }

  protected willSendRequest?(request: RequestOptions): ValueOrPromise<void>;

  protected resolveURL(request: RequestOptions): ValueOrPromise<URL> {
    let path = request.path;
    if (path.startsWith("/")) {
      path = path.slice(1);
    }
    const baseURL = this.baseURL;
    if (baseURL) {
      const normalizedBaseURL = baseURL.endsWith("/")
        ? baseURL
        : baseURL.concat("/");
      return new URL(path, normalizedBaseURL);
    } else {
      return new URL(path);
    }
  }

  protected cacheOptionsFor?(
    response: Response,
    request: Request,
  ): CacheOptions | undefined;

  protected async didReceiveResponse<TResult = any>(
    response: Response,
    _request: Request,
  ): Promise<TResult> {
    if (response.ok) {
      return (this.parseBody(response) as any) as Promise<TResult>;
    } else {
      throw await this.errorFromResponse(response);
    }
  }

  protected didEncounterError(error: Error, _request: Request) {
    throw error;
  }

  protected parseBody(response: Response): Promise<object | string> {
    const contentType = response.headers.get("Content-Type");
    const contentLength = response.headers.get("Content-Length");
    if (
      // As one might expect, a "204 No Content" is empty! This means there
      // isn't enough to `JSON.parse`, and trying will result in an error.
      response.status !== 204 &&
      contentLength !== "0" &&
      contentType &&
      (contentType.startsWith("application/json") ||
        contentType.startsWith("application/hal+json"))
    ) {
      return response.json();
    } else {
      return response.text();
    }
  }

  protected async errorFromResponse(response: Response) {
    const message = `${response.status}: ${response.statusText}`;

    let error: ApolloError;
    if (response.status === 401) {
      error = new AuthenticationError(message);
    } else if (response.status === 403) {
      error = new ForbiddenError(message);
    } else {
      error = new ApolloError(message);
    }

    const body = await this.parseBody(response);

    Object.assign(error.extensions, {
      response: {
        url: response.url,
        status: response.status,
        statusText: response.statusText,
        body,
      },
    });

    return error;
  }

  protected async get<TResult = any>(
    path: string,
    params?: URLSearchParamsInit,
    init?: RequestInit,
    ktCacheOptions?: KTCacheOptions,
  ): Promise<TResult> {
    return this.trackFetch<TResult>(
      Object.assign({ method: "GET", path, params }, init),
      ktCacheOptions || { cacheable: false },
    );
  }

  protected async post<TResult = any>(
    path: string,
    body?: Body,
    init?: RequestInit,
    ktCacheOptions?: KTCacheOptions,
  ): Promise<TResult> {
    return this.trackFetch<TResult>(
      Object.assign({ method: "POST", path, body }, init),
      ktCacheOptions || { cacheable: false },
    );
  }

  protected async patch<TResult = any>(
    path: string,
    body?: Body,
    init?: RequestInit,
    ktCacheOptions?: KTCacheOptions,
  ): Promise<TResult> {
    return this.trackFetch<TResult>(
      Object.assign({ method: "PATCH", path, body }, init),
      ktCacheOptions || { cacheable: false },
    );
  }

  protected async put<TResult = any>(
    path: string,
    body?: Body,
    init?: RequestInit,
    ktCacheOptions?: KTCacheOptions,
  ): Promise<TResult> {
    return this.trackFetch<TResult>(
      Object.assign({ method: "PUT", path, body }, init),
      ktCacheOptions || { cacheable: false },
    );
  }

  protected async delete<TResult = any>(
    path: string,
    params?: URLSearchParamsInit,
    init?: RequestInit,
    ktCacheOptions?: KTCacheOptions,
  ): Promise<TResult> {
    return this.trackFetch<TResult>(
      Object.assign({ method: "DELETE", path, params }, init),
      ktCacheOptions || { cacheable: false },
    );
  }

  private async trackFetch<TResult>(
    init: RequestInit & {
      path: string; 
      params?: URLSearchParamsInit;
    },
    ktCacheOptions: KTCacheOptions,
  ): Promise<TResult> {
    let traceSpan: Span;
    const contentInstance = this.context as any;
    if (contentInstance !== undefined) {
      if (contentInstance.rootSpan) {
        traceSpan = opentracing.globalTracer().startSpan(init.path, {
          childOf: contentInstance.rootSpan,
        });

        if (!init.headers) {
          init.headers = {};
        }

        opentracing
          .globalTracer()
          .inject(traceSpan, FORMAT_HTTP_HEADERS, init.headers);

        return this.fetch<TResult>(init, ktCacheOptions)
          .then((response) => {
            traceSpan.finish();
            return response;
          })
          .catch((error) => {
            traceSpan.log({
              error: true,
              errorMessage: error,
            });
            traceSpan.finish();

            throw error;
          });
      }
    }

    return this.fetch<TResult>(init, ktCacheOptions);
  }

  // Currently only changes caching for post api
  private async fetch<TResult>(
    init: RequestInit & {
      path: string;
      params?: URLSearchParamsInit;
    },
    ktCacheOptions: KTCacheOptions,
  ): Promise<TResult> {
    if (!(init.params instanceof URLSearchParams)) {
      init.params = new URLSearchParams(init.params);
    }

    if (!(init.headers && init.headers instanceof Headers)) {
      init.headers = new Headers(init.headers || Object.create(null));
    }

    const options = init as RequestOptions;

    if (ktCacheOptions.ttl) {
      options.cacheOptions = { ttl: ktCacheOptions.ttl };
    } else {
      options.cacheOptions = undefined;
    }

    if (this.willSendRequest) {
      await this.willSendRequest(options);
    }

    const url = await this.resolveURL(options);

    // Append params to existing params in the path
    for (const [name, value] of options.params) {
      url.searchParams.append(name, value);
    }

    // We accept arbitrary objects and arrays as body and serialize them as JSON
    if (
      options.body !== undefined &&
      options.body !== null &&
      (options.body.constructor === Object ||
        Array.isArray(options.body) ||
        ((options.body as any).toJSON &&
          typeof (options.body as any).toJSON === "function"))
    ) {
      options.body = JSON.stringify(options.body);
      // If Content-Type header has not been previously set, set to application/json
      if (!options.headers.get("Content-Type")) {
        options.headers.set("Content-Type", "application/json");
      }
    }

    const request = new Request(String(url), options);

    let cacheKey = this.cacheKeyFor(request);
    if (ktCacheOptions.cacheKey) {
      cacheKey = ktCacheOptions.cacheKey;
    }

    const performRequest = async () => {
      return this.trace(`${options.method} ${url}`, async () => {
        const cacheOptions = options.cacheOptions
          ? options.cacheOptions
          : this.cacheOptionsFor && this.cacheOptionsFor.bind(this);
        try {
          const response = await this.httpCache.fetch(request, {
            cacheKey,
            cacheOptions,
          });
          return await this.didReceiveResponse(response, request);
        } catch (error) {
          this.didEncounterError(error, request);
        }
      });
    };

    if (ktCacheOptions.cacheable) {
      let promise = this.memoizedResults.get(cacheKey);
      if (promise) return promise;

      promise = performRequest();
      this.memoizedResults.set(cacheKey, promise);
      return promise;
    }

    if (ktCacheOptions.deleteCache) {
      this.memoizedResults.delete(cacheKey);
    }

    return performRequest();
  }

  private async trace<TResult>(
    label: string,
    fn: () => Promise<TResult>,
  ): Promise<TResult> {
    if (process && process.env && process.env.NODE_ENV === "development") {
      // We're not using console.time because that isn't supported on Cloudflare
      const startTime = Date.now();
      try {
        return await fn();
      } finally {
        const duration = Date.now() - startTime;
        console.log(`${label} (${duration}ms)`);
      }
    } else {
      return fn();
    }
  }
}
