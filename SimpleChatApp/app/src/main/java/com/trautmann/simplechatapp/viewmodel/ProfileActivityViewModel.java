package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.model.User;
import com.trautmann.simplechatapp.view.InitSessionActivity;

/**
 * Created by Brandon Trautmann
 */

public class ProfileActivityViewModel extends BaseObservable {

    private Context context;
    private User user;
    private boolean isLoggingOut;

    public ProfileActivityViewModel(Context context) {
        this.context = context;
        setLoggingOut(false);
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

    public boolean isLoggingOut() {
        return isLoggingOut;
    }

    public void setLoggingOut(boolean loggingOut) {
        isLoggingOut = loggingOut;
    }

    public void getProfile() {
        RestActions.getCurrentUser()
                .subscribe(getCurrentUser -> {
                    if (getCurrentUser.getUser() != null) {
                        setUser(getCurrentUser.getUser());
                        notifyPropertyChanged(BR._all);
                    }

                }, throwable -> {
                    Log.e("ProfileActivityVM", "Error loading profile");
                });
    }

    public View.OnClickListener onLogOutClicked() {
        return view -> logOut();
    }

    public void logOut() {
        RestActions.logout()
                .doOnSubscribe(disposable -> {
                    setLoggingOut(true);
                    notifyPropertyChanged(BR._all);
                })
                .subscribe(genericResponse -> {
                    setLoggingOut(false);
                    notifyPropertyChanged(BR._all);
                    launchInitSessionActivity();

                }, throwable -> {
                    // TODO: We don't care if the call fails as the user wants to
                    // log out regardless. Use a Completable(?) instead of Single
                    setLoggingOut(false);
                    notifyPropertyChanged(BR._all);
                    launchInitSessionActivity();

                });
    }

    private void launchInitSessionActivity() {
        Intent intent = new Intent(context, InitSessionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
