package com.trautmann.simplechatapp.rest.service;

import com.trautmann.simplechatapp.rest.response.CreateChatMessage;
import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;
import com.trautmann.simplechatapp.rest.response.GetChatsList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Brandon Trautmann
 */

public interface ChatsService {

    @GET("chats?page=1&limit=50")
    Single<Response<GetChatsList>> getChatsList();

    @GET("chats/{id}/chat_messages?page=1&limit=50")
    Single<Response<GetChatMessagesList>> getChatMessagesList(@Path(value = "id") int chatId);

    @POST("chats/{id}/chat_messages")
    Single<Response<CreateChatMessage>> createChatMessage(@Path(value = "id") int chatId,
                                                          @Query("message") String message);

}
