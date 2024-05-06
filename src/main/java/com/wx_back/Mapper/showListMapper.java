package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.search;
import com.wx_back.pojo.showList;
import com.wx_back.pojo.workImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface showListMapper extends BaseMapper<showList> {

    @Select("select * from showlist")
    List<showList> selectAll();
    @Update("update showlist set imagUrl =#{NewUrl}  where id=#{id};")
    int updateOne(int id,String NewUrl);
}
