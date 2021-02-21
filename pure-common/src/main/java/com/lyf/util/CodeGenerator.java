package com.lyf.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

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
        AutoGenerator autoGenerator = new AutoGenerator();

        String projectPath = System.getProperty("user.dir");

        //自定义模板路径
        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity(projectPath + "/pure-common/src/main/resources/codeGenerator/templates/entity.java")
                .setMapper(projectPath + "/pure-common/src/main/resources/codeGenerator/templates/mapper.java")
                .setXml(projectPath + "/pure-common/src/main/resources/codeGenerator/templates/mapper.xml")
                .setService(projectPath + "/pure-common/src/main/resources/codeGenerator/templates/service.java.java")
                .setServiceImpl(projectPath + "/pure-common/src/main/resources/codeGenerator/templates/serviceImpl.java")
                .setController(projectPath + "/pure-common/src/main/resources/codeGenerator/templates/controller.java");

        //配置自定义模板
        autoGenerator.setTemplate(templateConfig);

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
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql:///mybatis?characterEncoding=utf-8&useSSL=false&useUnicode=true&autoReconnect=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.lyf");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 对应哪些表生成代码
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 公共父类
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段映射到实体属性的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 生成实体时去掉表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setEntityLombokModel(true);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();
    }

}