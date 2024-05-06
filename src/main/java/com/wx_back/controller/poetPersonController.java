package com.wx_back.controller;


import com.wx_back.Mapper.poetpersonMapper;
import com.wx_back.common.R;
import com.wx_back.pojo.poetperson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/poets")
@Slf4j
public class poetPersonController {
    @Autowired
    private poetpersonMapper poetpersonMapper;

    /**
     *
     * @Param id 根据id 获取诗人信息
     *
     * */

    @RequestMapping("/get")
    public R<poetperson> getById( @RequestParam("Pid") int Id){
        poetperson poetperson = poetpersonMapper.selectById(Id);
        return R.success(poetperson);
    }

}
