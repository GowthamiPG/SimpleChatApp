package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.model.ChatMessage;

/**
 * Created by Brandon Trautmann
 */

public class CreateChatMessage extends GenericResponse {

    @SerializedName("data")
    private ChatMessage messageSent;

    public CreateChatMessage(ChatMessage messageSent) {
        this.messageSent = messageSent;
    }

    public ChatMessage getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(ChatMessage messageSent) {
        this.messageSent = messageSent;
    }
}
