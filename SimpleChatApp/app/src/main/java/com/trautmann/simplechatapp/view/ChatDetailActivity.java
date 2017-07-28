package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.ChatDetailActivityBinding;
import com.trautmann.simplechatapp.rest.model.Chat;
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.viewmodel.ChatDetailActivityViewModel;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChatDetailActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.chat_detail_activity);
        int id = getIntent().getExtras().getInt(Constants.IntentArguments.CHAT_ID);
        String name = getIntent().getExtras().getString(Constants.IntentArguments.CHAT_NAME);
        binding.setViewModel(new ChatDetailActivityViewModel(this,
                new Chat(id, name, null, null)));

        setSupportActionBar(binding.chatDetailToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }




    }
}
