package com.allen.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统启动类
 *
 * @author luoxuetong
 * @date 2020年5月8日
 * @since 1.0.0
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@ComponentScan(basePackages = "com.lczq")
@MapperScan(basePackages = "com.lczq.**.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}
