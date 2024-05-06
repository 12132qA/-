package com.wx_back.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@SuppressWarnings("all")

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
        log.info("afterCompletion...");
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.info("URL : {}",request.getRequestURI() );
        Object data = (String)request.getSession().getAttribute("data");
        log.info(" phone: {}",data.getClass().getFields());
        String sessin_key = (String)request.getSession().getAttribute("session_key");
        log.info(" session_key: {}",sessin_key);
        log.info("preHandle...");
        return true;
    }
}