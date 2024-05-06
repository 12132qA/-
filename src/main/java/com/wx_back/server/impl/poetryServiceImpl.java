package com.wx_back.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.poetryMapper;
import com.wx_back.pojo.poetry;
import com.wx_back.server.poetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class poetryServiceImpl extends ServiceImpl<poetryMapper, poetry> implements poetryService {

}
