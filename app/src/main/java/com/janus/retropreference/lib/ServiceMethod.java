package com.janus.retropreference.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.janus.retropreference.lib.annotation.DefaultValue;
import com.janus.retropreference.lib.annotation.Get;
import com.janus.retropreference.lib.annotation.Preference;
import com.janus.retropreference.lib.annotation.Set;
import com.janus.retropreference.lib.annotation.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by janus on 2018/4/26.
 */

public class ServiceMethod {

    Method method;
    int type;//1-get,2-set
    String key;
    int valuePosition;
    int defaultValuePosition;
    SharedPreferences sp;
    Class<?> returnClazz;

    public ServiceMethod(Method method, String key, int type, int defaultValuePosition, SharedPreferences sp, Class<?> returnClazz) {
        this.method = method;
        this.key = key;
        this.type = type;
        this.defaultValuePosition = defaultValuePosition;
        this.sp = sp;
        this.returnClazz = returnClazz;
    }

    public ServiceMethod(Method method, String key, int type, int valuePosition, SharedPreferences sp) {
        this.method = method;
        this.key = key;
        this.type = type;
        this.valuePosition = valuePosition;
        this.sp = sp;
    }

    static class Builder {

        RetroPreference retroPreference;
        Method method;
        Annotation[] methodAnnos;
        Annotation[][] paramAnnos;
        Type[] paramTypes;

        public Builder(RetroPreference retroPreference, Method method) {
            this.retroPreference = retroPreference;
            this.method = method;
            this.methodAnnos = method.getAnnotations();
            this.paramTypes = method.getGenericParameterTypes();
            this.paramAnnos = method.getParameterAnnotations();
        }

        public ServiceMethod build(Context context) {
            Preference preference = method.getAnnotation(Preference.class);

            Utils.assertTrue(preference != null, "the method has no preference annotation");
            Utils.assertTrue(!TextUtils.isEmpty(preference.file()), "the method has no preference file name");
            Utils.assertTrue(!TextUtils.isEmpty(preference.key()), "the method has no preference key name");

            SharedPreferences sp = context.getSharedPreferences(preference.file(), Context.MODE_PRIVATE);

            Get get = method.getAnnotation(Get.class);
            Set set = method.getAnnotation(Set.class);
            if (get != null) {
                Class clazz = method.getReturnType();
                for (int i = 0; i < paramAnnos.length; i++) {
                    Annotation[] paramAnno = paramAnnos[i];
                    for (int j = 0; j < paramAnno.length; j++) {
                        Annotation annotation = paramAnno[j];
                        if (annotation instanceof DefaultValue) {
                            return new ServiceMethod(method, preference.key(), 1, i, sp, clazz);
                        }
                    }
                }
            } else if (set != null) {
                for (int i = 0; i < paramAnnos.length; i++) {
                    Annotation[] paramAnno = paramAnnos[i];
                    for (int j = 0; j < paramAnno.length; j++) {
                        Annotation annotation = paramAnno[j];
                        if (annotation instanceof Value) {
                            return new ServiceMethod(method, preference.key(), 2, i, sp);
                        }
                    }
                }
            }

            throw new IllegalArgumentException("the method is neither get nor set ");

        }

    }

}
