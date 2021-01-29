package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-31 17:41:21
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclareDTO 对象", description = "申报信息表")
public class PetsDeclareDTO extends BaseDto {

    private static final long serialVersionUID = -504558292220121097L;

//    @ApiModelProperty(notes = "申报人id" ,position = 0)
//    @Length(min=1, max=32,message = "申报人id 必须>=0 和 <=32位")
//    private String userId;

    @ApiModelProperty(notes = "宠物id" ,position = 1)
    @Length(min=1, max=32,message = "宠物id 必须>=0 和 <=32位")
    private String petId;

    @ApiModelProperty(notes = "治疗医院id" ,position = 2)
    @Length(min=1, max=32,message = "治疗医院id 必须>=0 和 <=32位")
    private String hospitalId;

//    @ApiModelProperty(notes = "实发金额" ,position = 3)
//    private BigDecimal paidInAmount;

    @ApiModelProperty(notes = "申报金额" ,position = 4)
    @DecimalMin(value = "0",message = "申报金额 必须>=0")
    @DecimalMax(value = "9999999999",message = "申报金额 必须<=9999999999")
    private BigDecimal declareMoney;

    @ApiModelProperty(notes = "报销方式=支付渠道(字典code)" ,position = 5)
    @Range(min=0, max=9L,message = "报销方式=支付渠道 必须>=0 和 <=9")
    private Integer payChannel;

//    @ApiModelProperty(notes = "申报状态(字典code 0-待审核*默认 1-已驳回 2-待付款 3-已完成)" ,position = 6)
//    @Range(min=0, max=9L,message = "申报状态 必须>=0 和 <=9")
//    private Integer state;

//    @ApiModelProperty(notes = "宠物年龄 (申请时)" ,position = 7)
//    @Range(min=0, max=999L,message = "宠物年龄  必须>=0 和 <=999")
//    private Integer petSex;
//
//    @ApiModelProperty(notes = "报销次数 (申请时)" ,position = 8)
//    @Range(min=0, max=999L,message = "报销次数  必须>=0 和 <=999")
//    private Integer declareNum;
//
//    @ApiModelProperty(notes = "累积缴费金额 (申请时)" ,position = 9)
//    @DecimalMin(value = "0",message = "累积缴费金额  必须>=0")
//    @DecimalMax(value = "9999999999",message = "累积缴费金额  必须<=9999999999")
//    private BigDecimal payMoney;
//
//    @ApiModelProperty(notes = "保单加入时间（申请时）" ,position = 10)
//    private LocalDateTime applyTime;
//
//    @ApiModelProperty(notes = "剩余天数（申请时）" ,position = 11)
//    @Range(min=0, max=1215752191L,message = "剩余天数（申请时） 必须>=0 和 <=1215752191")
//    private Integer expirationDayNum;
//
//    @ApiModelProperty(notes = "帮助次数( 申请时)" ,position = 12)
//    @Range(min=0, max=1215752191L,message = "帮助次数 必须>=0 和 <=1215752191")
//    private Integer helpNum;
//
//    @ApiModelProperty(notes = "加入天数（申请时）" ,position = 13)
//    @Range(min=0, max=1215752191L,message = "加入天数（申请时） 必须>=0 和 <=1215752191")
//    private Integer joinDayNum;

    //    @ApiModelProperty(notes = "申报人id" ,position = 0)
//    @Length(min=1, max=32,message = "申报人id 必须>=0 和 <=32位")
//    private String userId;


    @ApiModelProperty(notes = "生病记录图" ,position = 14)
    private String sickRecordPic;

    @ApiModelProperty(notes = "费用明细图" ,position = 15)
    private String expenseDetailsPic;

    @ApiModelProperty(notes = "费用发票图" ,position = 16)
    private String expenseInvoicePic;

    @ApiModelProperty(notes = "病因" ,position = 17)
    private String pathogeny;

//    @ApiModelProperty(notes = "审核备注( 多次审核 | 线分隔, 保留之前审核记录)" ,position = 18)
//    private String remarks;

}
