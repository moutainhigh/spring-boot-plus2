package com.ws.ldy.modules.admin.model.vo;

import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminRoleVO", description = "角色表")
public class AdminRoleVO extends BaseVo {

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "描叙")
    private String desc;

    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;

    @ApiModelProperty(value = "查询code")
    private String code;

    @ApiModelProperty(value = "角色分配, 当前用户拥有角色为true")
    private Boolean isChecked;

}

