package com.janus.retropreference;

import com.janus.retropreference.lib.annotation.DefaultValue;
import com.janus.retropreference.lib.annotation.Get;
import com.janus.retropreference.lib.annotation.Preference;
import com.janus.retropreference.lib.annotation.Set;
import com.janus.retropreference.lib.annotation.Value;

/**
 * Created by janus on 2018/4/26.
 */

public interface Setting {

    @Preference(file = "setting", key = "name")
    @Get
    String name(@DefaultValue String def);

    @Preference(file = "setting", key = "name")
    @Set
    boolean saveName(@Value String name);

}
