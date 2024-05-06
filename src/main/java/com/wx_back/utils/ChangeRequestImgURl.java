package com.wx_back.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wx_back.Mapper.workImgMapper;
import com.wx_back.pojo.createWork;
import com.wx_back.pojo.showList;
import com.wx_back.pojo.workImg;
import com.wx_back.server.impl.workImgServiceImpl;
import jdk.internal.dynalink.beans.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wx_back.Mapper.showListMapper;
import java.util.List;

@Slf4j
@Component
public class ChangeRequestImgURl {
    // 测试时 ip 地址可能随时改变
    public static  String ImgPublicURL = "http://192.168.29.104:8008";
    @Autowired
    private  showListMapper showListMapper;
    @Autowired
    private workImgMapper workImgMapper;

    // 对img文件进行 请求地址的修改
    public void changeImgUrl(){
        log.info("更改 WorkImg的url:");
        // 设置
        List<workImg> workImgs = workImgMapper.selectAll();
        for(workImg workImg : workImgs){
            workImgMapper.updateOne(workImg.getUrl(),workImg.getPhone(), ImgPublicURL+workImg.getUrl());
        }
        log.info("更改 showLists的url:");
        List<showList> showLists = showListMapper.selectAll();

        for(showList showList : showLists){
            showListMapper.updateOne(showList.getId(),ImgPublicURL+showList.getImagUrl().substring(27));
        }

    }

}