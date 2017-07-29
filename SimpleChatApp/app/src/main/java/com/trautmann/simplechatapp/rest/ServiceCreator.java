package com.trautmann.simplechatapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brandon Trautmann
 */

public class ServiceCreator {

    private static Gson gson = new GsonBuilder().create();
    private static Retrofit.Builder builder = getBuilder();
    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass) {
        if (retrofit == null) {
            retrofit = builder.client(ScaHttpClient.getInstance()).build();
        }
        return retrofit.create(serviceClass);
    }

    public static Retrofit.Builder getBuilder() {
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    private static String getBaseUrl() {
//        return "https://private-93240c-oracodechallenge.apiary-mock.com/";
        return "https://private-anon-47ba9efa30-oracodechallenge.apiary-mock.com/";
    }
}
