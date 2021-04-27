package com.kitchen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kitchen.app.App;
import com.kitchen.config.AppModule;
import lombok.extern.slf4j.Slf4j;

/**
 * Engineering Challenge Homework
 * Bootstrap of Application
 *
 * @author zack zou (zzyn125@qq.com)
 * @version 1.0.0
 */
@Slf4j
public class Bootstrap {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        App app = injector.getInstance(App.class);
        log.info("app is working");
        app.execute();
    }
}
