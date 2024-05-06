package com.wx_back.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.mylearnMapper;
import com.wx_back.pojo.mylearn;
import com.wx_back.server.mylearnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class mylearnServiceImpl  extends ServiceImpl<mylearnMapper, mylearn> implements mylearnService {
}
