package com.allen.demo.fallback;

import com.allen.demo.feign.DemoClient;
import com.allen.demo.model.DemoDTO;
import com.lczq.tool.result.BaseResult;
import com.lczq.tool.result.ResultStatus;

/**
 * Hystrix限流处理类，暂不使用
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 */
//@Component
public class DemoFallback implements DemoClient {

    @Override
    public BaseResult<DemoDTO> get(Long id) {
        return new BaseResult<DemoDTO>(ResultStatus.SYSTEM_ERROR.getCode(), "请求失败");
    }
}