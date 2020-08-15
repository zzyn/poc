package com.mq.kafka.demo.service.impl;

import com.mq.kafka.demo.entity.City;
import com.mq.kafka.demo.mapper.CityMapper;
import com.mq.kafka.demo.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

}
