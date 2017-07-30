package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.util.PreferencesHelper;
import com.trautmann.simplechatapp.view.MainActivity;

/**
 * Created by Brandon Trautmann
 */

public class InitSessionViewModel extends BaseObservable{

    private Context context;
    private boolean isRegistering;
    private boolean isLoggingIn;

    public InitSessionViewModel(Context context) {
        this.context = context;
        setLoggingIn(false);
        setRegistering(false);
    }

    @Bindable
    public boolean isLoggingIn() {
        return isLoggingIn;
    }

    public void setLoggingIn(boolean loggingIn) {
        isLoggingIn = loggingIn;
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public boolean isRegistering() {
        return isRegistering;
    }

    public void setRegistering(boolean registering) {
        isRegistering = registering;
        notifyPropertyChanged(BR._all);
    }

    public View.OnClickListener onAccountStatusPromptClicked() {
        return view -> setRegistering(!isRegistering());
    }

    public View.OnClickListener onLoginClicked(EditText emailInput, EditText passwordInput) {
        return view -> {
            String email = emailInput.getEditableText().toString();
            String password = passwordInput.getEditableText().toString();
            if (areValidLoginInputs(email, password)) {
                logUserIn(email, password);
            }
        };
    }

    public View.OnClickListener onRegisterClicked(EditText nameInput, EditText emailInput,
                                                  EditText passwordInput, EditText confirmPwInput) {
        return view -> {
            String name = nameInput.getEditableText().toString();
            String email = emailInput.getEditableText().toString();
            String password = passwordInput.getEditableText().toString();
            String confirmPw = confirmPwInput.getEditableText().toString();
            if (areValidRegisterInputs(name, email, password, confirmPw)) {
                registerUser(name, email, password, confirmPw);
            } else {
                Toast.makeText(context, "Please check input", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public boolean areValidRegisterInputs(String name, String email,
                                          String password, String confirmPassword) {
        return !TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(confirmPassword)
                && confirmPassword.equals(password);
    }

    public boolean areValidLoginInputs(String loginInput, String passwordInput) {
        return !TextUtils.isEmpty(loginInput)
                && !TextUtils.isEmpty(passwordInput);
    }

    public void logUserIn(String email, String password) {
        RestActions.login(email, password)
                .doOnSubscribe(disposable -> {
                    setLoggingIn(true);
                })
                .subscribe(genericResponse -> {
                    setLoggingIn(false);
                    setUserLoggedIn();
                    launchMainActivity();
                }, throwable -> {
                    setLoggingIn(false);
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
                });
    }

    private void setUserLoggedIn() {
        PreferencesHelper.set(Constants.Prefs.Auth.USER_LOGGED_IN,true);
    }

    private void launchMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void registerUser(String name, String email, String password,
                             String passwordConfirmation) {
        RestActions.createUser(name, email, password, passwordConfirmation)
                .doOnSubscribe(disposable -> {
                    setLoggingIn(true);
                })
                .subscribe(createUser -> launchMainActivity(), throwable -> Toast.makeText(context, "Couldn't sign you up." +
                        " Try again later!", Toast.LENGTH_SHORT).show());

    }
}
