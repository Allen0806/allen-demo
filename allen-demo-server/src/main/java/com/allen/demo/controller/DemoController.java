package com.allen.demo.controller;

import com.allen.demo.model.DemoDTO;
import com.allen.demo.service.DemoService;
import com.lczq.tool.result.BaseResult;
import com.lczq.tool.validation.ValidationGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * Controller层
 *
 * @author luoxuetong
 * @date 2020年5月12日
 * @since 1.0.0
 */
@Api("API接口")
@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    /**
     * 处理服务
     */
    @Autowired
    private DemoService demoService;

    /**
     * 保存信息
     *
     * @param demoDTO 信息对象
     * @return 响应对象
     */
    @ApiOperation("保存信息")
    @PostMapping(value = "/save")
    public BaseResult<Object> save(@NotNull(message = "信息不能为空") @Validated({ValidationGroup.Insert.class,
            Default.class}) @RequestBody DemoDTO demoDTO) {
        demoService.save(demoDTO);
        return BaseResult.success();
    }

    /**
     * 更新信息
     *
     * @param demoDTO
     * @return
     */
    @ApiOperation("更新信息")
    @PostMapping(value = "/update")
    public BaseResult<Object> update(@NotNull(message = "信息不能为空") @Validated({ValidationGroup.Update.class,
            Default.class}) @RequestBody DemoDTO demoDTO) {
        demoService.update(demoDTO);
        return BaseResult.success();
    }

    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除信息")
    @PostMapping(value = "/remove/{id}")
    public BaseResult<Object> remove(@NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        demoService.remove(id);
        return BaseResult.success();
    }

    /**
     * 获取信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取信息")
    @PostMapping(value = "/query/{id}")
    public BaseResult<DemoDTO> get(@NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        DemoDTO demoDTO = demoService.get(id);
        return BaseResult.success(demoDTO);
    }
}
