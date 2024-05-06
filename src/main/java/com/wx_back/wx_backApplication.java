package com.wx_back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.wx_back.utils.ChangeRequestImgURl;

@Slf4j   // lombok 提供的  日志
@SpringBootApplication
//@ServletComponentScan
//@MapperScan("com.domain")
//@ComponentScan("com.wx_back.config")
//@EnableCreateCacheAnnotation
//@EnableMethodCache(basePackages = "com.wx_back")
//@EnableTransactionManagement(proxyTargetClass = true) // 开启事务注解的支持  强置变换proxy动态代理的实现方法

public class wx_backApplication {
   @Autowired
    ChangeRequestImgURl changeRequestImgURl;
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(wx_backApplication.class);
        // 调用 修改了图片的url
        ChangeRequestImgURl changeRequestImgURl = run.getBean(ChangeRequestImgURl.class);
        changeRequestImgURl.changeImgUrl();
        log.info("项目启动成功");
    }

}