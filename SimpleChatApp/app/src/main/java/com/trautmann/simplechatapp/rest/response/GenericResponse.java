package com.trautmann.simplechatapp.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Brandon Trautmann
 */

public class GenericResponse {


    @SerializedName("message")
    private String message;
    @SerializedName("errors")
    private List<Error> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isErrorFree() {
        return errors == null || errors.size() == 0;
    }

    private class Error {

        public Error(String name) {
            this.name = name;
        }

        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
