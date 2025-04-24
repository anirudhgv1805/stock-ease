package com.anirudhgv.stockease.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "app_pref";
    private static final String KEY_TOKEN = "auth_token";
    private static final String KEY_USER_ROLE = "user_role";

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        preferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public String fetchAuthToken() {
        return preferences.getString(KEY_TOKEN, null);
    }

    public void saveUserRole(String role) {
        preferences.edit().putString(KEY_USER_ROLE, role).apply();
    }

    public String fetchUserRole() {
        return preferences.getString(KEY_USER_ROLE, null);
    }

    public boolean isClient() {
        return "client".equalsIgnoreCase(fetchUserRole());
    }

    public boolean isEmployee() {
        return "employee".equalsIgnoreCase(fetchUserRole());
    }

    public boolean isOwner() {
        return "owner".equalsIgnoreCase(fetchUserRole());
    }

    public void clearSession() {
        preferences.edit().clear().apply();
    }
}
