package com.web.www.utils;

/**
 * 线程上下文工具类
 */
public class CommonContextUtil {

    private final static ThreadLocal<Object> commonThreadLocal = new ThreadLocal<>();

    public static void set(Object value) {
        commonThreadLocal.set(value);
    }

    public static Object get() {
        return commonThreadLocal.get();
    }

    public static void remove() {
        commonThreadLocal.remove();
    }
}
