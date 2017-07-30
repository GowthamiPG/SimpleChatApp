package com.trautmann.simplechatapp.util;

/**
 * Created by Brandon Trautmann
 */

public class Constants {

    public static class IntentArguments {
        public static final String CHAT_ID = "chatId";
        public static final String CHAT_NAME = "chatName";
    }

    public static class DialogArguments {
        public static final String CHAT_ACTION = "chatAction";
        public static final String CHAT_ACTION_RENAME = "renameChat";
        public static final String CHAT_ACTION_CREATE = "createChat";
    }

    public static class Prefs {
        public static class Auth {
            public static final String USER_LOGGED_IN = "userLoggedIn";
            public static final String USER_AUTH_TOKEN = "userAuthToken";
        }
    }
}
