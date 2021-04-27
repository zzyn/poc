package com.kitchen.dataproviders.impl;

import com.kitchen.dataproviders.IngestRateProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class IngestRateProviderConfigImpl implements IngestRateProvider {

    private final String CONFIG_FILE = "config.properties";

    @Override
    public int getIngestRate() {
        Properties properties = new Properties();
        int rate = 0;
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE));
            rate =  Integer.valueOf(properties.getProperty("order.ingest.rate")).intValue();

        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return rate;
    }
}
