package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.view.MainActivity;

/**
 * Created by Brandon Trautmann
 */

public class InitSessionViewModel extends BaseObservable{

    private Context context;
    private boolean isLoggingIn;

    public InitSessionViewModel(Context context) {
        this.context = context;
        setLoggingIn(false);
    }

    public boolean isLoggingIn() {
        return isLoggingIn;
    }

    public void setLoggingIn(boolean loggingIn) {
        isLoggingIn = loggingIn;
    }

    public View.OnClickListener onLoginClicked(EditText emailInput, EditText passwordInput) {
        return view -> {
            if (areValidLoginInputs(emailInput, passwordInput)) {
                String email = emailInput.getEditableText().toString();
                String password = emailInput.getEditableText().toString();
                logUserIn(email, password);
            }
        };
    }

    public boolean areValidLoginInputs(EditText loginInput, EditText passwordInput) {
        return !TextUtils.isEmpty(loginInput.getEditableText().toString())
                && !TextUtils.isEmpty(passwordInput.getEditableText().toString());
    }

    public void logUserIn(String email, String password) {
        RestActions.login(email, password)
                .doOnSubscribe(disposable -> {
                    setLoggingIn(true);
                    notifyPropertyChanged(BR._all);
                })
                .subscribe(genericResponse -> {
                    setLoggingIn(false);
                    notifyPropertyChanged(BR._all);
                    launchMainActivity();
                }, throwable -> {
                    setLoggingIn(false);
                    notifyPropertyChanged(BR._all);
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
                });
    }

    private void launchMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void registerUser() {

    }
}
