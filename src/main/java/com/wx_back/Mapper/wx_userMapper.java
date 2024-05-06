package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.user;
import com.wx_back.pojo.wx_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.*;
@Mapper
public interface wx_userMapper extends BaseMapper<wx_user> {

    @Select("select * from wx_user where phone = #{phone};")
    public wx_user getByPhone(String phone);

}
