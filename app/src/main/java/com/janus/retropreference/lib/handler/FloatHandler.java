package com.janus.retropreference.lib.handler;

import android.content.SharedPreferences;

import com.janus.retropreference.lib.PreferenceHandler;

/**
 * Created by admin on 2018/4/26.
 */

public class FloatHandler implements PreferenceHandler<Float> {

    private SharedPreferences sp;

    @Override
    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public Float getValue(String key, Float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    @Override
    public boolean setValue(String key, Float value) {
        return sp.edit().putFloat(key, value).commit();
    }
}
