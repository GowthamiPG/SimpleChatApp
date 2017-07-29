package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.rest.model.Chat;

/**
 * Created by Brandon Trautmann
 */

public class UpdateChat extends GenericResponse {

    @SerializedName("data")
    private Chat chat;

    public UpdateChat(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
