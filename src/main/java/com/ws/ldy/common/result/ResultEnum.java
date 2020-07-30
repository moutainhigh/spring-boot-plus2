package com.ws.ldy.common.result;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 异常常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 11:16
 * @return
 */

@Getter
@NoArgsConstructor
public enum ResultEnum implements IEnum {
    //--------------------------请求相关错误 --------------------------
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(9001, "通常是参数错误导致jackson反序列化失败异常"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(9002, "请求方式错误"),


    //--------------------------系统相关错误- SYS_ --------------------------
    SYS_SUCCESS(200, "成功"),
    SYS_SUCCESS_FIND(200, "查询成功"),
    SYS_SUCCESS_INSERT(200, "添加成功"),
    SYS_SUCCESS_UPDATE(200, "编辑成功"),
    SYS_SUCCESS_DELETE(200, "删除成功"),
    SYS_OPERATE_ERROR(500, "服务器错误"),

    SYS_ERROR_CODE_400(400, "400错误"),
    SYS_ERROR_CODE_401(401, "401-禁止非法访问"),
    SYS_ERROR_CODE_403(403, "403-无权限访问"),
    SYS_ERROR_CODE_404(404, "404错误"),
    SYS_ERROR_CODE_415(415, "415错误"),
    SYS_ERROR_CODE_500(500, "系统错误"),


    // 后台管理相关错误-ADMIN_
    SYS_SOCKET_CONFIG_ERROR(9999, "请检查socket配置信息是否配置"),
    SYSTEM_VALID_ILLEGAL_PARAM(9999, "参数错误:不符合JSR 303规范"),
    SYSTEM_PARAMETER_ILLEGAL_PARAM(9999, "参数错误:JSON格式错误/或枚举参数错误"),
    SYSTEM_PARAMETER_WRONG_TYPE(9999, "参数错误:类型不匹配"),

    ADMIN_IS_NO_INSERT_ID(9999, "添加不能传递Id"),
    ADMIN_IS_NO_UPDATE_ID(9999, "编辑不能没有Id"),

    // 10000前端跳转登录页
    ADMIN_IS_NO_LOGIN(10000, "用户未登陆/或登录过期"),
    ADMIN_IS_NO_TOKEN(10000, "没有TOKEN信息"),


    //
    ADMIN_OSS_NO_PATH(9998, "路径错误或文件不存在"),
    ADMIN_IS_NO_MENU(9998, "当前用户没有任何菜单权限"),

    //

    //--------------------------前端API相关错误-API_ --------------------------
    API_IS_NO_LOGIN(20000, "用户未登陆"),
    API_IS_NO_INSERT_ID(20001, "添加不能传递Id"),
    API_IS_NO_UPDATE_ID(20002, "编辑不能没有Id"),
    API_IS_NO_TOKEN(20003, "没有TOKEN信息"),
    ;


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public Serializable getValue() {
        return code;
    }
}