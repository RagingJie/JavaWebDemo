package com.web.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching // 开启缓存
@EnableScheduling  // 开启定时任务
@SpringBootApplication
@MapperScan(basePackages = "com.web.www.mapper")
public class JavaWebApplication {

    /**
     * 启动项目
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(JavaWebApplication.class, args);
    }

}
