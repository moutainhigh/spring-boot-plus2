package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.auth.util.MD5Util;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.dto.UserAdminDTO;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.model.vo.AdminUserVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.admin.service.AdminUserService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *  系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/admin/adminUser")
@Api(value = "AdminUserController", tags = "用户管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminUserController extends BaseController<AdminUserService> {


    //权限
    @Autowired
    private AdminAuthorityService adminAuthorityService;


    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation("个人信息")
    public R<AdminUserVO> findUser() {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(JwtUtil.getUserId(request.getHeader(BaseConstant.Sys.TOKEN))), AdminUserVO.class));
    }

    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<AdminUserVO>> findPage(
            @ApiParam(value = "数据Id", required = false) @RequestParam(required = false) Integer id,
            @ApiParam(value = "账号/手机号", required = false) @RequestParam(required = false) String username,
            @ApiParam(value = "姓名/用户名", required = false) @RequestParam(required = false) String fullName) {
        Page<AdminUser> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminUser>()
                .orderByDesc(AdminUser::getCreateTime)
                .eq(id != null, AdminUser::getId, id)
                .eq(StringUtils.isNotBlank(fullName), AdminUser::getFullName, fullName)
                .like(StringUtils.isNotBlank(username), AdminUser::getUsername, username)
        );
        return R.success(BeanDtoVoUtil.pageVo(page, AdminUserVO.class));
    }

    @RequestMapping(value = "/findByRoleId", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定角色的用户列表", notes = "查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
    })
    public R<List<AdminUserVO>> findByRoleId(@RequestParam String roleId) {
        return R.success(BeanDtoVoUtil.listVo(baseService.findByRoleId(roleId), AdminUserVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Void> insert(@RequestBody UserAdminDTO userAdminDto) {
        AdminUser adminUser = userAdminDto.convert(AdminUser.class);
        adminUser.setPassword(MD5Util.encode(adminUser.getPassword()));
        adminUser.setDisable(0); //默认启用状态
        adminUser.setRegTime(LocalDateTime.now());
        baseService.save(adminUser);
        return R.success();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public R<Void> upd(@RequestBody UserAdminDTO userAdminDto) {
        baseService.updateById(userAdminDto.convert(AdminUser.class));
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Void> del(@RequestParam String id) {
        baseService.removeById(id);
        return R.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes = "")
    public R<Void> delByIds(@RequestParam String[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return R.successDelete();
    }


    @RequestMapping(value = "/updByPassword", method = RequestMethod.PUT)
    @ApiOperation(value = "密码修改", notes = "判断原密码是否正确,不正确返回错误信息msg ,正确直接修改,密码进行MD5加密 --> val(前端输入密码值)+盐(后端规则指定)=最终密码）")
    public R<Void> updByPassword(@RequestParam String oldPassword, @RequestParam String password) {
        AdminUser adminUser = baseService.getById(JwtUtil.getUserId(request.getHeader(BaseConstant.Sys.TOKEN)));
        if (adminUser.getPassword().equals(MD5Util.encode(oldPassword))) {
            adminUser.setPassword(MD5Util.encode(password));
            baseService.updateById(adminUser);
            return R.successUpdate();
        } else {
            return R.error(RType.ADMIN_USER_NO_PASSWORD);
        }
    }


    /**
     *  登录
     *
     * @return com.ws.ldy.common.result.Result<com.ws.ldy.admin.model.vo.LoginVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/3/30 0030 19:50
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, paramType = "query"),
    })
    public R<Void> login(@RequestParam String username, @RequestParam String password) {
        // 1、判断账号
        AdminUser user = baseService.getOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
        if (user == null) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        // 2、判断密码
        if (!user.getPassword().equals(MD5Util.encode(password))) {
            throw new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD);
        }
        // 3、判断禁用
        if (user.getDisable() != Enums.Base.Disable.DISABLE_0.getValue()) {
            throw new ErrorException(RType.LOGIN_IS_NO_DISABLE);
        }
        // 登录成功
        // 4、获取权限列表-未禁用
        List<SimpleGrantedAuthority> auth = adminAuthorityService.findUserIdRoleAuthorityNoDisable(user.getId());
        // 5、生成jwt
        String jwtToken = JwtUtil.generateToken(user, auth);
        response.setHeader(BaseConstant.Sys.TOKEN, jwtToken);
        // 6、刷新登录时间
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(user.getId());
        updAdminUser.setEntTime(LocalDateTime.now());
        baseService.updateById(updAdminUser);
        return R.success();
    }
}
