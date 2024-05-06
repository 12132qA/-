package com.wx_back.controller;

import com.wx_back.Exception.BusinessException;
import com.wx_back.Mapper.SearchMapper;
import com.wx_back.pojo.search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
@Slf4j
public class SearchController {
    @Autowired
    private SearchMapper searchMapper;

    /**
     *  搜索功能 实现
     * @Param data1
     *     搜索信息
     * */

    @RequestMapping("/Search")
    @ResponseBody
    public List<search> SearchData(@RequestParam("data") String data1){ // 参数的映射
        String data = "";
        System.out.println(data1);
        /**
         *  对 数据 进行处理
         *  设置 sql 的 模糊匹配
         * */
        try {
            if (data1.length() != 0) {
                StringBuilder sb = new StringBuilder(data1);
                sb.insert(0, '%');
                sb.append('%');
                data = sb.toString();
            }
        }catch (BusinessException e){
            log.info("后端业务处理异常");
        }
        List<search> searchList = searchMapper.searchPoetry(data);
        if(searchList == null){
            System.out.println("无数据");
        }else{
            System.out.println("有数据");
        }
        return searchList;
    }

}
