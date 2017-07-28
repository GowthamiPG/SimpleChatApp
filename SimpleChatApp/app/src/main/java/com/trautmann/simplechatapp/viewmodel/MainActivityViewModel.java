package com.trautmann.simplechatapp.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.response.GetChatsList;
import com.trautmann.simplechatapp.view.ProfileActivity;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class MainActivityViewModel {

    public MainActivityViewModel() {
    }

    public Single<GetChatsList> getChatList() {
        return RestActions.getChatsList();
    }


    public void launchProfileActivity(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
