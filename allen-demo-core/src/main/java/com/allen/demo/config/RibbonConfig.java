package com.allen.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon配置类
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 *
 */
@Configuration
public class RibbonConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
