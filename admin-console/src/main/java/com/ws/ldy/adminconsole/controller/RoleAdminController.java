package com.ws.ldy.adminconsole.controller;

import com.ws.ldy.adminconsole.controller.base.BaseAdminController;
import com.ws.ldy.adminconsole.entity.RoleAdmin;
import com.ws.ldy.admincore.controller.vo.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  角色管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@Controller
@RequestMapping("/roleAdmin")
public class RoleAdminController extends BaseAdminController {

    /***
     * TODO  分页查询
     * @param  type     =1 分页查询  =2 所有查询
     * @param  page    页数
     * @param  limit   记录数
     * @param  id-xxx  查询条件参数
     * @date 2019/11/14 15:20
     * @return Map<String, Object>
     */
    @RequestMapping("/findAll/{type}")
    @ResponseBody
    public Map<String, Object> findAll(@PathVariable Integer type,Integer page, Integer limit,Integer id) {
        if(type == 1){
            Map<String, Object> param = new HashMap<>(2);
            param.put("id",id);
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            //查询
            Page<RoleAdmin> roles = service.roleServiceImpl.page(dao.roleDao, page, limit, param, sort);
            return new Data(roles.getContent(), roles.getTotalPages()).getResData();
        }else{
            List<RoleAdmin> roles = service.roleServiceImpl.findAll(dao.roleDao);
            return new Data(roles).getResData();
        }
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param role 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(@PathVariable Integer type, RoleAdmin role) {
        if (type == 1) {
            service.roleServiceImpl.save(dao.roleDao, role);
        } else {
            service.roleServiceImpl.save(dao.roleDao, role);
        }
        return "success";
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     * @author 王松
     * @WX-QQ 1720696548
     * @date 2019/11/14 18:17
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer[] ids) {
        service.roleServiceImpl.deleteByIds(dao.roleDao, ids);
        return "success";
    }
}
