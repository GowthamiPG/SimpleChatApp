package com.trautmann.simplechatapp.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Brandon Trautmann
 */

public class ScaHttpClient {

    private static OkHttpClient httpClient;

    static OkHttpClient getInstance() {
        if (httpClient == null) {
            httpClient = getHttpClient();
        }
        return httpClient;
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
        return httpClient.build();
    }
}
