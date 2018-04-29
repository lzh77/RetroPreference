package com.janus.retropreference.lib.handler;

import android.content.SharedPreferences;

import com.janus.retropreference.lib.PreferenceHandler;

/**
 * Created by janus on 2018/4/26.
 */

public class StringHandler implements PreferenceHandler<String> {

    private SharedPreferences sp;

    @Override
    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public String getValue(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    @Override
    public boolean setValue(String key, String value) {
        return sp.edit().putString(key, value).commit();
    }
}
