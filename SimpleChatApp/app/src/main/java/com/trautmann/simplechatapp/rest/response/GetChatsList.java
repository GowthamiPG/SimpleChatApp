package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.model.MetaInformation;

import java.util.List;

/**
 * Created by Brandon Trautmann
 */

public class GetChatsList extends GenericResponse{

    @SerializedName("data")
    private List<Chat> chats;

    @SerializedName("meta")
    private MetaInformation metaInformation;

    public GetChatsList(List<Chat> chats, MetaInformation metaInformation) {
        this.chats = chats;
        this.metaInformation = metaInformation;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public MetaInformation getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(MetaInformation metaInformation) {
        this.metaInformation = metaInformation;
    }
}
