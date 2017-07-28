package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.model.Chat;
import com.trautmann.simplechatapp.rest.response.GetChatMessagesList;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivityViewModel {

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

    public String getTitle() {
        return chat.getName();
    }

    public Single<GetChatMessagesList> getChatMessagesList(int chatId) {
        return RestActions.getChatMessagesList(chatId);
    }

    public View.OnClickListener sendMessage(EditText textInput) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOG", "Send: " + textInput.getEditableText().toString());
            }
        };
    }

}
