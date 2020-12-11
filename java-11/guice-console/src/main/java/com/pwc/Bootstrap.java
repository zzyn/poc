package com.pwc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pwc.app.App;
import com.pwc.config.AppModule;

/**
 * Code Test
 *
 * @author zack zou (zzyn125@qq.com)
 * @version 1.0.0
 */
public class Bootstrap {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule(false));
        App app = injector.getInstance(App.class);
        String console = app.execute();
        System.out.println(console);
    }
}
