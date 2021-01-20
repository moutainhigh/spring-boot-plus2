/**
  * Copyright 2020 bejson.com 
  */
package com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.base;

import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ApiResultData extends Convert {

    private boolean success;
    private String errorCode;
    private String errorMsg;
    private String msgData;

}