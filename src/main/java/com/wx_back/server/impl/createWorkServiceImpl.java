package com.wx_back.server.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.createWorkMapper;
import com.wx_back.Mapper.wx_userMapper;
import com.wx_back.pojo.createWork;
import com.wx_back.server.createWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class createWorkServiceImpl extends ServiceImpl<createWorkMapper, createWork> implements createWorkService {
   @Autowired
    private createWorkMapper createWorkMapper;
   @Autowired
   private createWorkService createWorkService;
   @Autowired
   private wx_userMapper wxUserMapper;
   /**
    *  有 一定的 存取操作 可以适当 的 添加 缓存
    *  可尝试使用 redis 缓存方案
    * */


   public Boolean saveWork(createWork createWork){
       try {
           String id = wxUserMapper.getByPhone(createWork.getUserPhone()).getId().toString();
           log.info(" 作品插入 ID  :"+id);
           createWork.setId(id);
       }catch (Exception e) {
           e.printStackTrace();
       }
       return createWorkService.save(createWork);
   }

   /**
    *
    * @Param id 指的是 用户的 Id
    *  id 根据 用户 id 获取 所有的作品
    * */

   public List<createWork> getWork(String id){

//       if(createWorks== null){  // 缓存 不存在了 就 添加进入  能 一定程度上减轻 mysql的负担
           LambdaQueryWrapper<createWork> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
           // 添加查询条件 根据分类id 进行查询
           dishLambdaQueryWrapper.eq(createWork::getUserPhone, id);

       return createWorkMapper.selectList(dishLambdaQueryWrapper);

   }



}
