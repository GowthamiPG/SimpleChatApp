package com.trautmann.simplechatapp.viewmodel;

import com.trautmann.simplechatapp.rest.RestActionFactory;
import com.trautmann.simplechatapp.rest.response.GetChatsList;

import io.reactivex.Single;

/**
 * Created by Brandon Trautmann
 */

public class MainActivityViewModel {

    public MainActivityViewModel() {
    }

    public Single<GetChatsList> getChatList() {
        return RestActionFactory.getChatsList();
    }
}
