package com.trautmann.simplechatapp.rest;

import com.trautmann.simplechatapp.rest.response.CreateChatMessage;
import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;
import com.trautmann.simplechatapp.rest.response.GetChatsList;
import com.trautmann.simplechatapp.rest.service.ChatsService;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class RestActions {

    public static Single<GetChatsList> getChatsList() {
        RestAction<GetChatsList> action = new RestAction<>(
                ServiceCreator.createService(ChatsService.class).getChatsList());
        return action.perform();
    }

    public static Single<GetChatMessagesList> getChatMessagesList(int chatId) {
        RestAction<GetChatMessagesList> action = new RestAction<>(
                ServiceCreator.createService(ChatsService.class).getChatMessagesList(chatId));
        return action.perform();
    }

    public static Single<CreateChatMessage> createChatMessage(int chatId, String message) {
        RestAction<CreateChatMessage> action = new RestAction<>(
                ServiceCreator.createService(ChatsService.class).createChatMessage(chatId, message));
        return action.perform();
    }
}
