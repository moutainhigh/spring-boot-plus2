package com.ws.ldy.client.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.xj.model.entity.XjAdminBanner;
import com.ws.ldy.manage.xj.model.vo.XjAdminBannerVO;
import com.ws.ldy.manage.xj.service.XjAdminBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/xj/banner")
@Api(value = "UXjAdminBannerController", tags = "yh--base-plus--banner")
public class UXjAdminBannerController extends BaseController<XjAdminBannerService> {


    @RequestMapping(value = "/list/{position}", method = RequestMethod.GET)
    @ApiOperation(value = "列表-位置查询")
    @ApiImplicitParam(name = "position", value = "位置(字典code)", required = true, paramType = "path", example = "")
    public R<List<XjAdminBannerVO>> findPage(@PathVariable Integer position) {
        List<XjAdminBanner> list = baseService.list(new LambdaQueryWrapper<XjAdminBanner>()
                .orderByAsc(XjAdminBanner::getSort)
                .orderByDesc(XjAdminBanner::getCreateTime)
                .eq(XjAdminBanner::getDisable, Base.Disable.V0.getValue())
                .eq(XjAdminBanner::getPosition, position)
        );
        return R.successFind(BeanDtoVoUtil.listVo(list, XjAdminBannerVO.class));
    }
}
