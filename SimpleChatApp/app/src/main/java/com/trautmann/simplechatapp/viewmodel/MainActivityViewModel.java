package com.trautmann.simplechatapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;

import com.trautmann.simplechatapp.model.Chat;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.response.CreateChat;
import com.trautmann.simplechatapp.view.ProfileActivity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class MainActivityViewModel extends ViewModel{

    private MutableLiveData<List<Chat>> chatLiveData;

    public MainActivityViewModel() {
    }

    public MutableLiveData<List<Chat>> getChatLiveData() {
        if (chatLiveData == null) {
            chatLiveData = new MutableLiveData<>();
            getChatList();
        }
        return chatLiveData;
    }

    public void getChatList() {
        RestActions.getChatsList()
        .subscribe(getChatsList -> {
            if (getChatsList.getChats() != null) {
                chatLiveData.setValue(getChatsList.getChats());
            }

        }, throwable -> {

        });
    }

    public Single<CreateChat> createChat(String chatName, String firstChatMessage) {
        return RestActions.createChat(chatName, firstChatMessage)
                .doOnSuccess(createChat -> {
                    List<Chat> currentChats = chatLiveData.getValue();
                    if (currentChats != null) {
                        currentChats.add(createChat.getChat());
                    }
                    chatLiveData.setValue(currentChats);
                });
    }

    public void launchProfileActivity(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
