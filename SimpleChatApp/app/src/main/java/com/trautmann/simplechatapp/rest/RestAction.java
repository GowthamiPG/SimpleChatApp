package com.trautmann.simplechatapp.rest;

import com.trautmann.simplechatapp.rest.response.GenericResponse;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Brandon Trautmann
 */

public class RestAction<T extends GenericResponse> {

    private Single<Response<T>> observable;

    public RestAction(Single<Response<T>> observable) {
        this.observable = observable;
    }

    Single<T> perform() {
        return RestUtil.react(observable);
    }

}
