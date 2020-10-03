/**
  * Copyright 2020 bejson.com 
  */
package com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.response;

import com.ws.ldy.others.base.model.Convert;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class RouteLabelInfo extends Convert {

    private String code;
    private RouteLabelData routeLabelData;
    private String message;


}