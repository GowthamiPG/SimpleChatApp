package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.model.User;

/**
 * Created by Brandon Trautmann
 */

public class ProfileActivityViewModel extends BaseObservable {

    private Context context;
    private User user;

    public ProfileActivityViewModel(Context context) {
        this.context = context;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Bindable
    public String getUserName() {
        return getUser() == null ? "" : getUser().getName();
    }

    @Bindable
    public String getUserEmail() {
        return getUser() == null ? "" : getUser().getEmail();
    }

    public void getProfile() {
        RestActions.getCurrentUser()
                .subscribe(getCurrentUser -> {
                    if (getCurrentUser.getUser() != null) {
                        setUser(getCurrentUser.getUser());
                        notifyPropertyChanged(BR._all);
                    }

                }, throwable -> {
                        Log.e("ProfileActivityVM","Error loading profile");
                });
    }


}
