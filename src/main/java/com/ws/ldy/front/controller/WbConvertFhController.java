package com.ws.ldy.front.controller;

import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.mystic.Transform;
import com.ws.ldy.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO  接口管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping("/front/wbConvertFhController")
@Api(value = "wbConvertFhController", tags = "文字转符号")
public class WbConvertFhController extends BaseController {


    @ApiOperation("查询所有,跟据角色赋予选中状态")
    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = false, paramType = "query"),
            @ApiImplicitParam(name = "narrow", value = "缩小倍数（建议3或4）", required = false, paramType = "query")
    })
    public Result<String> convert(@RequestParam String content, @RequestParam Integer narrow) {
        String transform = Transform.transform(content, narrow);
        //System.out.println(transform);
        String replace = transform.replaceAll("\n", "<br>").replaceAll(" ", "&ensp;");
        return success(replace);
    }
}
//&nbsp; ：一个字符的半角的不断行的空格，如果需要在网页中插入多个空格，可以将“&nbsp;”代码写多遍；
//        &ensp; ：一个字符的半角的空格，也可以将“&ensp;”写多遍来插入多个空格；
//        &emsp;