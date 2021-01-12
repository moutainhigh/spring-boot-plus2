package com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContactInfoList {
    /**
     * 地址类型：
     * 1，寄件方信息 2，到件方信息
     *
     * 是
     */
    private int contactType;
    /**
     * 国家或地区 2位代码
     * 参照附录国家代码附件 --   中国：CN
     *
     * 是
     */
    private String country;
    /**
     * 联系人
     *
     * 条件
     */
    private String contact;
    /**
     * 联系电话
     *
     * 条件
     */
    private String tel;
    /**
     * 详细地址，若province/city
     * 字段的值不传，此字段必须包
     * 含省市信息，避免影响原寄地
     * 代码识别，如：广东省深圳市
     * 福田区新洲十一街万基商务大
     * 厦10楼；若需要生成电子运
     * 单，则为必填
     */
    private String address;
}
