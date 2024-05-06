package com.wx_back.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wx_back.pojo.workImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface workImgMapper extends BaseMapper<workImg> {
    @Select("select * from workimg where name=#{name} and phone=#{phone}")
    workImg selectBy(String name,String phone);
    @Select("select * from workimg")
    List<workImg> selectAll();
    @Update("update workimg set filePath =#{NewUrl}  where url=#{url} and phone=#{phone}")
    void updateOne(String url,String phone,String NewUrl);

}
