package com.trautmann.simplechatapp.viewmodel;

import com.trautmann.simplechatapp.rest.RestActions;
import com.trautmann.simplechatapp.rest.response.GetChatsList;

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
}
