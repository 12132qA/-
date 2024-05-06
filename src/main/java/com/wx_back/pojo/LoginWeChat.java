package com.wx_back.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信https://api.weixin.qq.com/sns/jscode2session 接口返回的 数据
 * */
@Setter
@Getter
public class LoginWeChat implements Serializable {
    String openid;
    // 用户手机号
    String phone;
    String ID;
}
