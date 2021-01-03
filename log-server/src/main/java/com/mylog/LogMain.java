package com.mylog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mylog.mapper"})
public class LogMain {
    public static void main(String[] args) {
        SpringApplication.run(LogMain.class, args);
    }
}
