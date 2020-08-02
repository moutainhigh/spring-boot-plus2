package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.RoleAdmin;
import com.ws.ldy.modules.admin.model.vo.RoleAdminVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleAdminService  extends IService<RoleAdmin>{

    /**
     *   查询所有角色--用户当前拥有角色赋予checked=true
     *
     * @param userId
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 0:45
     */
    List<RoleAdminVo> findRoleChecked(String userId);


    /**
     *   修改用户角色
     *
     * @param userId
     * @param roleIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 2:25
     */
    boolean updUserRole(@RequestParam String userId, @RequestParam String[] roleIds);

}