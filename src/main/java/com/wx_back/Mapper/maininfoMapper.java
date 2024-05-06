package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.maininfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface maininfoMapper extends BaseMapper<maininfo> {
    @Select("select * from maininfo where name=#{name}")
    public maininfo getInfo(String name);
}
