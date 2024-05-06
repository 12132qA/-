package com.wx_back.controller.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx_back.pojo.LoginRequest;
import com.wx_back.pojo.LoginWeChat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * 发送 请求 获取 openid 和 session_key
 *  设置值
 * @Param loginRequest
 * */
@Slf4j
@Component
public class HttpGet {
    @Autowired
    private HttpClient httpClient;
    /**
     *  用于 获取  openid 和  sesion_key
     *  设置值
     *  添加synchronized关键字 线程锁  防止线程阻塞  互斥问题的产生
     * @Param loginRequest
     *
     * */
    public synchronized LoginWeChat getWeChat(LoginRequest loginRequest){
        System.out.println("get we Chat 被调用了");

        // 获取的结果对象
        LoginWeChat loginWeChat = null;
        // 添加 线程锁  防止线程互斥问题的产生

        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(8000);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + loginRequest.getAppid() + "&secret=" + loginRequest.getSecret() + "&js_code=" + loginRequest.getJs_code() + "&grant_type=authorization_code";
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 8000);
        // https://api.weixin.qq.com/sns/jscode2session?appid=wx88439c29c604083c&secret=f79805eff8478ed63f7331693ac5ed13&js_code=0e37Ik100w8wpQ1wz8400nMj6r27Ik1&grant_type=authorization_code
        // 添加参数
        // f79805eff8478ed63f7331693ac5ed13 小程序密钥
        String result = "";
        try {
            int code = httpClient.executeMethod(getMethod);
            if (code == 200) {
                result = getMethod.getResponseBodyAsString();
                loginWeChat = JSON.parseObject(result, LoginWeChat.class);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return loginWeChat;
    }


}
