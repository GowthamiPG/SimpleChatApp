package com.trautmann.simplechatapp.rest.service;

import com.trautmann.simplechatapp.rest.response.GenericResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Brandon Trautmann
 */

public interface AuthService {

    @POST("auth/login")
    @FormUrlEncoded
    Single<Response<GenericResponse>> login(@Field("email") String email,
                                            @Field("password") String password);

}
