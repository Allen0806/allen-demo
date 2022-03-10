package com.allen.demo.constant;

import com.lczq.tool.result.ResultStatus;

/**
 * 系统状态码定义常量类
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 *
 */
public class ResultStatuses {

	/*************** 业务线管理状态码定义 ***************/
	public static final ResultStatus LD_0101 = new ResultStatus("LD0101", "保存信息失败");
	public static final ResultStatus LD_0102 = new ResultStatus("LD0102", "更新信息失败");
	public static final ResultStatus LD_0103 = new ResultStatus("LD0103", "获取信息失败");
}
