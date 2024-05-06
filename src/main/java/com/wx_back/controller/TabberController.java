package com.wx_back.controller;

import com.wx_back.common.R;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 *  轮播图图片传输
 * */
@RestController
@CrossOrigin
public class TabberController {

    @RequestMapping("/tabbar")
    @ResponseBody
    public R<List<String>> tabBar_Test(){ // @RequestBody 只有传入的数据为 json 类型才有用
        System.out.println("tabBar_test 被调用了");
        List<String> tabBar = new ArrayList<>();

//        tabBar.add("https://www.ssfiction.com/wp-content/uploads/2020/08/20200801_5f24bfbe4d33f.jpg");
//        tabBar.add("https://img0.baidu.com/it/u=2483689985,3754430559&fm=253&fmt=auto&app=138&f=PNG?w=1204&h=500");
//        tabBar.add("https://img1.baidu.com/it/u=441017204,2115992118&fm=253&fmt=auto&app=138&f=JPEG?w=650&h=460");
    // 返回图片的url
        tabBar.add("https://img2.baidu.com/it/u=3924985565,444500931&fm=253&fmt=auto&app=138&f=JPEG?w=1193&h=500");
        tabBar.add("https://inews.gtimg.com/newsapp_bt/0/13857342546/1000");
        tabBar.add("https://img1.baidu.com/it/u=441017204,2115992118&fm=253&fmt=auto&app=138&f=JPEG?w=650&h=460");


        return  R.success(tabBar);
    }
}
