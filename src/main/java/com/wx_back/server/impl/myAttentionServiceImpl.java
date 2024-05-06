package com.wx_back.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wx_back.Mapper.myAttentionMapper;
import com.wx_back.pojo.myAttention;
import com.wx_back.server.myAttentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class myAttentionServiceImpl
        extends ServiceImpl<myAttentionMapper, myAttention>
        implements myAttentionService {
}
