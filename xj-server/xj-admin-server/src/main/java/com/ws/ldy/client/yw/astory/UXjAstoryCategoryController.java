package com.ws.ldy.client.yw.astory;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.astory.model.vo.XjAstoryCategoryVO;
import com.ws.ldy.modules.yw.astory.service.XjAstoryCategoryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 段子分类表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-20 22:04:03
 */
@RestController
@RequestMapping("/api/consumer/astory/xjAstoryCategory")
@Api(value ="UXjAstoryCategoryController" ,tags = "独立功能--段子分类(用户端)")
public class UXjAstoryCategoryController extends BaseController<XjAstoryCategoryService> {



    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes= "")
    public R<List<XjAstoryCategoryVO>> findList() {
        return R.successFind(BeanDtoVoUtil.listVo( baseService.list(),XjAstoryCategoryVO.class));
    }


}