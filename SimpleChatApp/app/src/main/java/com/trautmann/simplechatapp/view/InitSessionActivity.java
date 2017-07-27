package com.trautmann.simplechatapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trautmann.simplechatapp.R;

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
            setContentView(R.layout.activity_init_session);
            showLoginFragment();
        }
    }

    private boolean userAlreadyLoggedIn() {
        return false;
    }

    private void showLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.initSessionFrameLayout, new LoginFragment(), "login").commit();
    }
}
