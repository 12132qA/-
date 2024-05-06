package com.wx_back.filter;

import com.alibaba.fastjson.JSON;
import com.wx_back.common.R;
import com.wx_back.pojo.LoginWeChat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")  // 过滤器
@Component // 注入到 spring 容器当中
@SuppressWarnings("all")
public class LoginCheckFilter implements Filter {
    //  操作 redis
    @Autowired
    private RedisTemplate redisTemplate;
    // 路径匹配器 支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    /**
     *
     * */
    @Override  // 用到 servlet 技术
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestRUI = request.getRequestURI();

        String[] urls  = new String[]{ // 定义不需要处理的请求路径
                "/Person/login",
                "/Person/logout",
                "/tabbar",
                "/main/Info",
                "/create/download/**",
                "/common/download",
                "/Search",
                "/vedio/**",
                "/images/**",
                "/img-person/**",
                "/create/upload",
                "/D:/wx_imgs/**",
                "/mike/swagger-ui.html/**",
                "/swagger-ui.html/**",
                "/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
                "/webjars/springfox-swagger-ui/**"
        };
        //  log.info("拦截到请求:{}",request.getRequestURI()); // {} 为占位符
        log.info("拦截到请求 {}",requestRUI);
        boolean check = check(urls,requestRUI);
        if(check) {
            log.info("本次请求{}不需要处理",requestRUI);
            filterChain.doFilter(request, response);
            return;
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        // 获取所有的参数数据
        while(parameterNames.hasMoreElements()) {
            log.info( " key值: "+ parameterNames.nextElement() );
        }
        String phone = request.getParameter("phone");
        String id = request.getParameter("id");
        log.info("从参数中获取 phone : {}" ,phone);
        log.info("从参数中获取 id : {}",id);
        if(id == null || phone == null){
            log.info("id 或 phone 输入为空");
            return;
        }

        LoginWeChat login = (LoginWeChat)redisTemplate.opsForValue().get(phone);
        // 检查登录状态
        if(phone.equals( login.getPhone()) && id.equals(login.getID ()) ) {
            log.info("已登录");
            filterChain.doFilter(request, response);  //filterChain.doFilter将请求转发给过滤器链下一个过滤器
            return;
        }

        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }
    /**
    *
    * @param urls
     * @param requestURI
     * @return
    * */
    // 路径匹配 检查本次请求是否放行
    public boolean check(String[] urls ,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match) return true;
        }
        return false;
    }


}
