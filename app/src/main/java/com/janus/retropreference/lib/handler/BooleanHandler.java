package com.janus.retropreference.lib.handler;

import android.content.SharedPreferences;

import com.janus.retropreference.lib.PreferenceHandler;

/**
 * Created by admin on 2018/4/26.
 */

public class BooleanHandler implements PreferenceHandler<Boolean> {

    private SharedPreferences sp;

    @Override
    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public Boolean getValue(String key, Boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    @Override
    public boolean setValue(String key, Boolean value) {
        return sp.edit().putBoolean(key, value).commit();
    }
}
