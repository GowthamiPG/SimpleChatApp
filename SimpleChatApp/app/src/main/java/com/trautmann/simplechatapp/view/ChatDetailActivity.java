package com.trautmann.simplechatapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trautmann.simplechatapp.R;

/**
 * Created by Brandon Trautmann
 */

public class ChatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail_activity);
    }
}
