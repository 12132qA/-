package com.wx_back.controller;

import com.wx_back.Mapper.maininfoMapper;
import com.wx_back.common.R;
import com.wx_back.pojo.maininfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *  首页的 词牌名的相关信息
 *
 * */

@RestController
@CrossOrigin
@RequestMapping("/main")
@Slf4j
public class infoController {
    @Autowired
    private maininfoMapper maininfoMapper;
    @Autowired
    private HttpClient httpUtil;

    /**
     *  @Param name
     *  通过词牌名获取相关的信息
     *
     * */
    @RequestMapping("/Info")
    public R<maininfo> getInfo(String name){
        log.info("词牌名 {} 相关信息被调用",name);
        maininfo info = maininfoMapper.getInfo(name);
        return R.success(info);
    }

    public byte[]  getBytesByStream(InputStream inputStream){
        byte[] bytes = new byte[1024];
        int b;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while((b = inputStream.read(bytes)) != -1){

                byteArrayOutputStream.write(bytes,0,b);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
