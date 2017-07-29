package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.databinding.library.baseAdapters.BR;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.model.Chat;
import com.trautmann.simplechatapp.rest.model.ChatMessage;
import com.trautmann.simplechatapp.rest.response.CreateChatMessage;
import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;
import com.trautmann.simplechatapp.rest.response.UpdateChat;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivityViewModel extends BaseObservable{

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

    @Bindable
    public String getTitle() {
        return chat.getName();
    }

    public int getChatId() {
        return chat.getId();
    }

    public final ObservableArrayList<ChatMessage> chatMessages =
            new ObservableArrayList<>();

    public ObservableArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public Single<GetChatMessagesList> getChatMessagesList(int chatId) {
        return RestActions.getChatMessagesList(chatId);
    }

    public Single<CreateChatMessage> createChatMessage(String message) {
        return RestActions.createChatMessage(getChatId(), message);
    }


    public Single<UpdateChat> updateChat(String newChatName) {
        return RestActions.updateChat(chat.getId(), newChatName)
                .doOnSuccess(updateChat -> {
                    chat.setName(newChatName);
                    notifyPropertyChanged(BR._all);
                });
    }

    // When we go back to data binding onClick, use this
    public View.OnClickListener sendMessage(int chatId, EditText textInput) {
        return view -> {
            if (!TextUtils.isEmpty(textInput.getEditableText().toString())) {
                createChatMessage(textInput.getEditableText().toString());
            }
        };
    }

}
