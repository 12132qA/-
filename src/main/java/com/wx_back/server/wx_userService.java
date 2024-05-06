package com.wx_back.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wx_back.pojo.wx_user;
import org.apache.ibatis.annotations.Update;

public interface wx_userService extends IService<wx_user> {
//    @Update(" update  wx_user set openid =  where phone = #{phone}")
//    public int updateByPhone(wx_user wxUser,String phone);
}
