package com.lyf.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator ag = new AutoGenerator();
//        FreemarkerTemplateEngine freemarkerTemplateEngine=new FreemarkerTemplateEngine();
//        Properties properties=new Properties();
//        properties.setProperty(FreemarkerTemplateEngine)
//        freemarkerTemplateEngine.init()

        ag.setTemplateEngine(new FreemarkerTemplateEngine());
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        //自定义模板路径
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("/templates/mybatisCodeGenerator/entity.java")
                .setMapper("/templates/mybatisCodeGenerator/mapper.java")
                .setXml("/templates/mybatisCodeGenerator/mapper.xml")
                .setService("/templates/mybatisCodeGenerator/service.java")
                .setServiceImpl("/templates/mybatisCodeGenerator/serviceImpl.java")
                .setController("/templates/mybatisCodeGenerator/controller.java");

        //配置自定义模板
        ag.setTemplate(templateConfig);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/pure-module1/src/main/java");
        gc.setAuthor("lyf");
        //生成后是否打开资源管理器
        gc.setOpen(false);
        //重新生成时是否覆盖原文件
        gc.setFileOverride(true);
        //去掉service接口的首字母I
        gc.setServiceName("%Service");
        //开启实体属性Swagger2注解
        gc.setSwagger2(true);
        ag.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql:///mybatis?characterEncoding=utf-8&useSSL=false&useUnicode=true&autoReconnect=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        ag.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.lyf");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        ag.setPackageInfo(pc);


     /*   // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });*/

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 要映射的表名
        strategy.setInclude(scanner("表名，如有多个请用英文逗号分割").split(","));
        // 生成实体类时去掉表前缀_
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 表名映射到实体类名的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段名映射到实体类属性名的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 公共父类
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 生成lombok注解
        strategy.setEntityLombokModel(true);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);

        ag.setStrategy(strategy);
        ag.execute();
    }

}