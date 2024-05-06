package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper extends BaseMapper<search> {
    @Select("select * from poetry where author like #{data} or texts like #{data} or Dynastic like #{data} or name like #{data}")
    List<search> searchPoetry (String data);

}
