package com.janus.retropreference.lib;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by janus on 2018/4/26.
 */

public class RetroPreference {

    private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap<>();

    public RetroPreference() {
    }

    public <T> T create(final Context context, final Class<T> service) {
        Utils.validateServiceInterface(service);
        eagerlyValidateMethods(service, context);

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        // If the method is a method from Object then defer to normal invocation.
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(this, args);
                        }
                        ServiceMethod serviceMethod = loadServiceMethods(method, context);

                        SharedPreferences sp = serviceMethod.sp;

                        if (serviceMethod.type == 1) {
                            Class r = serviceMethod.returnClazz;
                            PreferenceHandler handler = Utils.buildHandler(r);
                            handler.setSp(sp);
                            Object defaultValue = args[serviceMethod.defaultValuePosition];
                            return handler.getValue(serviceMethod.key, defaultValue);
                        } else if (serviceMethod.type == 2) {
                            Object value = args[serviceMethod.valuePosition];
                            Class r = value.getClass();
                            PreferenceHandler handler = Utils.buildHandler(r);
                            handler.setSp(sp);
                            return handler.setValue(serviceMethod.key, value);
                        }
                        return null;
                    }
                });
    }

    private <T> void eagerlyValidateMethods(Class<T> service, Context context) {
        for (Method method : service.getDeclaredMethods()) {
            loadServiceMethods(method, context);
        }
    }

    private ServiceMethod loadServiceMethods(Method method, Context context) {
        ServiceMethod result;
        synchronized (serviceMethodCache) {
            result = serviceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(this, method).build(context);
                serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

}
