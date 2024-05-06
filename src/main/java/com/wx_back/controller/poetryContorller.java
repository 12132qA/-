package com.wx_back.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wx_back.Mapper.mylearnMapper;
import com.wx_back.Mapper.wx_userMapper;
import com.wx_back.common.R;
import com.wx_back.common.posDto;
import com.wx_back.pojo.mylearn;
import com.wx_back.pojo.poetry;
import com.wx_back.pojo.wx_user;
import com.wx_back.server.impl.mylearnServiceImpl;
import com.wx_back.server.impl.poetryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wx_back.Mapper.poetryMapper;
import java.util.List;
@Slf4j
@RestController
@SuppressWarnings("all")
@CrossOrigin
@RequestMapping("/poetry")
public class poetryContorller {
    @Autowired
    private wx_userMapper userMapper;
     @Autowired
     private poetryMapper poetryMapper;
     @Autowired
     private mylearnMapper mylearnMapper;
     @Autowired
     private mylearnServiceImpl mylearnService;
     @Autowired
     private poetryServiceImpl poetryService;
     /**
      * @Param posDto 接收 诗歌请求 信息类
      * */
     @RequestMapping("/gets")
     public R<List<poetry>> getBy(posDto posDto){

         log.info("数据是 "+ posDto.toString());

         String name = posDto.getName();
         String party = posDto.getParty();

         LambdaQueryWrapper<poetry> queryWrapper = new LambdaQueryWrapper<>();
         List<poetry> poetries  = null;
       try {
           if (name.length() > 0) queryWrapper.eq(poetry::getAuthor, name);
           if (party.length() > 0) queryWrapper.eq(poetry::getSortFaction, party);

           poetries = poetryMapper.selectList(queryWrapper); //1782ca67880f4d2395c4ebcb70f9da13.jpg
       }catch (Exception e){
           e.printStackTrace();
           log.info("数据库查询错误");
       }
         return  R.success(poetries);
     }
     /**
      *
      * @Param id 通过 id
      * 获取相关的值
      * */
     @RequestMapping("/get")
     public R<poetry> getById(@RequestParam("Pid") int Pid){
         log.info("数据 是 :" + " get请求 // ");
         poetry poetry = poetryMapper.selectById(Pid);
         return  R.success(poetry);
     }

     @RequestMapping("/add")
    public R<String> AddToPerson(@RequestBody mylearn mylearn){
         log.info("对应的数据是 "+mylearn);

         wx_user wxUser = userMapper.getByPhone(mylearn.getUserPhone());
         mylearn.setId(wxUser.getId().toString());

         if(mylearnService.save(mylearn)){
             return R.success("打卡成功");
         }
        return R.error("打卡失败");
     }
     @RequestMapping("/myLearn")
    public R<List<mylearn>> getMyLearn(String phone,String session_key){
         List<mylearn> mylearns = mylearnMapper.selectByPhone(phone);
         return  R.success(mylearns);

     }


}
