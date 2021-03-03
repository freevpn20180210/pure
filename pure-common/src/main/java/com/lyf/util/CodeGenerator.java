package com.lyf.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis-plus代码生成器
 *
 * @author lyf
 * @since 2021-03-03
 */

public class CodeGenerator {

    //数据库配置
    private static String url = "jdbc:mysql:///mybatis?characterEncoding=utf-8&useSSL=false&useUnicode=true&autoReconnect=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String password = "root";
    private static DbType dbType = DbType.MYSQL;

    //表名,如有多个用英文逗号分割
    private static String tableName = "user";
    //项目模块名
    private static String projectModuleName = "pure-module1";
    //业务模块名
    private static String bizModuleName = "user";
    //包名
    private static String parentPackage = "com.lyf";
    //作者
    private static String author = "lyf";

    /*********************************************以下固定配置***************************************************/
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator ag = new AutoGenerator();

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setDbType(dbType);
        ag.setDataSource(dsc);

        //全局配置
        GlobalConfig gc = new GlobalConfig()
                //设置作者名
                .setAuthor(author)
                //设置输出文件夹
                .setOutputDir(System.getProperty("user.dir") + "/" + projectModuleName + "/src/main/java/")
                //生成后是否打开资源管理器--否
                .setOpen(false)
                //重新生成时是否覆盖原文件--是
                .setFileOverride(true)
                //开启实体属性Swagger2注解--是
                .setSwagger2(true)
                //开启xml通用查询映射结果--是
                .setBaseResultMap(true)
                //开启xml通用查询结果列--是
                .setBaseColumnList(true)
                //自定义生成文件命名
                .setEntityName("%s")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController");
        ag.setGlobalConfig(gc);

        //模板配置
        //自定义模板路径
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("/templates/mybatisCodeGenerator/entity.java")
                .setMapper("/templates/mybatisCodeGenerator/mapper.java")
                .setXml("/templates/mybatisCodeGenerator/mapper.xml")
                .setService("/templates/mybatisCodeGenerator/service.java")
                .setServiceImpl("/templates/mybatisCodeGenerator/serviceImpl.java")
                .setController("/templates/mybatisCodeGenerator/controller.java");
        ag.setTemplate(templateConfig);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("vo", "vo");
                this.setMap(map);
            }
        };
        //自定义输出配置
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();
        // 创建Vo对象
        fileOutConfigs.add(new FileOutConfig("/templates/mybatisCodeGenerator/vo.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return System.getProperty("user.dir") + "/" + projectModuleName + "/src/main/java/"
                        + parentPackage.replace(".", "/")
                        + "/" + bizModuleName
                        + "/vo/" + tableInfo.getEntityName() + "Vo.java";
            }
        });
        cfg.setFileOutConfigList(fileOutConfigs);
        //自定义覆盖配置,只覆盖Entity类
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                return true;
            } else {
                String fileName = file.getName();
                //如果是文件名是以这些字符串作为后缀结束的,不覆盖
//                return !(fileName.endsWith("Controller.java")
//                        || fileName.endsWith("Mapper.java") || fileName.endsWith("Mapper.xml")
//                        || fileName.endsWith("Service.java") || fileName.endsWith("ServiceImpl.java")
//                        || fileName.endsWith("Vo.java"));
                return true;

            }
        });
        ag.setCfg(cfg);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(bizModuleName);
        pc.setParent(parentPackage);
        ag.setPackageInfo(pc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //要映射的表名
        strategy.setInclude(tableName.split(","));
        //去掉表前缀_
        strategy.setTablePrefix(pc.getModuleName() + "_");
        //表名生成策略：下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //表字段生成策略：下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //controller是否需要@RestController注解
        strategy.setRestControllerStyle(true);
        //开启lombok模型
        strategy.setEntityLombokModel(true);
        //开启链式调用
        strategy.setChainModel(true);
        //设置逻辑删除字段
        strategy.setLogicDeleteFieldName("deleted");
        //设置乐观锁版本号字段
        strategy.setVersionFieldName("version");
        //设置自动填充字段
        List<TableFill> list = new ArrayList<>();
        list.add(new TableFill("deleted", FieldFill.INSERT));
        list.add(new TableFill("version", FieldFill.INSERT));
        list.add(new TableFill("status", FieldFill.INSERT));
        list.add(new TableFill("file_uuid", FieldFill.INSERT));
        list.add(new TableFill("creator_id", FieldFill.INSERT));
        list.add(new TableFill("create_time", FieldFill.INSERT));
        list.add(new TableFill("updater_id", FieldFill.UPDATE));
        list.add(new TableFill("update_time", FieldFill.UPDATE));
        strategy.setTableFillList(list);
        //公共父类
        strategy.setSuperEntityClass("com.lyf.base.entity.BaseEntity");
//        strategy.setSuperMapperClass("com.apa7.ms.common.service.mapper.BaseMp");
        strategy.setSuperServiceClass("com.lyf.base.service.BaseService");
//        strategy.setSuperServiceImplClass("com.apa7.ms.common.service.service.impl.BaseServiceImpl");
        strategy.setSuperControllerClass("com.lyf.base.controller.BaseController");
        ag.setStrategy(strategy);
        ag.execute();

    }

}