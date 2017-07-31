package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.trautmann.simplechatapp.model.User;
import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.util.Constants;
import com.trautmann.simplechatapp.util.PreferencesHelper;
import com.trautmann.simplechatapp.view.InitSessionActivity;

/**
 * Created by Brandon Trautmann
 */

public class ProfileActivityViewModel extends BaseObservable {

    private Context context;
    private User user;
    private boolean isNetworking;
    private boolean isEditingProfile;

    public ProfileActivityViewModel(Context context) {
        this.context = context;
        setNetworking(false);
        setEditingProfile(false);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public String getUserName() {
        return getUser() == null ? "" : getUser().getName();
    }

    @Bindable
    public String getUserEmail() {
        return getUser() == null ? "" : getUser().getEmail();
    }

    @Bindable
    public boolean isNetworking() {
        return isNetworking;
    }

    public void setNetworking(boolean networking) {
        isNetworking = networking;
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public boolean isEditingProfile() {
        return isEditingProfile;
    }

    public void setEditingProfile(boolean editingProfile) {
        isEditingProfile = editingProfile;
        notifyPropertyChanged(BR._all);
    }

    public void getProfile() {
        RestActions.getCurrentUser()
                .subscribe(getCurrentUser -> {
                    if (getCurrentUser.getUser() != null) {
                        setUser(getCurrentUser.getUser());
                    }

                }, throwable -> {
                    Log.e("ProfileActivityVM", "Error loading profile");
                });
    }

    public View.OnClickListener onLogOutClicked() {
        return view -> logOut();
    }

    public View.OnClickListener onUpdateUserClicked(EditText nameEditText,
                                                    EditText emailEditText) {
        return view -> {
            if (!TextUtils.isEmpty(nameEditText.getEditableText().toString())
                    || !TextUtils.isEmpty(emailEditText.getEditableText().toString())) {
                updateUser(nameEditText.getEditableText().toString(),
                        emailEditText.getEditableText().toString());
            }
        };
    }

    public void updateUser(String name, String email) {
        RestActions.updateUser(name, email)
                .subscribe(updateUser -> setEditingProfile(false),
                        throwable -> Toast.makeText(context, "Couldn't update profile. Try again " +
                        "later!", Toast.LENGTH_SHORT).show());
    }

    public void logOut() {
        RestActions.logout()
                .doOnSubscribe(disposable -> {
                    setNetworking(true);
                })
                .subscribe(genericResponse -> {
                    setNetworking(false);
                    storeUserLoggedOut();
                    launchInitSessionActivity();

                }, throwable -> {
                    // TODO: We don't care if the call fails as the user wants to
                    // log out regardless. Use a Completable(?) instead of Single
                    setNetworking(false);
                    storeUserLoggedOut();
                    launchInitSessionActivity();

                });
    }

    private void storeUserLoggedOut() {
        PreferencesHelper.set(Constants.Prefs.Auth.USER_LOGGED_IN, false);
    }

    private void launchInitSessionActivity() {
        Intent intent = new Intent(context, InitSessionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
