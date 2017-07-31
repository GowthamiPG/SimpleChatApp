package com.trautmann.simplechatapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.model.ChatMessage;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.response.CreateChatMessage;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivityViewModel extends ViewModel {

    private Chat chat;
    private MutableLiveData<List<ChatMessage>> chatMessageLiveData;
    private MutableLiveData<String> titleLiveData;

    public ChatDetailActivityViewModel(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    private void setChatName(String chatName) {
        this.chat.setName(chatName);
        this.titleLiveData.setValue(chatName);
    }

    public MutableLiveData<String> getTitleLiveData() {
        if (titleLiveData == null) {
            titleLiveData = new MutableLiveData<>();
            titleLiveData.setValue(chat.getName());
        }
        return titleLiveData;
    }

    public int getChatId() {
        return chat.getId();
    }

    public MutableLiveData<List<ChatMessage>> getChatMessageLiveData() {
        if (chatMessageLiveData == null) {
            chatMessageLiveData = new MutableLiveData<>();
            getChatMessagesList(getChatId());
        }
        return chatMessageLiveData;
    }

    public void getChatMessagesList(int chatId) {
        RestActions.getChatMessagesList(chatId)
                .subscribe(getChatMessagesList -> {
                    if (getChatMessagesList.getChatMessages() != null) {
                        chatMessageLiveData.setValue(getChatMessagesList.getChatMessages());
                    }
                }, throwable -> {

                });
    }

    public Single<CreateChatMessage> createChatMessage(String message, Context context) {
        return RestActions.createChatMessage(getChatId(), message)
                .doOnSuccess(createChatMessage -> {
                    if (createChatMessage.getMessageSent() != null) {
                        List<ChatMessage> currentChatMessages = chatMessageLiveData.getValue();
                        if (currentChatMessages != null) {
                            currentChatMessages.add(createChatMessage.getMessageSent());
                            chatMessageLiveData.setValue(currentChatMessages);
                        }
                    }
                })
                .doOnError(throwable -> Toast.makeText(context, "Couldn't send message!",
                        Toast.LENGTH_SHORT).show());
    }

    public void updateChat(String newChatName, Context context) {
        if (canRenameChat(newChatName)) {
            RestActions.updateChat(chat.getId(), newChatName)
                    .doOnSuccess(updateChat -> setChatName(newChatName))
                    .subscribe(updateChat -> {
                            },
                            throwable -> Toast.makeText(context,
                                    "Unable to rename chat", Toast.LENGTH_SHORT).show());
        }
    }

    public boolean canSendMessage(String input) {
        return !TextUtils.isEmpty(input);
    }

    public boolean canRenameChat(String input) {
        return !TextUtils.isEmpty(input);
    }

}
