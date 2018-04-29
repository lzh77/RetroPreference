package com.janus.retropreference.lib;

import com.janus.retropreference.lib.handler.BooleanHandler;
import com.janus.retropreference.lib.handler.FloatHandler;
import com.janus.retropreference.lib.handler.IntegerHandler;
import com.janus.retropreference.lib.handler.LongHandler;
import com.janus.retropreference.lib.handler.StringHandler;
import com.janus.retropreference.lib.handler.StringSetHandler;

/**
 * Created by janus on 2018/4/26.
 */

public class Utils {

    static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

    public static void assertTrue(boolean flag, String msg) {
        if (!flag) {
            throw new IllegalArgumentException(msg);
        }
    }

    static <T> PreferenceHandler buildHandler(Class<T> clazz) {
        if (clazz == String.class) {
            return new StringHandler();
        } else if (clazz == java.util.Set.class) {
            return new StringSetHandler();
        } else if (clazz == Long.class) {
            return new LongHandler();
        } else if (clazz == Integer.class) {
            return new IntegerHandler();
        } else if (clazz == Float.class) {
            return new FloatHandler();
        } else if (clazz == Boolean.class) {
            return new BooleanHandler();
        }
        throw new IllegalArgumentException("the data type can't be used in sharedpreferences");
    }

}
