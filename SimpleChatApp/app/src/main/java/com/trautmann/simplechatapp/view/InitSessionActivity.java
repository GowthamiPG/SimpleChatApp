package com.trautmann.simplechatapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.InitSessionActivityBinding;
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.util.PreferencesHelper;
import com.trautmann.simplechatapp.viewmodel.InitSessionViewModel;

/**
 * Created by Brandon Trautmann
 */

public class InitSessionActivity extends AppCompatActivity {


    InitSessionActivityBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (userAlreadyLoggedIn()) {
            launchMainActivity();
        } else {
            binding =
                    DataBindingUtil.setContentView(this, R.layout.init_session_activity);
            binding.setViewModel(new InitSessionViewModel());
        }
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean userAlreadyLoggedIn() {
        return PreferencesHelper.get(Constants.Prefs.Auth.USER_LOGGED_IN, false);
    }

}
