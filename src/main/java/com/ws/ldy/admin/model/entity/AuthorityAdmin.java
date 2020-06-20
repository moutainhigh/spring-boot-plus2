package com.ws.ldy.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.admin.baseModel.BaseAdminEntity;
import lombok.Data;

/**
 * TODO  权限列表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@TableName(value = "t_admin_authority")
public class AuthorityAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 权限类Id（方法与类/层级关系展示)
     */
    private Integer pid;
    /**
     * 权限描叙
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 权限url
     */
    private String url;
    /**
     * 请求方式（请求方式(GET/POST/PUT/DELETE）
     */
    private String method;
}

