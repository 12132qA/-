package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.mylearn;
import com.wx_back.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface mylearnMapper extends BaseMapper<mylearn> {
    @Select("select * from mylearn where userPhone=#{id}")
    List<mylearn> selectByPhone(String phone);
}
