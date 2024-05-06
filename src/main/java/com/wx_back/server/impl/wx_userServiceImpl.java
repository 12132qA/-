package com.wx_back.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.wx_userMapper;
import com.wx_back.pojo.wx_user;
import com.wx_back.pojo.wx_user;
import com.wx_back.server.wx_userService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * */
@Service
@Slf4j
public class wx_userServiceImpl extends ServiceImpl<wx_userMapper, wx_user> implements wx_userService{
    @Autowired
    private wx_userService wxUserService;
    @Autowired
    private wx_userMapper wxUserMapper;
    @SneakyThrows// 查询是否会报异常不确定
    public String saveUser(wx_user wxUser){
        wx_user wxUserans = null;
           LambdaQueryWrapper<wx_user> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
           // 添加查询条件 根据分类id 进行查询
           dishLambdaQueryWrapper.eq(wx_user::getPhone, wxUser.getPhone());

           wxUserans = wxUserMapper.selectOne(dishLambdaQueryWrapper);


        if(wxUserans != null){
            wxUser.setId(wxUserans.getId());
            boolean res = wxUserService.updateById(wxUser);
            if(res)  log.info( "openid 修改成功" );
            return res?"成功":"失败";
        }

        return  wxUserService.save(wxUser)? "成功":"失败" ;
    }


}
