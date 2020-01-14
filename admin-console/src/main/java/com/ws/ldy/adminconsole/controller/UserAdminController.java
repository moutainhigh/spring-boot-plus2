package com.ws.ldy.adminconsole.controller;

import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.UserAdminServiceImpl;
import com.ws.ldy.admincore.annotation.LdyAuthority;
import com.ws.ldy.admincore.controller.base.BaseController;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@Controller
@RequestMapping("/userAdmin")
@LdyAuthority(value = {"userAdmin", "系统用户"})
public class UserAdminController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;

    /***
     * TODO  分页查询
     * @param   type 1 = 用户列表查询  2=角色用户分配查询
     * @date 2019/11/14 15:20
     * @return Map<String, Object>
     */
    @RequestMapping("/findAll/{type}")
    @ResponseBody
    @LdyAuthority(value = {"user:findAll", "查询"})
    public ResponseData findAll(@PathVariable Integer type, int page, int limit, Integer id, Integer roleId) {
        Map<String, Object> param = new HashMap<>(2);
        Page<UserAdmin> userPages = null;
        param.put("id", id);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        if (type == 1) {
            //查询所有
            userPages = userAdminServiceImpl.page(page, limit, param, sort);
            return ResponseData.success(userPages.getContent(), userPages.getTotalPages());
        } else {
            //查询所有
            userPages = userAdminServiceImpl.page(page, 999, param, sort);
            //角色选中状态处理
            List<UserAdmin> users = userPages.getContent();
            users = roleUserAdminServiceImpl.RoleUserChecked(users, roleId);
            return ResponseData.success(users, userPages.getTotalPages());
        }
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param user 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    @LdyAuthority(value = {"user:save", "添加/修改"})
    public String save(@PathVariable Integer type, UserAdmin user) {
        if (type == 1) {
            user.setTime(new Date());
            userAdminServiceImpl.save(user);
        } else {
            userAdminServiceImpl.save(user);
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
    @LdyAuthority(value = {"user:delete", "删除"})
    public String delete(Integer[] ids) {
        userAdminServiceImpl.deleteByIds(ids);
        return "success";
    }


    /***
     * TODO  账号登录
     * @param account
     * @param password
     * @date 2019/11/18 10:13
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping("/login")
    @LdyAuthority(value = {"user:login", "登录"})
    public String login(String account, String password) {
        UserAdmin user = userAdminServiceImpl.findAccountPwd(account, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "success";
        } else {
            return "no";
        }
    }


    /***
     * TODO  密码修改
     * @param password
     * @date 2019/11/18 10:13
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping("/updPwd")
    @LdyAuthority(value = {"user:updPwd", "密码修改"})
    public String updPwd(String oldPassword, String password) {
        UserAdmin user = (UserAdmin) session.getAttribute("user");
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(password);
            userAdminServiceImpl.save(user);
            return "success";
        } else {
            return "no";
        }
    }
}
