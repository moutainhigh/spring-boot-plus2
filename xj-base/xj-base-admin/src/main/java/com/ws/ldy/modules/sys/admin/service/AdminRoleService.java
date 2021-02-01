package com.ws.ldy.modules.sys.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.admin.model.dto.AdminRoleDTO;
import com.ws.ldy.modules.sys.admin.model.dto.role.RoleAuthDTO;
import com.ws.ldy.modules.sys.admin.model.dto.role.RoleMenuDTO;
import com.ws.ldy.modules.sys.admin.model.dto.role.UserRoleDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminRole;
import com.ws.ldy.modules.sys.admin.model.vo.AdminRoleVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleService extends IService<AdminRole> {


    /**
     * 添加角色-默认有所有URL 权限
     * @author wangsong
     * @param dto
     * @date 2020/9/19 0019 10:55
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean insert(@RequestBody AdminRoleDTO dto);


    /**
     *  查询用户角色| 用户当前拥有角色赋予 isChecked=true
     *
     * @param userId
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 0:45
     */
    List<AdminRoleVO> findByUserIdRoleChecked(String userId);


    /**
     * 修改用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean updUserRole(String userId, List<String> roleIds);

    /**
     * 分配用户角色
     *
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 2:25
     */
    boolean updUserRole(UserRoleDTO dto);

    /**
     *  分配角色菜单权限
     *
     * @return void
     * @date 2019/11/17 0017 2:08
     */
    boolean roleMenuAuth(RoleMenuDTO dto);


    /***
     *  分配所有角色拥有所有权限
     * @date 2019/11/18 15:45
     * @return void
     */
    boolean roleAuthAll();

    /***
     *  分配角色url权限
     * @date 2019/11/18 15:45
     * @return void
     */
    boolean roleUrlAuth(RoleAuthDTO dto);


    /**
     * 获取超管角色
     */
    AdminRole findSysRole();
}