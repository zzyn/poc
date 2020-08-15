package com.mq.kafka.demo.domain.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mq.kafka.demo.domain.CityAndCenterService;
import com.mq.kafka.demo.domain.LocalDateTimeNow;
import com.mq.kafka.demo.entity.Center;
import com.mq.kafka.demo.entity.City;
import com.mq.kafka.demo.message.OmniCenterMessage;
import com.mq.kafka.demo.message.OmniCenterPayload;
import com.mq.kafka.demo.service.CenterService;
import com.mq.kafka.demo.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class CityAndCenterServiceImpl implements CityAndCenterService {

    private final CityService cityService;
    private final CenterService centerService;
    private final LocalDateTimeNow localDateTimeNow;

    @Autowired
    public CityAndCenterServiceImpl(CityService cityService, CenterService centerService, LocalDateTimeNow localDateTimeNow) {
        this.cityService = cityService;
        this.centerService = centerService;
        this.localDateTimeNow = localDateTimeNow;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void upsert(OmniCenterMessage msg) throws Exception {

        try {

            OmniCenterPayload payload = msg.getPayload();

            if(Objects.nonNull(payload.getCityCode())){

                City city = new City()
                        .setCityCname(payload.getCityCName())
                        .setCityName(payload.getCityName())
                        .setCityKey(payload.getCityCode())
                        .setCountry(payload.getCountry())
                        .setCreatedBy(payload.getCreatedBy())
                        .setCreatedTimestamp(payload.getCreatedDate());

                City dbCity = cityService.getBaseMapper()
                        .selectOne(new QueryWrapper<City>()
                                .lambda()
                                .eq(City::getCityKey, city.getCityKey()));

                if(Objects.nonNull(dbCity)){

                    dbCity.setCityCname(city.getCityCname())
                            .setCityName(city.getCityName())
                            .setCountry(city.getCountry())
                            .setUpdatedBy(city.getCreatedBy())
                            .setUpdatedTimestamp(localDateTimeNow.now());

                    boolean ops = cityService.saveOrUpdate(dbCity);
                    if(!ops){
                        throw new RuntimeException("update city failed.");
                    }

                } else {
                    boolean ops = cityService.save(city);
                    if(!ops){
                        throw new RuntimeException("save city failed.");
                    }
                }

            }


            Center center = new Center()
                    .setCenterStatus(StringUtils.compareIgnoreCase("active", payload.getCenterStatus()) == 0 ? 1 : 0)
                    .setCityKey(payload.getCityCode())
                    .setCenterCode(payload.getCenterCode())
                    .setCenterName(payload.getCenterName())
                    .setCreatedBy(payload.getCreatedBy())
                    .setCreatedTimestamp(payload.getCreatedDate());

            Center dbCenter = centerService.getBaseMapper()
                    .selectOne(new QueryWrapper<Center>()
                            .lambda()
                            .eq(Center::getCenterCode, center.getCenterCode()));

            if(Objects.nonNull(dbCenter)){

                dbCenter.setCenterName(center.getCenterName())
                        .setCityKey(center.getCityKey())
                        .setCenterStatus(center.getCenterStatus())
                        .setUpdatedBy(center.getCreatedBy())
                        .setUpdatedTimestamp(localDateTimeNow.now());

                boolean ops = centerService.saveOrUpdate(dbCenter);
                if(!ops){
                    throw new RuntimeException("update center failed.");
                }

            } else {
                boolean ops = centerService.save(center);
                if(!ops){
                    throw new RuntimeException("save center failed.");
                }
            }

        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
