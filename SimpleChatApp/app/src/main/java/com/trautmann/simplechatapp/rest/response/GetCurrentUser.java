package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.model.User;

/**
 * Created by Brandon Trautmann
 */

public class GetCurrentUser extends GenericResponse {

    @SerializedName("data")
    private User user;

    public GetCurrentUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
