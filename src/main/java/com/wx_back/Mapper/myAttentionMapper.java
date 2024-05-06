package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.myAttention;
import com.wx_back.pojo.poetry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface myAttentionMapper extends BaseMapper<myAttention>{

    @Select("select * from myattention where phone = #{phone}")
    public List<myAttention> getAll(String phone);

}
