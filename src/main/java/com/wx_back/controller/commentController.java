package com.wx_back.controller;

import com.wx_back.Exception.BusinessException;
import com.wx_back.common.R;
import com.wx_back.common.commentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  commment 评论的处理类
 * @Param commentDto  评论区的对象的键值对信息
 *
 * @Param commentDto
 *
 * @Param key 该评论对应的键
 * @Param comment 该评论对应的值
 *
 * */
@Slf4j
@SuppressWarnings("all")
@RestController
@RequestMapping("/comment")
public class commentController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/save")
    public R<String> saveComment(commentDto commentDto) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        log.info(commentDto.getKey());
        try {
            if (valueOperations.get(commentDto.getKey()) != null) {
                valueOperations.getAndSet(commentDto.getKey(), commentDto.getComment());
            }
            else valueOperations.set(commentDto.getKey(),commentDto.getComment());
        }catch (BusinessException e){
            e.printStackTrace();
            log.error("comment评论区存储错误 可以是空指针");
        }
        return  R.success("评论信息存储成功");
    }
    @RequestMapping("/get")
    public R<Object> getComment(String sid){
         Object o = redisTemplate.opsForValue().get(sid);
        if(o == null){
            return R.success("无数据");
        }
        return R.success(o);
    }

}
