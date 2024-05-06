package com.wx_back.Mapper;

import com.wx_back.pojo.user;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper extends BaseMapper<user> {
    @Select("select * from user where id=#{id}")
    user selectById(Long id);


}
