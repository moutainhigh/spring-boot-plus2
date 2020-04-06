package com.ws.ldy.base.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO  通用Vo ,获取反序列类生成UUID
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/10/31 21:12
 * spring
 */
@Data  //set,get
@NoArgsConstructor  //无参构造
public class BaseAdminVo extends  Convert{

    @ApiModelProperty(value = "id")
    private Integer id;
}
