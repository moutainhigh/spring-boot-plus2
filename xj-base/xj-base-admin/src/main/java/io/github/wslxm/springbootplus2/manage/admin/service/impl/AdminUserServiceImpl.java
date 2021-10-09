package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.manage.admin.service.*;
import io.github.wslxm.springbootplus2.core.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.core.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.auth.util.MD5Util;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.cache.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Admin;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminUserMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminUserDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleUser;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminUser;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminUserQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl extends BaseIServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRoleUserService adminRoleUserService;

    @Autowired
    private AdminAuthorityService adminAuthorityService;

    @Autowired
    private XjAdminConfigService xjAdminConfigService;

    @Autowired
    private AdminOrganService adminOrganService;


    @Override
    public IPage<AdminUserVO> list(AdminUserQuery query) {
        // 是否只查询当前登录人创建的用户
        String createUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        if (query.getCurrent() <= 0) {
            // list
            IPage<AdminUserVO> page = new Page<>();
            return page.setRecords(baseMapper.list(null, query, createUserId));
        } else {
            // page
            IPage<AdminUserVO> page = new Page<>(query.getCurrent(), query.getSize());
            return page.setRecords(baseMapper.list(page, query, createUserId));
        }
    }

    @Override
    @Transactional
    public String insert(AdminUserDTO dto) {
        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), null, dto.getTerminal(), null);
        this.verifyRepeatePhone(dto.getPhone(), null, dto.getTerminal(), null);
        //
        AdminUser adminUser = dto.convert(AdminUser.class);
        adminUser.setPassword(MD5Util.encode(adminUser.getPassword()));
        adminUser.setRegTime(LocalDateTime.now());
        if (dto.getDisable() == null) {
            // 如果未设置状态,默认启用状态
            adminUser.setDisable(Base.Disable.V0.getValue());
        }
        // 判断当前是否登录(登录了添加创建人)
        R<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request, response);
        Boolean isLogin = jwtUserR.getCode().equals(RType.SYS_SUCCESS.getValue()) ? true : false;
        if (isLogin) {
            String userId = jwtUserR.getData().getUserId();
            adminUser.setCreateUser(userId);
        }
        this.save(adminUser);
        if (dto.getRoleIds() != null) {
            //分配角色
            adminRoleService.updUserRole(adminUser.getId(), dto.getRoleIds());
        }
        return adminUser.getId();
    }

    @Override
    @Transactional
    public Boolean upd(String id, AdminUserDTO dto) {
        AdminUser adminUser = this.getOne(new LambdaQueryWrapper<AdminUser>()
                .select(AdminUser::getUsername, AdminUser::getPhone, AdminUser::getTerminal)
                .eq(AdminUser::getId, id));

        // 判重账号/判重电话
        this.verifyRepeatUsername(dto.getUsername(), adminUser.getUsername(), dto.getTerminal(), adminUser.getTerminal());
        this.verifyRepeatePhone(dto.getPhone(), adminUser.getPhone(), dto.getTerminal(), adminUser.getTerminal());
        //
        AdminUser entity = dto.convert(AdminUser.class);
        entity.setId(id);
        this.updateById(entity);
        // 角色信息重分配
        if (dto.getRoleIds() != null && dto.getRoleIds().size() > 0) {
            adminRoleService.updUserRole(id, dto.getRoleIds());
        }
        return true;
    }

    @Override
    public Boolean del(String userId) {
        adminRoleUserService.remove(new LambdaUpdateWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
        return this.removeById(userId);
    }

    @Override
    public AdminUserVO findId(String id) {
        // id查询数据
        AdminUserQuery query = new AdminUserQuery();
        query.setId(id);
        IPage<AdminUserVO> list = this.list(query);
        if (list.getRecords().size() == 0) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), RType.PARAM_ERROR.getMsg() + ":id");
        }
        AdminUserVO userVO = list.getRecords().get(0);

        // 角色id组装便于角色回显
        userVO.setRoleIds(userVO.getRoles() == null ? null : userVO.getRoles().stream().map(AdminRoleVO::getId).collect(Collectors.toList()));
        // 公司/部门信息
        userVO.setOrgan(adminOrganService.fingNextOrgans(userVO.getOrganId()));
        return userVO;
    }

    @Override
    public List<AdminUser> findByRoleId(String roleId) {
        return baseMapper.findByRoleId(roleId);
    }


    @Override
    public List<AdminUserVO> listKeyData(String searchName, String terminal) {
        List<AdminUser> list = this.list(new LambdaQueryWrapper<AdminUser>()
                .select(AdminUser::getUsername, AdminUser::getFullName, AdminUser::getPhone, AdminUser::getId)
                .eq(terminal != null, AdminUser::getTerminal, terminal)
                .orderByDesc(AdminUser::getCreateTime)
                .and(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(searchName),
                        i -> i.like(AdminUser::getFullName, searchName)
                                .or().like(AdminUser::getUsername, searchName)
                )
        );
        return BeanDtoVoUtil.listVo(list, AdminUserVO.class);
    }


    @Override
    public Boolean login(String username, String password, Integer terminal) {
        AdminUser user = loginUsernameOrPhone(username, password, terminal);
        // 登录成功
        // 4、获取权限列表,保存权限-未禁用,管理端(登录+认证的)
        List<String> authList = adminAuthorityService.findByUserIdAuthority(user.getId());
        // 获取登录token 有效期
        XjAdminConfigVO configVO = xjAdminConfigService.findByCode(ConfigCacheKey.MANAGE_LOGIN_EXPIRATION);
        Integer expiration = configVO != null ? Integer.parseInt(configVO.getContent()) : 60;

        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setTerminal(user.getTerminal());
        jwtUser.setType(JwtUtil.userType[0]);
        // 设置token有效期(分)
        jwtUser.setExpiration(expiration);
        // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
        jwtUser.setAuthList(authList);
        JwtUtil.createToken(jwtUser, response);
        // 6、刷新最后登录时间
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(user.getId());
        updAdminUser.setEndTime(LocalDateTime.now());
        return this.updateById(updAdminUser);
    }


    @Override
    public Boolean updResetPassword(String id, String password) {
        return this.update(new LambdaUpdateWrapper<AdminUser>()
                .set(AdminUser::getPassword, MD5Util.encode(password))
                .eq(AdminUser::getId, id));
    }


    @Override
    public Boolean updByPassword(String oldPassword, String password) {
        AdminUser adminUser = this.getById(JwtUtil.getJwtUser(request).getUserId());
        if (!adminUser.getPassword().equals(MD5Util.encode(oldPassword))) {
            throw new ErrorException(RType.USER_PASSWORD_ERROR);
        }
        adminUser.setPassword(MD5Util.encode(password));
        return this.updateById(adminUser);
    }


    /**
     * 手机号和电话号登录验证，失败自动进入全局异常,成功返回用户信息
     * @author wangsong
     * @param  username 账号
     * @param  password 密码
     * @param  terminal 终端, 如果没有传递终端, 可以导致查出不同端的多条账号数据
     * @date 2021/9/30 0030 14:18
     * @return boolean
     * @version 1.0.0
     */
    private AdminUser loginUsernameOrPhone(String username, String password, Integer terminal) {
        // 1、判断账号
        List<AdminUser> users = this.list(new LambdaQueryWrapper<AdminUser>()
                .and(i -> i.eq(AdminUser::getUsername, username)
                        .or().eq(AdminUser::getPhone, username))
                .eq(terminal != null, AdminUser::getTerminal, terminal)
        );

        if (users.isEmpty()) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        AdminUser user = users.get(0);
        // 如果没有传递终端,且获取到多条账号数据,默认为总管理端平台账号
        if (users.size() > 1) {
            for (AdminUser adminUser : users) {
                if (adminUser.getTerminal().equals(Admin.Terminal.V1.getValue())) {
                    user = adminUser;
                    break;
                }
            }
        }
        // 2、判断密码
        if (!user.getPassword().equals(MD5Util.encode(password))) {
            throw new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD);
        }
        // 3、判断禁用
        if (!user.getDisable().equals(Base.Disable.V0.getValue())) {
            throw new ErrorException(RType.LOGIN_IS_NO_DISABLE);
        }
        return user;
    }


    /**
     * 验证账号是否重复
     * @author wangsong
     * @param username 新手机号
     * @param oldUserName 原手机号
     * @date 2021/9/30 0030 14:12
     * @return void
     * @version 1.0.0
     */
    private void verifyRepeatUsername(String username, String oldUserName, Integer terminal, Integer oldTerminal) {
        if (StringUtils.isNotBlank(username)) {
            // 判重账号
            if (oldUserName == null || !username.equals(oldUserName) || !terminal.equals(oldTerminal)) {
                if (this.count(new LambdaUpdateWrapper<AdminUser>()
                        .eq(AdminUser::getUsername, username)
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                        .eq(terminal != null, AdminUser::getTerminal, terminal)
                ) > 0) {
                    throw new ErrorException(RType.USER_ACCOUNT_IS_DUPLICATE);
                }
            }
        }
    }


    /**
     * 验证手机号是否重复
     * @author wangsong
     * @param phone 新手机号
     * @param oldPhone 原手机号
     * @date 2021/9/30 0030 14:11
     * @return void
     * @version 1.0.0
     */
    private void verifyRepeatePhone(String phone, String oldPhone, Integer terminal, Integer oldTerminal) {
        if (StringUtils.isNotBlank(phone)) {
            // 判重电话
            if (oldPhone == null || !phone.equals(oldPhone) || !terminal.equals(oldTerminal)) {
                if (this.count(new LambdaUpdateWrapper<AdminUser>()
                        .eq(AdminUser::getPhone, phone)
                        .eq(AdminUser::getDeleted, Base.Deleted.V0.getValue())
                        .eq(terminal != null, AdminUser::getTerminal, terminal)
                ) > 0) {
                    throw new ErrorException(RType.USER_PHONE_IS_DUPLICATE);
                }
            }
        }
    }
}