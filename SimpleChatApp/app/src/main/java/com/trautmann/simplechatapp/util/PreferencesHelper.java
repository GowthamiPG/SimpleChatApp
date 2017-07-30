package com.trautmann.simplechatapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Brandon Trautmann
 */

public class PreferencesHelper {

    private static SharedPreferences prefs;

    public static void initialize(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void clear() {
        prefs.edit().clear().apply();
    }

    public static void remove(String key) {
        prefs.edit().remove(key).apply();
    }

    public static void set(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public static void set(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public static boolean get(String key, boolean _default) {
        return prefs.getBoolean(key, _default);
    }

    public static String get(String key, String _default) {
        return prefs.getString(key, _default);
    }
}
