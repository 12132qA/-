package com.wx_back.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 *  描述 获取 openid 和 session_key 参数的实体类
 *        的实体类
 * */

@Setter
@Getter

public class LoginRequest implements Serializable {
    String appid;

    String secret;

    String js_code;

    String grant_type;
}
