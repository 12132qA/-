package com.wx_back.controller;

import com.wx_back.common.BaseContext;
import com.wx_back.controller.utils.HttpGet;
import com.wx_back.pojo.LoginRequest;
import com.wx_back.pojo.LoginWeChat;
import com.wx_back.pojo.wx_user;
import com.wx_back.server.impl.wx_userServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wx_back.common.R;

import java.util.Scanner;
import java.util.UUID;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "标准演示接口 登录功能")
@SuppressWarnings("all")
@RequestMapping("/Person")
public class LoginController {
    /**
     * 登录注册 功能
     *
     * @Param code 微信接口 返回的 code 用于 获取 openid 和 Session_key
     * redis 做缓存
     * */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    HttpGet httpGet ;
    @Autowired
    wx_userServiceImpl wxUserService;

    /**
     * @Param code 用于 获取 openid 和  session_key 的 code 码
     * @Param phone 用户 手机号
     * */

    @RequestMapping("/login")
    @ApiOperation(value = "接受String参数", notes = "演示String 参数是否接受成功")
    public R<LoginWeChat> LoginTo(@ApiParam(name = "接收String参数", defaultValue = "")String code, String phone){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        log.info(code);
        log.info(phone);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAppid("wx88439c29c604083");
        loginRequest.setSecret("f79805eff8478ed63f7331693ac5ed13");
        loginRequest.setJs_code(code);
        loginRequest.setGrant_type("authorization_code");

         LoginWeChat weChat = httpGet.getWeChat(loginRequest);
        // 获取 openid 和  session_key

         // 线程 隔离
        BaseContext.setCurrentId(Long.valueOf(phone));
        String ID= UUID.randomUUID().toString();
        weChat.setID(ID);
        weChat.setPhone(phone);
        log.info("生成的 wechat对象用于检测登录状态 : {}",weChat);
        wx_user wxUser = new wx_user();
         // 存在 这返回相应的值 @TODO
        LoginWeChat loginWeChat = (LoginWeChat) valueOperations.get(phone);

        if(loginWeChat!=null){
            log.info("loginWeChat:" + loginWeChat.toString());
            valueOperations.getAndSet(phone,weChat);
            loginWeChat.setID(ID);
            wxUser.setAdmin(1);
            wxUser.setOpenid(weChat.getOpenid());
            wxUser.setPhone(phone);
//            wxUser.setSession_key();
            wxUserService.saveUser(wxUser);
            log.info("登录数据存入了 redis 缓存中..");
        }else {
            // 存入缓存中
            valueOperations.set(phone,weChat);
            wxUser.setAdmin(1);
            wxUser.setOpenid(weChat.getOpenid());
            wxUser.setPhone(phone);
            wxUserService.saveUser(wxUser);
            log.info("登录数据存入了 redis 缓存中");
        }
        log.info("生成的用户唯一标识ID : {}",ID);
        return R.success(weChat);
    }

    /**
     * 登出
     * */

    @RequestMapping("/logout")
    public R<LoginWeChat> LoginOut(String code,String phone){
        return null;
    }

}
