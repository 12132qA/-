package com.wx_back.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.workImgMapper;
import com.wx_back.pojo.workImg;
import com.wx_back.server.workImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class workImgServiceImpl extends ServiceImpl<workImgMapper, workImg> implements workImgService {
    @Autowired
    private workImgService workImgService;

    // 传输
   public boolean saveImg(workImg workImg){
       return  workImgService.save(workImg);
   }

   public workImg getOne(String phone ,String title){
       LambdaQueryWrapper<workImg> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
       // 添加查询条件 根据分类id 进行查询
       dishLambdaQueryWrapper.eq(workImg::getPhone, phone).eq(workImg::getName,title);
       return workImgService.getOne(dishLambdaQueryWrapper);
   }

}
