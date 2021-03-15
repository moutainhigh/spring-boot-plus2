package com.ws.ldy.modules.sys.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.sys.admin.model.vo.AdminAuthorityVO;

import java.util.List;

/**
 *   Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
public interface AdminAuthorityService extends IService<AdminAuthority> {

    /**
     * 查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1 （list数据）
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AdminAuthorityVO> findList();


    /**
     * 编辑
     * @author wangsong
     * @param dto
     */
    Boolean upd(AdminAuthorityDTO dto);

    /**
     * 接口自动扫描（1、项目启动时自动执行   2、设置了权限授权状态更新）
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    Boolean refreshAuthDB();


    /**
     * 获取用户的url权限列表，给指定角色的有的权限数据赋予选中状态(list数据)
     *
     * @param roleId 角色id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AdminAuthorityVO> findByRoleIdAuthorityChecked(String roleId);

    /**
     * 获取用户的url权限列表，给指定角色的有的权限数据赋予选中状态(tree数据)
     *
     * @param roleId 角色id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AdminAuthorityVO> findByRoleIdAuthorityTreeChecked(String roleId);


    /**
     * 获取用户的url权限列表，只返回未禁用的 url
     *
     * @param  userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<String> findByUserIdaAndDisableFetchAuthority(String userId);

    /**
     * 刷新接口
     *
     * @param
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    public void refreshAuthCache();


    /**
     * 子级找父级
     *
     * @param
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    public AdminAuthority findFatherAuth(String uri);

}
