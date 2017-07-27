package com.trautmann.simplechatapp.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Brandon Trautmann
 */

public class Chat {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("users")
    private List<User> users;

    @SerializedName("last_chat_message")
    private ChatMessage lastChatMessage;


    public Chat(int id, String name, List<User> users, ChatMessage lastChatMessage) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.lastChatMessage = lastChatMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public ChatMessage getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(ChatMessage lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }
}
