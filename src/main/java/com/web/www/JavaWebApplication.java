package com.web.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling  // 开启定时任务
@SpringBootApplication
@MapperScan(basePackages = "com.web.www.mapper")
public class JavaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebApplication.class, args);
	}

}
