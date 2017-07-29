package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.trautmann.simplechatapp.rest.model.User;

/**
 * Created by Brandon Trautmann
 */

public class UpdateUser extends GenericResponse {

    @SerializedName("data")
    private User user;

    public UpdateUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
