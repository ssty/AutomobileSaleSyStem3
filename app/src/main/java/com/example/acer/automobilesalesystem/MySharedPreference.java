package com.example.acer.automobilesalesystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

public class MySharedPreference {

    private SharedPreferences prefs;

    public MySharedPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setKeyValues(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public void setKeyValues(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }

    public void setKeyValues(String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }

    public void setKeyValues(String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    public String getStringValues(String key) {
        String value = prefs.getString(key, "");
        return value;
    }

    public int getIntValues(String key) {
        int nullValue = 0;
        int value = prefs.getInt(key, nullValue);
        return value;
    }

    public long getLongValues(String key) {
        long nullValue = 0;
        long value = prefs.getLong(key, nullValue);
        return value;
    }

    public boolean getBoolValues(String key) {
        boolean value = false;
        value = prefs.getBoolean(key, value);
        return value;
    }

    public Map<String, ?> getAllVal() {
        Map<String, ?> keys = prefs.getAll();
        return keys;
    }

    public void clearData() {
        prefs.edit().clear().apply();
    }
}
