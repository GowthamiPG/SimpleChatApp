package com.trautmann.simplechatapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Brandon Trautmann
 */

public class ChatMessage {

    @SerializedName("id")
    private int id;

    @SerializedName("chat_id")
    private int chatId;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("message")
    private String message;

    @SerializedName("created_at")
    private String messageTimestamp;

    @SerializedName("user")
    private User sender;

    public ChatMessage(int id, int chatId, int userId, String message, String messageTimestamp, User sender) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.message = message;
        this.messageTimestamp = messageTimestamp;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(String messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
