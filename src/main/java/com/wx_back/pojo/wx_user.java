package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 *  wx_user
 *  用户实体类
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("wx_user")
public class wx_user implements Serializable {
    /**
     * 使用 雪花 id
     * */
   @TableField(value = "id")
   @TableId(type = IdType.ASSIGN_ID,value = "id")
    Long id;
    String phone;

    // 权限
    int admin;
    String openid;
    String session_key;

    public wx_user(String phone, String openid, String sessionKey) {
        this.phone = phone;
        this.openid = openid;
        this.session_key = sessionKey;
    }

    public wx_user(String phone, String openid) {
    }
}
