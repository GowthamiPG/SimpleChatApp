package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.ChatDetailActivityBinding;
import com.trautmann.simplechatapp.rest.model.Chat;
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.viewmodel.ChatDetailActivityViewModel;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivity extends AppCompatActivity {

    ChatDetailActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                DataBindingUtil.setContentView(this, R.layout.chat_detail_activity);
        int chatId = getIntent().getExtras().getInt(Constants.IntentArguments.CHAT_ID);
        String chatName = getIntent().getExtras().getString(Constants.IntentArguments.CHAT_NAME);
        binding.setViewModel(new ChatDetailActivityViewModel(this,
                new Chat(chatId, chatName, null, null)));

        setSupportActionBar(binding.chatDetailToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getChatMessages(chatId);
    }

    private void getChatMessages(int chatId) {
        binding.getViewModel().getChatMessagesList(chatId)
                .doOnSubscribe(disposable -> Log.d("ChatDetailActivity", "Getting chat messages..."))
                .subscribe(getChatMessagesList -> {
                    if (getChatMessagesList.getChatMessages() != null) {
                        Toast.makeText(ChatDetailActivity.this, "Found " +
                                        getChatMessagesList.getChatMessages().size() + " messages!",
                                Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> Toast.makeText(ChatDetailActivity.this, "Error getting" +
                        " messages!", Toast.LENGTH_SHORT).show());
    }
}
