package com.ws.ldy.modules.sys.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.admin.model.dto.AdminMenuDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminMenu;
import com.ws.ldy.modules.sys.admin.model.vo.AdminMenuVO;

import java.util.List;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminMenuService extends IService<AdminMenu> {


    /**
     * 添加(超管默认分配改菜单)
     * @param dto
     * @return
     */
    Boolean insert(AdminMenuDTO dto);


    /**
     *    获取导航树菜单列表
     *
     * @return
     * @date 2019/11/13 14:45
     */
    List<AdminMenuVO> getMenuTree();

    /**
     *   根据父id 查询所有子节点数据（包括自己 Tree数据）, 根据角色权限赋值isChecked = true||false
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminMenu>
     * @date 2019/11/15 16:18
     */
    List<AdminMenuVO> findPIdOrRoleIdTree(String pId, String roleId);

    /**
     *   根据父id 查询所有子节点数据（包括自己 List列表） , 根据角色权限赋值isChecked = true||false
     *
     * @param pId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminMenu>
     * @date 2019/11/15 16:18
     */
    List<AdminMenuVO> findPIdOrRoleIdList(String pId, String roleId);

}