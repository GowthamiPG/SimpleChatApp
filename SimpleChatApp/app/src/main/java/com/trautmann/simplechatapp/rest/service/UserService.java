package com.trautmann.simplechatapp.rest.service;

import com.trautmann.simplechatapp.rest.response.CreateUser;
import com.trautmann.simplechatapp.rest.response.GetCurrentUser;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Brandon Trautmann
 */

public interface UserService {

    @GET("users/current")
    Single<Response<GetCurrentUser>> getCurrentUser();

    @POST("users")
    @FormUrlEncoded
    Single<Response<CreateUser>> createUser(@Field("name") String name,
                                            @Field("email") String email,
                                            @Field("password") String password,
                                            @Field("password_confirmation") String passwordConfirmation);


}
