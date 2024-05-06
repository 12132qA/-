package com.wx_back.server.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.poetpersonMapper;
import com.wx_back.pojo.poetperson;
import com.wx_back.server.poetpersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class poetpersonServiceImpl extends ServiceImpl<poetpersonMapper, poetperson> implements poetpersonService {
}
