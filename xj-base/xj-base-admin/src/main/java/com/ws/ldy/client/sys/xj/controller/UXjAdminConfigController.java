package com.ws.ldy.client.sys.xj.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminConfig;
import com.ws.ldy.modules.sys.xj.model.vo.XjAdminConfigVO;
import com.ws.ldy.modules.sys.xj.service.XjAdminConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient  + "/xj/adminConfig")
@Api(value = "XjAdminConfigController", tags = "yh--base-plus--全局配置")
public class UXjAdminConfigController extends BaseController<XjAdminConfigService> {


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "CODE查询", notes = "")
    @ApiImplicitParam(name = "code", value = "配置code|搜索值(不能重复)", required = false, paramType = "query", example = "")
    public R<XjAdminConfigVO> findByCode(@RequestParam String code) {
        XjAdminConfig xjAdminConfig = baseService.findByCode(code);
        return R.successFind(BeanDtoVoUtil.convert(xjAdminConfig, XjAdminConfigVO.class));
    }
}
