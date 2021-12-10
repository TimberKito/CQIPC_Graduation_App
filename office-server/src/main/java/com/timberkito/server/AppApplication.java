package com.timberkito.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Timber.Wang
 * @date 2021/12/10 20:25
 */
@SpringBootApplication
@MapperScan("com.timberkito.server.mapper")
public class AppApplication{
    public static void main (String[] args){
        SpringApplication.run(AppApplication.class,args);
    }
}
