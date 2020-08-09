package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleMapper;
import com.ws.ldy.modules.admin.model.entity.AdminRole;
import com.ws.ldy.modules.admin.model.entity.AdminRoleAuth;
import com.ws.ldy.modules.admin.model.entity.AdminRoleMenu;
import com.ws.ldy.modules.admin.model.entity.AdminRoleUser;
import com.ws.ldy.modules.admin.model.vo.AdminRoleVO;
import com.ws.ldy.modules.admin.service.AdminRoleAuthService;
import com.ws.ldy.modules.admin.service.AdminRoleMenuService;
import com.ws.ldy.modules.admin.service.AdminRoleService;
import com.ws.ldy.modules.admin.service.AdminRoleUserService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class AdminRoleServiceImpl extends BaseIServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleUserService roleUserAdminService;

    @Autowired
    private AdminRoleMenuService adminRoleMenuService;

    @Autowired
    private AdminRoleAuthService adminRoleAuthService;

    /**
     * 查询用户角色| 用户当前拥有角色赋予 isChecked=true
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/9 0009 9:44
     * @version 1.0.0
     */
    @Override
    public List<AdminRoleVO> findByUserIdRoleChecked(String userId) {
        //TODO 代码不够完美，代优化
        //查询所有角色
        List<AdminRole> roles = this.list();
        //查询用户当前角色
        List<AdminRoleUser> roleUsers = roleUserAdminService.list(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
        Map<String, String> roleUserMap = new HashMap<>();
        roleUsers.forEach(item -> roleUserMap.put(item.getRoleId(), "0"));
        //返回数据
        List<AdminRoleVO> adminRoleVOList = new ArrayList<>();
        roles.forEach(role -> {
            AdminRoleVO roleVo = role.convert(AdminRoleVO.class);
            if (roleUserMap.containsKey(role.getId())) {
                roleVo.setIsChecked(true);
            } else {
                roleVo.setIsChecked(false);
            }
            adminRoleVOList.add(roleVo);
        });
        return adminRoleVOList;
    }


    /**
     * 分配用户的角色
     * @author wangsong
     * @param userId
     * @param roleIds
     * @date 2020/8/9 0009 9:41
     * @return boolean
     * @version 1.0.0
     */
    @Override
    @Transactional
    public boolean updUserRole(String userId, String[] roleIds) {
        //删除原角色所有权限数据
        boolean result = roleUserAdminService.remove(new QueryWrapper<AdminRoleUser>().eq("user_id", userId));
        if (roleIds == null || roleIds.length <= 0) {
            return true;
        }
        List<AdminRoleUser> roleUserList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            roleUserList.add(new AdminRoleUser(roleIds[i], userId));
        }
        return roleUserAdminService.saveBatch(roleUserList, 1024);
    }


    /**
     *  分配角色菜单权限
     *
     * @param roleId
     * @param menuIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 17:47
     */
    @Override
    public boolean roleMenuAuth(String roleId, String[] menuIds) {
        // 删除当前角色所有菜单权限
        boolean result = adminRoleMenuService.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, roleId));
        if (menuIds == null || menuIds.length <= 0) {
            return true;
        }
        List<AdminRoleMenu> addRoleMenu = new LinkedList<>();
        for (int i = 0; i < menuIds.length; i++) {
            addRoleMenu.add(new AdminRoleMenu(roleId, menuIds[i]));
        }
        return adminRoleMenuService.saveBatch(addRoleMenu, 1024);
    }


    /**
     *  分配角色url权限
     *
     * @param roleId
     * @param authIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 17:47
     */
    @Override
    public boolean roleUrlAuth(String roleId, String[] authIds) {
        //删除原数据
        boolean result = adminRoleAuthService.remove(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, roleId));
        if (authIds == null || authIds.length <= 0) {
            return true;
        }
        List<AdminRoleAuth> roleAuthList = new ArrayList<>();
        for (int i = 0; i < authIds.length; i++) {
            roleAuthList.add(new AdminRoleAuth(authIds[i], roleId));
        }
        return adminRoleAuthService.saveBatch(roleAuthList, 1024);
    }

}