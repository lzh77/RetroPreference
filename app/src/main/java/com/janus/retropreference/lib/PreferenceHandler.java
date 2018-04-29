package com.janus.retropreference.lib;

import android.content.SharedPreferences;

/**
 * Created by janus on 2018/4/26.
 */

public interface PreferenceHandler<T> {

    void setSp(SharedPreferences sp);

    T getValue(String key, T defaultValue);

    boolean setValue(String key, T value);

}
