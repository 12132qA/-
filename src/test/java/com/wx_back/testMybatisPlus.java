package com.wx_back;

import com.wx_back.Mapper.userMapper;
import com.wx_back.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {wx_backApplication.class})
public class testMybatisPlus {
      @Autowired
     private userMapper userMapper;
    @Test
    public void saveUser(){
        user user = new user();
        user.setName("name");
        user.setPassword("101010");
        user.setDeleted(1);
        user.setVersion(2);

    }
}
