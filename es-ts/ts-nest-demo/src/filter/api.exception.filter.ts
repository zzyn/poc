import { ArgumentsHost, Catch, ExceptionFilter } from "@nestjs/common";
import { Request, Response } from "express";
import { ApiException } from "./error/api.exception";

@Catch(ApiException)
export class ApiExceptionFilter implements ExceptionFilter {
  catch(exception: ApiException, host: ArgumentsHost) {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    const request = ctx.getRequest<Request>();
    const status = exception.getStatus();

    response.status(status).json({
      method: request.method,
      path: request.url,
      body: request.body,
      timestamp: new Date().toISOString(),
      status,
      message: exception.message,
      stack: exception.stack,
    });
  }
}
