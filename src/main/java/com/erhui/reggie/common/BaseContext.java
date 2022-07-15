package com.erhui.reggie.common;

/**
 * 用于保存和获取当前登录用户的Id
 * author:erhui
 * version:1.0
 **/
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
