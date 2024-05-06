package com.wx_back.pojo.query;

import com.wx_back.pojo.user;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 乐观锁 测试使用
 *
 * */

@EqualsAndHashCode(callSuper = true)
@Data
public class userquery extends user {
    private Long upId; // 描述上限

}
