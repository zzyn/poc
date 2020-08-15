package com.mq.kafka.demo.service.impl;

import com.mq.kafka.demo.entity.Center;
import com.mq.kafka.demo.mapper.CenterMapper;
import com.mq.kafka.demo.service.CenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CenterServiceImpl extends ServiceImpl<CenterMapper, Center> implements CenterService {

}
