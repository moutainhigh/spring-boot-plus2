package com.ws.ldy.modules.business.file.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 常用工具文件管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-17 16:21:46
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_file")
@ApiModel(value = "XjFile 对象", description = "常用工具文件管理")
public class XjFile extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /** 
     * 文件名(标题) 
     */
    @TableField(value = "`name`")
    private String name;

    /** 
     * 文件描叙 
     */
    @TableField(value = "`desc`")
    private String desc;

    /** 
     * 文件大小 
     */
    @TableField(value = "size")
    private Double size;

    /** 
     * 文件格式(后缀) 
     */
    @TableField(value = "suffix")
    private String suffix;

    /** 
     * 文件url 
     */
    @TableField(value = "url")
    private String url;

    /** 
     * 文件类型(1-开发工具 2-源码  3-文档   4-图片 5-音频 6-视频 7-sql) 
     */
    @TableField(value = "type")
    private Integer type;

}

