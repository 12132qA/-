package com.wx_back.controller;
import com.wx_back.common.R;
import com.wx_back.pojo.user;
import com.wx_back.Mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  用于 测试 的 类
 *
 * */

@RestController
@CrossOrigin
public class testController {
    @Autowired
    private userMapper userDao;

    @RequestMapping("/register")
    @ResponseBody
    public R<String> register(@RequestBody user user){ // @RequestBody 只有传入的数据为 json 类型才有用

        System.out.println(user);
        int insert = userDao.insert(user);
        if(insert>0){
            return R.success("注册成功");
        }
        return R.success("注册失败");
    }

    /**
     * .
     *
     * @author zgy
     * @date 2024/4/10 15:24
     * @param str
     * @return
     * @methodName wx_test
     *
     **/

    @RequestMapping("/test")
    @ResponseBody
    public String wx_test(String str){ // @RequestBody 只有传入的数据为 json 类型才有用
        List<user> userList = userDao.selectList(null);
        System.out.println(userList);
        System.out.println(userList.get(0));
        System.out.println(str);
        return"响应成功";
    }
}