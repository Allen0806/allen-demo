package com.allen.demo.service;

import com.allen.demo.model.DemoDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 处理服务层接口
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 */
@Validated
public interface DemoService {

    /**
     * 保存信息
     *
     * @param demoDTO 待保存的信息
     */
    void save(@NotNull(message = "信息对象不能为空") @Valid DemoDTO demoDTO);

    /**
     * 更新信息
     *
     * @param demoDTO 待更新的信息
     */
    void update(@NotNull(message = "信息对象不能为空") @Valid DemoDTO demoDTO);

    /**
     * 删除信息
     *
     * @param id 待删除的信息主键ID
     */
    void remove(@NotNull(message = "主键不能为空") @Valid Long id);

    /**
     * 获取信息
     *
     * @param id 主键ID
     * @return 获取的信息
     */
    DemoDTO get(@NotNull(message = "主键不能为空") @Valid Long id);
}
