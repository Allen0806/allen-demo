package com.allen.demo.model;

import com.lczq.tool.validation.ValidationGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * demo数据传输对象
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 */
@ApiModel("信息数据传输类")
public class DemoDTO implements java.io.Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = -2636190666130690460L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空", groups = {ValidationGroup.Update.class})
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称，最长20位", required = true)
    @NotNull(message = "名称不能为空", groups = {ValidationGroup.Insert.class})
    @Size(max = 20, message = "名称最长20位")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
