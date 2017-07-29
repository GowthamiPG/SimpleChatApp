package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;

import com.trautmann.simplechatapp.model.ChatMessage;

/**
 * Created by Brandon Trautmann
 */

public class ChatMessageListItemViewModel {


    private Context context;
    private ChatMessage chatMessage;

    public ChatMessageListItemViewModel(Context context, ChatMessage chatMessage) {
        this.context = context;
        this.chatMessage = chatMessage;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getSenderName() {
        return chatMessage.getSender().getName();
    }

    public String getMessageBody() {
        return chatMessage.getMessage();
    }


}
