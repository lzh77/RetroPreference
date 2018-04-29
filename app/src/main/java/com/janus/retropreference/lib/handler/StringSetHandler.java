package com.janus.retropreference.lib.handler;

import android.content.SharedPreferences;

import com.janus.retropreference.lib.PreferenceHandler;

/**
 * Created by admin on 2018/4/26.
 */

public class StringSetHandler implements PreferenceHandler<java.util.Set<String>> {

    private SharedPreferences sp;

    @Override
    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public boolean setValue(String key, java.util.Set<String> value) {
        return sp.edit().putStringSet(key, value).commit();
    }

    @Override
    public java.util.Set<String> getValue(String key, java.util.Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }
}
