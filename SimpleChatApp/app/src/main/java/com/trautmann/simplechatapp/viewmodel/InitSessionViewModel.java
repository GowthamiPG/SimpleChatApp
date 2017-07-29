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
    }

    @Bindable
    public boolean isRegistering() {
        return isRegistering;
    }

    public void setRegistering(boolean registering) {
        isRegistering = registering;
    }

    public View.OnClickListener onAccountStatusPromptClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRegistering(!isRegistering());
                notifyPropertyChanged(BR._all);
            }
        };
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

    public View.OnClickListener onRegisterClicked(EditText nameInput, EditText emailInput,
                                                  EditText passwordInput, EditText confirmPwInput) {
        return view -> {
            if (areValidRegisterInputs(nameInput, emailInput, passwordInput, confirmPwInput)) {
                String name = nameInput.getEditableText().toString();
                String email = emailInput.getEditableText().toString();
                String password = passwordInput.getEditableText().toString();
                String passwordConfirmation = confirmPwInput.getEditableText().toString();
                registerUser(name, email, password, passwordConfirmation);
            } else {
                Toast.makeText(context, "Please check input", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public boolean areValidRegisterInputs(EditText nameInput, EditText emailInput,
                                          EditText passwordInput, EditText confirmPwInput) {
        return !TextUtils.isEmpty(nameInput.getEditableText().toString())
                && !TextUtils.isEmpty(emailInput.getEditableText().toString())
                && !TextUtils.isEmpty(passwordInput.getEditableText().toString())
                && !TextUtils.isEmpty(confirmPwInput.getEditableText().toString())
                && confirmPwInput.getEditableText().toString()
                .equals(passwordInput.getEditableText().toString());
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

    public void registerUser(String name, String email, String password,
                             String passwordConfirmation) {
        RestActions.createUser(name, email, password, passwordConfirmation)
                .doOnSubscribe(disposable -> {
                    setLoggingIn(true);
                    notifyPropertyChanged(BR._all);
                })
                .subscribe(createUser -> launchMainActivity(), throwable -> Toast.makeText(context, "Couldn't sign you up." +
                        " Try again later!", Toast.LENGTH_SHORT).show());

    }
}
