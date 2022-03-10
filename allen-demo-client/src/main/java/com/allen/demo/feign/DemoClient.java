package com.allen.demo.feign;

import com.allen.demo.fallback.DemoFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.allen.demo.model.DemoDTO;
import com.lczq.tool.result.BaseResult;

/**
 * Feign Client，供外部系统集成使用
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 */
@FeignClient(name = "lczq-demo-server", path = "/demo/demo", fallback = DemoFallback.class)
public interface DemoClient {

    /**
     * Feign Client接口
     *
     * @param id 主键ID
     * @return 接收结果
     */
    @PostMapping(value = "/query/{id}", headers = {"Content-Type=application/json"})
    BaseResult<DemoDTO> get(@PathVariable("id") Long id);
}