package com.ws.ldy.modules.dev.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.dev.BaseDevEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO  开发任务
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 11:14:57
 */
@Data
@TableName("t_dev_task")
@ApiModel(value = "DevTask 对象", description = "开发任务")
public class DevTask extends BaseDevEntity {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "创建人id (保留)")
    @TableField(value = "create_user_id")
    private Integer createUserId;

    @ApiModelProperty(notes = "指派给id（保留）")
    @TableField(value = "task_user_id")
    private Integer taskUserId;

    @ApiModelProperty(notes = "任务名")
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "任务内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(notes = "任务类型(1-管理端 2-用户端 3-app端)")
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty(notes = "任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)")
    @TableField(value = "state")
    private Integer state;

    @ApiModelProperty(notes = "项目（字典code）")
    @TableField(value = "item")
    private String item;

    @ApiModelProperty(notes = "计划时间")
    @TableField(value = "planned_time")
    private LocalDateTime plannedTime;

    @ApiModelProperty(notes = "完成时间")
    @TableField(value = "ent_time")
    private LocalDateTime entTime;

    @ApiModelProperty(notes = "预计耗时(小时)")
    @TableField(value = "estimate_time")
    private Integer estimateTime;

    @ApiModelProperty(notes = "实际耗时(小时)")
    @TableField(value = "take_up_time")
    private Integer takeUpTime;

}

