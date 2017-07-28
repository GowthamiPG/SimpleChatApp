package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.trautmann.simplechatapp.rest.model.User;

/**
 * Created by Brandon Trautmann
 */

public class ProfileActivityViewModel extends BaseObservable {

    private Context context;
    private User user;

    public ProfileActivityViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return getUser().getName();
    }

    public String getUserEmail() {
        return getUser().getEmail();
    }

    public void getProfile() {

    }


}
