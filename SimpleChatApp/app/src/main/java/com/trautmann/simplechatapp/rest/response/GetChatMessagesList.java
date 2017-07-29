package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.model.MetaInformation;

import java.util.List;

/**
 * Created by Brandon Trautmann
 */

public class GetChatMessagesList extends GenericResponse {

    @SerializedName("data")
    private List<ChatMessage> chatMessages;

    @SerializedName("meta")
    private MetaInformation metaInformation;

    public GetChatMessagesList(List<ChatMessage> chatMessages, MetaInformation metaInformation) {
        this.chatMessages = chatMessages;
        this.metaInformation = metaInformation;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public MetaInformation getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }
}
