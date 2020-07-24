package com.ws.ldy.others.base.model.dev;

import com.ws.ldy.others.base.model.convert.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * TODO  通用Entity ,获取反序列类生成UUID
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 * spring
 */
@Data  //set,get
@NoArgsConstructor  //无参构造
public class BaseDevDto extends Convert {

    @ApiModelProperty(value = "id--> 添加不传，修改必传")
    private Integer id;
}
