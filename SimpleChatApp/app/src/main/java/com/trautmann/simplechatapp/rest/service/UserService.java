package com.trautmann.simplechatapp.rest.service;

import com.trautmann.simplechatapp.rest.response.GetCurrentUser;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Brandon Trautmann
 */

public interface UserService {

    @GET("users/current")
    Single<Response<GetCurrentUser>> getCurrentUser();

}
