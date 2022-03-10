package com.allen.demo.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Spring Boot ThreadPoolTaskExecutor 配置类
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 *
 */
@Configuration
@EnableAsync // 开启异步方法
public class ThreadPoolConfig {

	/**
	 * 线程池，需要重新定义名字
	 * 
	 * @return 线程池对象
	 */
	@Bean("commonExecutor")
	public ThreadPoolTaskExecutor commonExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		// 设置线程池参数信息
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setQueueCapacity(200);
		taskExecutor.setKeepAliveSeconds(10);
		taskExecutor.setThreadNamePrefix("common-");
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setAwaitTerminationSeconds(60);
		// 修改拒绝策略为使用当前线程执行
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		// 初始化线程池
		taskExecutor.initialize();
		return taskExecutor;
	}

}
