package com.lyf;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
@MapperScan("com.lyf.mapper")
public class Module1App {

    public static void main(String[] args) {
        SpringApplication.run(Module1App.class, args);
    }

}