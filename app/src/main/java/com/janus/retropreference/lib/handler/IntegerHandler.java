package com.janus.retropreference.lib.handler;

import android.content.SharedPreferences;

import com.janus.retropreference.lib.PreferenceHandler;

/**
 * Created by admin on 2018/4/26.
 */

public class IntegerHandler implements PreferenceHandler<Integer> {

    private SharedPreferences sp;

    @Override
    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public Integer getValue(String key, Integer defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    @Override
    public boolean setValue(String key, Integer value) {
        return sp.edit().putInt(key, value).commit();
    }
}
