package com.trautmann.simplechatapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.trautmann.simplechatapp.R;
import com.trautmann.simplechatapp.databinding.InitSessionActivityBinding;
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
            //TODO: Bump user to MainActivity if already logged in
        } else {
            binding =
                    DataBindingUtil.setContentView(this, R.layout.init_session_activity);
            binding.setViewModel(new InitSessionViewModel(this));

            handleLoginClicks();
        }
    }

    private void handleLoginClicks() {
        binding.loginButton.setOnClickListener(view -> {
            if (binding.getViewModel().areValidLoginInputs(
                    binding.usernameEditText, binding.passwordEditText)) {
                String email = binding.usernameEditText.getEditableText().toString();
                String password = binding.passwordEditText.getEditableText().toString();
                binding.getViewModel().logUserIn(email, password)
                        .doOnSubscribe(disposable -> {
                            binding.loginButton.setVisibility(View.GONE);
                            binding.loginProgressBar.setVisibility(View.VISIBLE);
                        })
                        .subscribe(genericResponse -> {
                            Intent intent = new Intent(InitSessionActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }, throwable -> {
                            binding.loginProgressBar.setVisibility(View.GONE);
                            binding.loginButton.setVisibility(View.VISIBLE);
                        });
            }
        });
    }

    private boolean userAlreadyLoggedIn() {
        return false;
    }

}
