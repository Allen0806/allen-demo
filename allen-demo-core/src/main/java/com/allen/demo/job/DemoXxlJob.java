package com.allen.demo.job;

import com.allen.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

/**
 * 定时任务
 * 
 * @author luoxuetong
 * @date 2020年11月23日
 * @since 1.0.0
 */
@Component
public class DemoXxlJob {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoXxlJob.class);

	/**
	 * 处理服务
	 */
	@Autowired
	private DemoService demoService;

	/**
	 * 任务
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@XxlJob("demoJobHandler")
	public ReturnT<String> demoJobHandler(String param) throws Exception {
		demoService.get(1L);
		LOGGER.info("定时任务执行成功");
		return ReturnT.SUCCESS;
	}
}
