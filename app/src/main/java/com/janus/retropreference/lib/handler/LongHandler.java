package com.janus.retropreference.lib.handler;

import android.content.SharedPreferences;

import com.janus.retropreference.lib.PreferenceHandler;

/**
 * Created by admin on 2018/4/26.
 */

public class LongHandler implements PreferenceHandler<Long> {

    private SharedPreferences sp;

    @Override
    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public Long getValue(String key, Long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    @Override
    public boolean setValue(String key, Long value) {
        return sp.edit().putLong(key, value).commit();
    }
}
