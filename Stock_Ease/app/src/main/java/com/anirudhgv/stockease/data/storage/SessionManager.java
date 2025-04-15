package com.anirudhgv.stockease.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "app_pref";
    private static final String KEY_TOKEN = "auth_token";

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        preferences.edit().putString(KEY_TOKEN,token).apply();
    }

    public String fetchAuthToken(){
        return preferences.getString(KEY_TOKEN,null);
    }

    public void clearToken() {
        preferences.edit().remove(KEY_TOKEN).apply();
    }
}
