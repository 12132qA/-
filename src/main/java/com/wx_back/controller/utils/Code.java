package com.wx_back.controller.utils;

import io.swagger.models.auth.In;

/**
 *  规定前端数据返回的
 *              数据格式
 * */
public class Code {

    public static final Integer CODE_200 =200; // 请求成功

    public static final  Integer CODE_600 = 600; // 请求成功

    public static final Integer CODE_601 = 601; // 信息已经 存在

    public static final Integer CODE_500 = 500; // 服务返回错误 请联系管理员

    public static final Integer CODE_400 = 404; // 请求不存在

    public static final Integer SAVE_OK = 20011;  // 存入数据成功
    public static final Integer DELETE_OK = 20021; // 删除成功
    public static final  Integer UPDA_OK = 20031; // 跟新成功
    public static final Integer GET_OK = 20041; // 获取成功

    public static final Integer SAVE_ERR = 20010;  // 存入数据失败
    public static final Integer DELETE_ERR = 20020;  //  删除成功
    public static final  Integer UPDA_ERR = 20030; // 跟新失败
    public static final Integer GET_ERR = 20040; // 获取失败
    public static final Integer SYSTEM_ERR = 50001; // 系统异常
    public static final Integer BUSINESS_ERR = 50002; // 业务异常
    public static final Integer SYSTEM_TIMEOUT_ERR = 50003; // 系统超时异常
    public static final Integer SYSTEM_UNKNOW_ERR = 66666;  // 未知错误



}
