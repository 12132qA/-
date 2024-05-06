package com.wx_back.config;

import com.wx_back.filter.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class MyWebAppConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 重写addInterceptors方法并为拦截器配置拦截规则
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        //排除路径

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/tabbar","/Search");
    }
}
