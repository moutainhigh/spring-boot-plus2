package com.ws.ldy.modules.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.modules.dev.model.dto.DevNormDTO;
import com.ws.ldy.modules.dev.model.entity.DevNorm;
import com.ws.ldy.modules.dev.model.vo.DevNormVO;
import com.ws.ldy.modules.dev.service.DevNormService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * TODO  开发规范
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 12:22:53
 */
@RestController
@RequestMapping("/dev/devNorm")
@Api(value ="DevNorm" ,tags = "开发规范",description = "开发规范")
public class DevNormController extends BaseController<DevNormService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query",example = "20")
    })
    public Result<IPage<DevNormVO>> findPage( 
            @ApiParam(value = "规范名称",required = false) @RequestParam(required = false) String name) {
        Page<DevNorm> page = baseService.page(this.getPage(), new LambdaQueryWrapper<DevNorm>()
                .orderByDesc(DevNorm::getCreateTime)
                .eq(StringUtils.isNotBlank(name),DevNorm::getName,name)

        );
        return Result.successFind(BeanDtoVoUtil.pageVo(page, DevNormVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody @Validated DevNormDTO dto) {
        DevNorm devNorm = dto.convert(DevNorm.class);
        baseService.save(devNorm);
        return Result.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public Result<Void> update(@RequestBody @Validated DevNormDTO dto) {
        baseService.updateById(dto.convert(DevNorm.class));
        return Result.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation("单删除")
    public Result<Void> delete(@RequestParam Integer id) {
        baseService.removeById(id);
        return Result.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation("批量删除")
    public Result<Void> deleteByIds(@RequestParam Integer[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return Result.successDelete();
    }
}
