package com.trautmann.simplechatapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.InitSessionActivityBinding;
import com.trautmann.simplechatapp.viewmodel.InitSessionViewModel;

/**
 * Created by Brandon Trautmann
 */

public class InitSessionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (userAlreadyLoggedIn()) {
            //TODO: Bump user to MainActivity if already logged in
        } else {
            InitSessionActivityBinding binding =
                    DataBindingUtil.setContentView(this, R.layout.init_session_activity);
            binding.setViewModel(new InitSessionViewModel(this));
        }
    }

    private boolean userAlreadyLoggedIn() {
        return false;
    }

}
