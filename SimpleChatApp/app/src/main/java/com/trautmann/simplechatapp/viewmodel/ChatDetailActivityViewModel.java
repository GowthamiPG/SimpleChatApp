package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;

import com.trautmann.simplechatapp.rest.RestActionFactory;
import com.trautmann.simplechatapp.rest.model.Chat;
import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivityViewModel {

    private Context context;
    private Chat chat;

    public ChatDetailActivityViewModel(Context context, Chat chat) {
        this.context = context;
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getTitle() {
        return chat.getName();
    }

    public Single<GetChatMessagesList> getChatMessagesList(int chatId) {
        return RestActionFactory.getChatMessagesList(chatId);
    }

}
