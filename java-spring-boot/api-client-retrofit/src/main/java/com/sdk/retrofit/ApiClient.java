package com.sdk.retrofit;

import com.sdk.retrofit.auth.ApiKeyAuth;
import com.sdk.retrofit.auth.HttpBasicAuth;
import com.sdk.retrofit.auth.OAuth;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;

import io.opentracing.Tracer;
import io.opentracing.contrib.okhttp3.TracingCallFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Define the ApiClient
 */
public class ApiClient {

    private Map<String, Interceptor> apiAuthorizations;
    private String baseUrl;
    private OkHttpClient.Builder okBuilder;
    private Retrofit.Builder adapterBuilder;
    private ObjectMapper objectMapper;

    /**
     * @param baseUrl
     */
    public ApiClient(String baseUrl) {
        this.apiAuthorizations = new LinkedHashMap<>();
        this.baseUrl = baseUrl;
        createDefaultAdapter();
    }

    /**
     * create default adapter
     */
    private void createDefaultAdapter() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        okBuilder = new OkHttpClient.Builder();

        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        adapterBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper));
    }

    /**
     * Create service instance
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass) {
        return adapterBuilder
                .client(okBuilder.build())
                .build()
                .create(serviceClass);
    }

    /**
     * Create service instance
     *
     * @param serviceClass
     * @param <S>
     * @param tracer
     * @return
     */
    public <S> S createService(Class<S> serviceClass, Tracer tracer) {
        return adapterBuilder
                .callFactory(new TracingCallFactory(okBuilder.build(), tracer))
                .build()
                .create(serviceClass);
    }
    /**
     * @param apiKey
     * @return
     */
    public ApiClient setApiKey(String apiKey) {
        for (Interceptor apiAuthorization : apiAuthorizations.values()) {
            if (apiAuthorization instanceof ApiKeyAuth) {
                ApiKeyAuth keyAuth = (ApiKeyAuth) apiAuthorization;
                keyAuth.setApiKey(apiKey);
                return this;
            }
        }
        return this;
    }

    /**
     * @param userName
     * @param password
     * @return
     */
    public ApiClient setCredentials(String userName, String password) {
        for (Interceptor apiAuthorization : apiAuthorizations.values()) {
            if (apiAuthorization instanceof HttpBasicAuth) {
                HttpBasicAuth basicAuth = (HttpBasicAuth) apiAuthorization;
                basicAuth.setCredentials(userName, password);
                return this;
            }
            if (apiAuthorization instanceof OAuth) {
                OAuth oAuth = (OAuth) apiAuthorization;
                oAuth.getTokenRequestBuilder().setUsername(userName).setPassword(password);
                return this;
            }
        }
        return this;
    }

    /**
     * @return
     */
    public OAuthClientRequest.TokenRequestBuilder getTokenEndPoint() {
        for (Interceptor apiAuthorization : apiAuthorizations.values()) {
            if (apiAuthorization instanceof OAuth) {
                OAuth oAuth = (OAuth) apiAuthorization;
                return oAuth.getTokenRequestBuilder();
            }
        }
        return null;
    }

    /**
     * @param okBuilder
     */
    public void addAuthsToOkBuilder(OkHttpClient.Builder okBuilder) {
        for (Interceptor apiAuthorization : apiAuthorizations.values()) {
            okBuilder.addInterceptor(apiAuthorization);
        }
    }

}
