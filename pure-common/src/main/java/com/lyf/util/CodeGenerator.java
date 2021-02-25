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

    private static String url = "jdbc:mysql:///mybatis?characterEncoding=utf-8&useSSL=false&useUnicode=true&autoReconnect=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String password = "root";
    private static DbType dbType = DbType.MYSQL;

    private static String parentPackage = "com.lyf";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append("请输入" + tip + "：");
        System.out.println(sb.toString());
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

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        //输出文件夹
        gc.setOutputDir(System.getProperty("user.dir") + "/pure-module1/src/main/java");
        //设置作者名
        gc.setAuthor("lyf");
        //生成后是否打开资源管理器--否
        gc.setOpen(false);
        //重新生成时是否覆盖原文件--是
        gc.setFileOverride(true);
        //去掉service接口的首字母I
        gc.setServiceName("%Service");
        //开启实体属性Swagger2注解
        gc.setSwagger2(true);
        ag.setGlobalConfig(gc);

        //模板配置
        //使用freemarker模板引擎
        ag.setTemplateEngine(new FreemarkerTemplateEngine());
        //自定义模板路径
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("/templates/mybatisCodeGenerator/entity.java")
                .setMapper("/templates/mybatisCodeGenerator/mapper.java")
                .setXml("/templates/mybatisCodeGenerator/mapper.xml")
                .setService("/templates/mybatisCodeGenerator/service.java")
                .setServiceImpl("/templates/mybatisCodeGenerator/serviceImpl.java")
                .setController("/templates/mybatisCodeGenerator/controller.java");
        ag.setTemplate(templateConfig);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setDbType(dbType);
        ag.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(parentPackage);
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

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //要映射的表名
        strategy.setInclude(scanner("表名，如有多个请用英文逗号分割").split(","))
                //去掉表前缀_
                .setTablePrefix(pc.getModuleName() + "_");
        //表名生成策略：下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //表字段生成策略：下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //公共父类
//        strategy.setSuperEntityClass("com.apa7.ms.common.core.entity.BaseEntity");
//        strategy.setSuperMapperClass("com.apa7.ms.common.service.mapper.BaseMp");
//        strategy.setSuperServiceClass("com.apa7.ms.common.core.service.BaseService");
//        strategy.setSuperServiceImplClass("com.apa7.ms.common.service.service.impl.BaseServiceImpl");
//        strategy.setSuperControllerClass("");
        //实体类生成lombok注解
        strategy.setEntityLombokModel(true);
        //restful api风格控制器
//        strategy.setRestControllerStyle(false);
        strategy.setChainModel(true);
        // 生成实体类字段注解
//        strategy.setVersionFieldName("version");
//        strategy.setLogicDeleteFieldName("deleted");
//        strategy.setTableFillList(Arrays.asList(
//                new TableFill("update_time", FieldFill.UPDATE),
//                new TableFill("create_time", FieldFill.INSERT)
//        ));
        ag.setStrategy(strategy);
        ag.execute();

    }

}