package com.trautmann.simplechatapp.rest;

import com.trautmann.simplechatapp.rest.response.GenericResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * © Copyright 2016 Oakwood Software Consulting, Inc.  All Rights Reserved.
 */

public class RestUtil {


    public static <T extends GenericResponse> Function<Response<T>, T> receive() {
        return RestUtil::isCallSuccessful;
    }

    private static <T extends GenericResponse> T isCallSuccessful(Response<T> rawResponse) {
        if (rawResponse.isSuccessful()) {
            if (rawResponse.body().isErrorFree()) {
                return rawResponse.body();
            } else {
                throw Exceptions.propagate(new Exception(rawResponse.body().getMessage()));
            }
        } else {
            throw Exceptions.propagate(new Exception(rawResponse.message()));
        }
    }

    public static <T extends GenericResponse> Single<T> react(Single<Response<T>> single) {
        return single.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(receive());
    }
}
