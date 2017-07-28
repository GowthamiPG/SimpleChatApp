package com.trautmann.simplechatapp.rest;

import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;
import com.trautmann.simplechatapp.rest.response.GetChatsList;
import com.trautmann.simplechatapp.rest.service.ChatsService;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class RestActionFactory {

    public static Single<GetChatsList> getChatsList() {
        RestAction<GetChatsList> action = new RestAction<>(
                ServiceFactory.createService(ChatsService.class).getChatsList());
        return action.perform();
    }

    public static Single<GetChatMessagesList> getChatMessagesList(int chatId) {
        RestAction<GetChatMessagesList> action = new RestAction<>(
                ServiceFactory.createService(ChatsService.class).getChatMessagesList(chatId));
        return action.perform();
    }
}
