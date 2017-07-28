package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.response.GenericResponse;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class InitSessionViewModel {

    private Context context;

    public InitSessionViewModel(Context context) {
        this.context = context;
    }

    public boolean areValidLoginInputs(EditText loginInput, EditText passwordInput) {
        return !TextUtils.isEmpty(loginInput.getEditableText().toString())
                && !TextUtils.isEmpty(passwordInput.getEditableText().toString());
    }

    public Single<GenericResponse> logUserIn(String login, String password) {
        return RestActions.login(login, password);
    }

    public void registerUser() {

    }
}
