package com.trautmann.simplechatapp.rest;

import com.trautmann.simplechatapp.rest.response.CreateChat;
import com.trautmann.simplechatapp.rest.response.CreateChatMessage;
import com.trautmann.simplechatapp.rest.response.GenericResponse;
import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;
import com.trautmann.simplechatapp.rest.response.GetChatsList;
import com.trautmann.simplechatapp.rest.response.GetCurrentUser;
import com.trautmann.simplechatapp.rest.service.AuthService;
import com.trautmann.simplechatapp.rest.service.ChatsService;
import com.trautmann.simplechatapp.rest.service.UserService;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class RestActions {

    // Auth
    public static Single<GenericResponse> login(String email, String password) {
        RestAction<GenericResponse> action = new RestAction<>(
                ServiceCreator.createService(AuthService.class).login(email, password));
        return action.perform();
    }

    public static Single<GenericResponse> logout() {
        RestAction<GenericResponse> action = new RestAction<>(
                ServiceCreator.createService(AuthService.class).logout());
        return action.perform();
    }

    // User
    public static Single<GetCurrentUser> getCurrentUser() {
        RestAction<GetCurrentUser> action = new RestAction<>(
                ServiceCreator.createService(UserService.class).getCurrentUser());
        return action.perform();
    }

    // Chats
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


    public static Single<CreateChat> createChat(String chatName, String firstChatMessage) {
        RestAction<CreateChat> action = new RestAction<>(
                ServiceCreator.createService(ChatsService.class).createChat(chatName, firstChatMessage));
        return action.perform();
    }
}
