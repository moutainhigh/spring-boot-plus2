package com.ws.ldy.base.controller;

import com.ws.ldy.common.error.ErrorException;
import com.ws.ldy.common.result.ResultEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO  全局异常拦截---> 处理错误返回，由 ErrorPageConfig 配置拦截跳转到此类方法
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2019/11/19 9:43
 * 方式1、直接跳转到具体错误页面
 * 方式2、返回json格式数据，由前端处理
 */
@SuppressWarnings("all")
@Controller
public class ErrorController {

    /**
     * TODO  方式2：系统错误返回json
     *
     * @param code 对应错误码，ErrorPageConfig配置
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @date 2019/11/18 21:14
     */
    @RequestMapping(value = "/error/{code}")
    @ResponseBody
    public void error(@PathVariable int code) {
        //根据状态值查询对应的枚举
        ResultEnum errorConstantEnum = ResultEnum.getEnum(String.valueOf(code));
        //返回对应提示
        if (errorConstantEnum != null) {
            throw new ErrorException(errorConstantEnum);
        }
        //返回500错误
        throw new ErrorException(ResultEnum.SYS_ERROR);
    }
}
/**
 * TODO  方式1：系统错误跳指定错误页（不适用于前后端分离）
 *
 * @param code 对应错误码，ErrorPageConfig配置
 * @date 2019/11/18 21:15
 * @return
 */
//    @RequestMapping(value = "/error/{code}")
//    public String error(@PathVariable int code, Model model) {
//        String pager = "/admin/error";
//
//        switch (code) {
//            case 404:
//                model.addAttribute("code", 404);
//                pager = "/admin/404";
//                break;
//            case 500:
//                model.addAttribute("code", 500);
//                pager = "/admin/error";
//                break;
//            default:
//        }
//        return pager;
//    }
