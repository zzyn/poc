package com.tools.generater;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class aad_svc_generater {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator().setTemplateEngine(new FreemarkerTemplateEngine());

        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("");
        gc.setOpen(false);
        gc.setOutputDir("/Users/zack/aad-svc/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);

        gc.setServiceName("%sService");

        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/kt_aad_qa?useUnicode=true&characterEncoding=utf8");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityBuilderModel(true);
        strategy.setEntityColumnConstant(false);
        strategy.setEntitySerialVersionUID(false);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(new String[]{
                "access_token"
                , "account"
                , "acl"
                , "group"
                , "id_token"
                , "refresh_token"
                , "role"
                , "user"
                , "user_role_acl"});
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.platform.aadsvc");
        mpg.setPackageInfo(pc);

        InjectionConfig injectionConfig = new InjectionConfig() {
            //自定义属性注入:abc
            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        mpg.setCfg(injectionConfig);

        // 执行生成
        mpg.execute();
    }

}