package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.rest.model.User;

/**
 * Created by Brandon Trautmann
 */

public class CreateUser extends GenericResponse {

    @SerializedName("data")
    private User user;

    public CreateUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

