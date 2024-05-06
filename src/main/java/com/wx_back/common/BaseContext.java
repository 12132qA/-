package com.wx_back.common;

import lombok.Data;

/**
 * 基于ThreadLocal封装的工具类 用于保持和获取当前登录用户的id
 * */
@Data
public class BaseContext {
    // 以线程为作用域 获取id 名称
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

/**
 * *设置值
 *   @param id
 * */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    /**
     * @return  获取值
     * */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
