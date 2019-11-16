package com.ws.ldy.adminconsole.dao;

import com.ws.ldy.adminconsole.entity.RoleMenuAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuAdminDao extends BaseDao<RoleMenuAdmin, Integer> {

    /**
     * TODO    查询角色所有权限
     * @param roleId
     * @date  2019/11/16 0016 23:00
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     */
    @Query(value = "select * from t_admin_role_menu where role_id = ?1",nativeQuery = true)
    List<RoleMenuAdmin> findRoleId(Integer roleId);
}
