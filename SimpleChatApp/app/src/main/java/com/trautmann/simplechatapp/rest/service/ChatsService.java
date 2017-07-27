package com.trautmann.simplechatapp.rest.service;

import com.trautmann.simplechatapp.rest.response.GetChatsList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Brandon Trautmann
 */

public interface ChatsService {

    @GET("chats?page=1&limit=50")
    Single<Response<GetChatsList>> getChatsList();

}
