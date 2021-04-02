package com.ws.ldy.modules.yw.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
@ApiModel(value = "SignDto 对象", description = "验签测试")
public class SignDto implements Serializable {

    private static final long serialVersionUID = 879334186471238662L;
    private Integer a;
    private Integer b;
    private Integer d;
    private Integer c;
    private Integer e;
    private LocalDateTime a1;
    // 测试下级
    private SignDto signDto;
    // 测试list下级
    private List<SignDto> dtos;
}