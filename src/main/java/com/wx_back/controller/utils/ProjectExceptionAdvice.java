package com.wx_back.controller.utils;

import com.wx_back.Exception.BusinessException;
import com.wx_back.Exception.SystemException;
import com.wx_back.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@SuppressWarnings("all")
@RestControllerAdvice
public class ProjectExceptionAdvice {
   // 拦截 所有的 异常信息

    /**
     *  拦截 异常处理的 类
     * @Param ex
     *
     * */
    @ExceptionHandler(BusinessException.class)
    public R doBusinessException(Exception ex){
    // 记录日志
        log.info("业务 异常");
    // 告诉运维

    // 通知开发
        ex.printStackTrace();
        return R.error("后端业务异常");
    }
    @ExceptionHandler(SystemException.class)
    public R doSystemException(SystemException e){
        // 记录日志
        // 发送消息给运维
        // 发送邮箱给 开发人员

        log.info("系统 异常");
//        System.out.println("系统 异常");
        return new R<String>(); // e.getCode(),null,e.getMessage()
    }


}
