package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.manage.xj.model.dto.XjAdminMsgDTO;
import com.ws.ldy.manage.xj.model.query.XjAdminMsgQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminMsgVO;
import com.ws.ldy.manage.xj.service.XjAdminMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/msg")
@Api(value = "XjAdminMsgController", tags = "base-plus--消息通知")
public class XjAdminMsgController extends BaseController<XjAdminMsgService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<XjAdminMsgVO>> list(@ModelAttribute @Validated XjAdminMsgQuery query) {
        return R.success(baseService.list(query));
    }


    @PostMapping
    @ApiOperation(value = "添加/发送消息")
    public R<Boolean> insert(@RequestBody @Validated XjAdminMsgDTO dto) {
        return R.success(baseService.insertMsg(dto));
    }


    @PutMapping(value = "/{id}/read")
    @ApiOperation(value = "消息修改为已读")
    public R<Boolean> updRead(@PathVariable String id) {
        return R.successUpdate(baseService.updRead(id));
    }


    @ApiOperation(value = "查询未读数量(当前登录用户)")
    @GetMapping(value = "/findUnreadNum")
    public R<Integer> findUnreadNum() {
        return R.successFind(baseService.findUnreadNum());
    }
}
