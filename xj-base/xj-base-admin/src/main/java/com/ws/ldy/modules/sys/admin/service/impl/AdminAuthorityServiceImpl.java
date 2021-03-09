package com.ws.ldy.modules.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.XjAdminServer;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.ClassUtil;
import com.ws.ldy.common.utils.EnumUtil;
import com.ws.ldy.common.utils.IdUtil;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.enums.Admin;
import com.ws.ldy.enums.Base;
import com.ws.ldy.modules.sys.admin.mapper.AdminAuthorityMapper;
import com.ws.ldy.modules.sys.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.sys.admin.model.entity.AdminRole;
import com.ws.ldy.modules.sys.admin.model.entity.AdminRoleAuth;
import com.ws.ldy.modules.sys.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.sys.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.sys.admin.service.AdminRoleAuthService;
import com.ws.ldy.modules.sys.admin.service.AdminRoleService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminAuthorityServiceImpl extends BaseIServiceImpl<AdminAuthorityMapper, AdminAuthority> implements AdminAuthorityService {

    /**
     * url 权限注解扫包范围( 直接获取启动类的包路径)
     */
    private final String PACKAGE_NAME = XjAdminServer.class.getPackage().getName();

    /**
     * 当前系统的所有权限接口数据（key = uri ),用于登录验证用户接口权限避免去数据库查询接口数据
     */
    public static Map<String, AdminAuthority> AUTH_MAP = new HashMap<>();


    @Autowired
    private AdminRoleAuthService adminRoleAuthService;
    @Autowired
    private AdminRoleService adminRoleService;


    /**
     *  查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录，顶级目录ID = -1
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findList() {
        // 查询所有
        List<AdminAuthority> list = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByDesc(AdminAuthority::getType)
                .orderByDesc(AdminAuthority::getMethod)
        );
        List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(list, AdminAuthorityVO.class);
        // pid='' 的数据设置 pid 为枚举字典的code 值
        adminAuthorityVOList.forEach(i -> {
            if (StringUtils.isBlank(i.getPid())) {
                Admin.AuthorityType byCode = EnumUtil.getByCode(i.getType(), Admin.AuthorityType.class);
                i.setPid(byCode.getValue().toString());
            }
        });
        // 生成Admin.AuthorityType 的权限数据放入列表, 有多少条枚举字段就添加几条数据进去, id=枚举的code, pid=''的也设置为了枚举code, 同等于设置了父子级关系
        for (Admin.AuthorityType authorityType : Admin.AuthorityType.values()) {
            AdminAuthorityVO adminAuthorityVO = new AdminAuthorityVO();
            adminAuthorityVO.setId(authorityType.getValue().toString());
            adminAuthorityVO.setDesc(authorityType.getDesc());
            // 设置顶级 pid
            adminAuthorityVO.setPid("-1");
            adminAuthorityVO.setMethod("");
            adminAuthorityVO.setType(null);
            adminAuthorityVO.setState(null);
            adminAuthorityVO.setDisable(null);
            adminAuthorityVOList.add(adminAuthorityVO);
        }
        return adminAuthorityVOList;
    }


    /**
     *   接口自动扫描（1、项目启动时自动执行   2、设置了权限授权状态更新）
     *   <p>
     *       扫描添加接口信息，扫描启动类下的所有包
     *       存在修改（不修改原数据的禁用启动和权限状态,防止重启项目时修改被还原）
     *       不存在添加
     *       多余的生成
     *   </p>
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @SuppressWarnings("all")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean refreshAuthDB() {
        log.info("  @.@...正在更新接口资源,所有被权限管理的接口将被打印出来…… ^.^ ");
        // 扫描包，获得包下的所有类
        List<Class<?>> classByPackageName = ClassUtil.getClasses(PACKAGE_NAME);
        // 当前当前数据库已经存在的所有url权限列表--> key=url，value=对象，获取后移除Map中已取出，最后剩下的全部删除
        List<AdminAuthority> list = this.list();
        Map<String, AdminAuthority> authorityMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            authorityMap = list.stream().collect(Collectors.toMap(AdminAuthority::getUrl, item -> item));
        }
        // 所有需要修改list |添加list |删除的Ids
        List<AdminAuthority> updAuth = new ArrayList<>();
        List<AdminAuthority> addAuth = new ArrayList<>();
        List<String> delIds = new ArrayList<>();
        int classNum = 0;
        // 遍历所有类
        for (Class<?> classInfo : classByPackageName) {
            // 类上存在 @Api 注解 + @RequestMapping 的类进行下一步操作
            Api apiClass = classInfo.getDeclaredAnnotation(Api.class);
            RequestMapping requestMappingClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
            if (apiClass == null || requestMappingClass == null) {
                continue;
            }
            // 判断当前类是否需要保存到接口权限内（目前：PC_ADMIN=平台 / PC_USER  用户端），,代码生成器生成的代码默认(BaseConstant.InterfaceType.RELEASE)不需要任何权限可访问,需手动修改BaseConstant.InterfaceType. 参数
            Integer uriType = null;
            Integer state = null;
            String url = requestMappingClass.value()[0];
            if (url.indexOf(BaseConstant.Uri.apiAdmin) != -1) {
                // 管理端 | 默认需登录+授权
                uriType = Admin.AuthorityType.V0.getValue();
                state = Admin.AuthorityState.V2.getValue();
            } else if (url.indexOf(BaseConstant.Uri.apiClient) != -1) {
                // 用户端 | 默认需登录
                uriType = Admin.AuthorityType.V1.getValue();
                state = Admin.AuthorityState.V1.getValue();
            } else if (url.indexOf(BaseConstant.Uri.apiOpen) != -1) {
                // 通用 | 默认无需登录+无需授权
                uriType = Admin.AuthorityType.V2.getValue();
                state = Admin.AuthorityState.V0.getValue();
            } else if (url.indexOf(BaseConstant.Uri.apiOauth2) != -1) {
                // Oauth2.0接口 | 默认需Oauth2.0授权
                uriType = Admin.AuthorityType.V3.getValue();
                state = Admin.AuthorityState.V3.getValue();
            } else {
                // 其他 api默认放行
                uriType = Admin.AuthorityType.V2.getValue();
                state = Admin.AuthorityState.V0.getValue();
            }
            if (uriType != null) {
                String classLog = "  接口类：--------------@.@[" + apiClass.tags()[0] + "-" + apiClass.value() + "]--";
                log.info(String.format("%-100s", classLog).replace(" ", "-"));
                if (authorityMap.containsKey(url)) {
                    // 存在修改
                    AdminAuthority updAuthority = authorityMap.get(url);
                    updAuthority.setUrl(url);
                    updAuthority.setDesc(apiClass.tags()[0]);
                    updAuthority.setType(uriType);
                    updAuthority.setState(state);
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, updAuthority, updAuth, addAuth);
                    updAuth.add(updAuthority);
                    // 移除Map中已取出的数据
                    authorityMap.remove(url);
                } else {
                    // 不存在新添加
                    AdminAuthority addAuthority = new AdminAuthority();
                    addAuthority.setId(IdUtil.snowflakeId());
                    addAuthority.setPid("");
                    addAuthority.setMethod("");
                    addAuthority.setUrl(url);
                    addAuthority.setDesc(apiClass.tags()[0]);
                    addAuthority.setType(uriType);
                    addAuthority.setState(state);
                    addAuthority.setDisable(Base.Disable.V0.getValue());
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, addAuthority, updAuth, addAuth);
                    addAuth.add(addAuthority);
                }
                //被管理的类数量
                classNum++;
            }
        }
        //
        log.info("  本次刷新接口+类 总数量为:{} ,如接口 [备注信息] 或 [请求方式] 或 [终端] 发送改变,则已被刷新", updAuth.size());
        log.info("  本次添加接口+类 总量为:  {}", addAuth.size());
        // 修改
        if (updAuth.size() > 0) {
            this.updateBatchById(updAuth, 1024);
        }
        // 新增
        if (addAuth.size() > 0) {
            // 判断新增的接口中是否有重复的url，如果Url有重复直接将直接抛出异常
            Map<String, AdminAuthority> addUrls = addAuth.stream().collect(Collectors.toMap(AdminAuthority::getUrl, item -> item));
            // 添加权限
            this.saveBatch(addAuth, 1024);
            // 给所有角色分配新接口的权限
            List<AdminRole> roles = adminRoleService.list();
            if (roles.size() > 0) {
                List<AdminRoleAuth> addRoleAuth = new LinkedList<>();
                for (AdminRole role : roles) {
                    for (AdminAuthority adminAuthority : addAuth) {
                        addRoleAuth.add(new AdminRoleAuth(adminAuthority.getId(), role.getId()));
                    }
                }
                // 更新
                adminRoleAuthService.saveBatch(addRoleAuth, 1024);
                log.info("  本次使用角色分配了接口权限 {} 个", addAuth.size());
            }
        }
        // 删除
        if (authorityMap.size() > 0) {
            authorityMap.forEach((k, v) -> delIds.add(v.getId()));
            this.removeByIds(delIds);
        }
        log.info("  本次删除接口+类 总数量为: {}", delIds.size());
        log.info("  当前被管理的 总类数量为:  {}", classNum);
        log.info("  当前被管理的 总接口数量为: {}", ((updAuth.size() + addAuth.size()) - classNum));
        log.info("  @.@...");
        return true;
    }


    /**
     * 添加指定类的所有接口权限到 athorityList
     *
     * @param classInfo    当前类
     * @param authorityMap 当前数据库存在权限
     * @param authority    类的权限数据
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    private void putMethods(Class<?> classInfo, Map<String, AdminAuthority> authorityMap, AdminAuthority authority, List<AdminAuthority> updAuth, List<AdminAuthority> addAuth) {
        // 获取类的所有方法循环添加方法级权限
        for (Method method : classInfo.getDeclaredMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (requestMapping == null || apiOperation == null) {
                // log.info("  接口资源：[{}]  -->  [{}]  -->  [{}] ", String.format("%-6s", requestMethod), String.format("%-40s", url), "NO");
                // log.info(method.getDeclaringClass().getName() + "." + method.getName() + "方法没有@ApiOperation 或 @RequestMapping注解");
                continue;
            }
            // url | 请求方式 | 方法swagger注释
            String url = authority.getUrl() + requestMapping.value()[0];
            String requestMethod = requestMapping.method()[0].name();
            String desc = apiOperation.value();
            // 日志输出, 使用占位方式让日志对齐
            log.info("  接口资源：[{}]  -->  [{}]  -->  [{}] ", String.format("%-6s", requestMethod), String.format("%-40s", url), desc);
            // 存在修改，不存在新添加
            if (authorityMap.containsKey(url)) {
                AdminAuthority updAuthority = authorityMap.get(url);
                updAuthority.setPid(authority.getId());
                updAuthority.setDesc(desc);
                updAuthority.setUrl(url);
                updAuthority.setMethod(requestMethod);
                updAuthority.setType(authority.getType());
                updAuth.add(updAuthority);
                // 移除Map中已取出的数据
                authorityMap.remove(url);
            } else {
                AdminAuthority addAuthority = new AdminAuthority();
                addAuthority.setId(IdUtil.snowflakeId());
                addAuthority.setPid(authority.getId());
                addAuthority.setDesc(desc);
                addAuthority.setUrl(url);
                addAuthority.setMethod(requestMethod);
                addAuthority.setType(authority.getType());
                addAuthority.setState(authority.getState());
                addAuthority.setDisable(authority.getDisable());
                addAuth.add(addAuthority);
            }
        }
    }


    /**
     * 获取接口列表，给指定角色的拥有的权限数据赋予选中状态
     *
     * @param roleId
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findByRoleIdAuthorityChecked(String roleId) {
        // 获取当前角色拥有的url权限列表
        List<AdminRoleAuth> roleIds = adminRoleAuthService.list(new LambdaQueryWrapper<AdminRoleAuth>()
                .select(AdminRoleAuth::getRoleId, AdminRoleAuth::getAuthId, AdminRoleAuth::getId)
                .eq(AdminRoleAuth::getRoleId, roleId)
        );
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(AdminRoleAuth::getAuthId).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有管理端的url,请求方式排序( PC_admin)
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByAsc(AdminAuthority::getMethod)
                .eq(AdminAuthority::getType, Admin.AuthorityType.V0.getValue())
        );
        // 返回数据处理
        if (authorityList == null || authorityList.size() <= 0) {
            return null;
        } else {
            List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(authorityList, AdminAuthorityVO.class);
            adminAuthorityVOList.forEach(authVO -> {
                if (roleAuthIds.contains(authVO.getId())) {
                    authVO.setIsChecked(true);
                } else {
                    authVO.setIsChecked(false);
                }
            });
            return adminAuthorityVOList;
        }
    }


    /**
     * 获取权限列表，给指定角色的拥有的权限数据赋予选中状态(树结构)
     * @param roleId
     * @return
     */
    @Override
    public List<AdminAuthorityVO> findByRoleIdAuthorityTreeChecked(String roleId) {
        // 获取当前角色拥有的url权限列表
        List<AdminRoleAuth> roleIds = adminRoleAuthService.list(new LambdaQueryWrapper<AdminRoleAuth>()
                .select(AdminRoleAuth::getRoleId, AdminRoleAuth::getAuthId, AdminRoleAuth::getId)
                .eq(AdminRoleAuth::getRoleId, roleId)
        );
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(AdminRoleAuth::getAuthId).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有管理端的url,请求方式排序( PC_admin)
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByAsc(AdminAuthority::getMethod)
                .eq(AdminAuthority::getType, Admin.AuthorityType.V0.getValue())
        );
        List<AdminAuthorityVO> respAuthorityVOList = new ArrayList<>();
        // 返回数据处理
        if (authorityList == null || authorityList.size() <= 0) {
            return null;
        } else {
            List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(authorityList, AdminAuthorityVO.class);
            adminAuthorityVOList.forEach(authVO -> {
                if (roleAuthIds.contains(authVO.getId())) {
                    authVO.setIsChecked(true);
                } else {
                    authVO.setIsChecked(false);
                }
                // 拼接下级tree数据
                if (authVO.getPid().equals("") || authVO.getPid().equals(0)) {
                    adminAuthorityVOList.forEach(authTwoVO -> {
                        if (authTwoVO.getPid().equals(authVO.getId())) {
                            if (authVO.getAuthoritys() == null) {
                                authVO.setAuthoritys(new ArrayList<AdminAuthorityVO>() {{
                                    add(authTwoVO);
                                }});
                            } else {
                                authVO.getAuthoritys().add(authTwoVO);
                            }
                        }
                    });
                    respAuthorityVOList.add(authVO);
                }
            });
            return respAuthorityVOList;
        }
    }


    /**
     * 获取用户的url权限列表，只返回未禁用的 需要登录+授权的url
     *
     * @param  userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<String> findByUserIdaAndDisableFetchAuthority(String userId) {
        List<AdminAuthority> auth = baseMapper.findByUserIdaAndDisableFetchAuthority(
                userId, Base.Disable.V0.getValue(), Admin.AuthorityState.V2.getValue()
        );
        if (auth == null) {
            return null;
        } else {
            return auth.stream().map(AdminAuthority::getUrl).collect(Collectors.toList());
        }
    }


    /**
     * 刷新权限数据
     * @author wangsong
     * @date 2020/8/22 0022 22:35
     * @return void
     * @version 1.0.0
     */
    @Override
    public void refreshAuthCache() {
        // 查询权限表中所有接口
        List<AdminAuthority> authorityList = this.list(null);
        // 缓存所有接口数据到 jvm
        AUTH_MAP = authorityList.stream().collect(Collectors.toMap(AdminAuthority::getUrl, auth -> auth));
        // 数据统计
        int authorityCount = 0;
        int authorityCountState2 = 0;
        int authorityCountState1 = 0;
        int authorityCountState0 = 0;
        for (AdminAuthority auth : authorityList) {
            // 所有被管理的权限,管理端，需登录/授权的接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Admin.AuthorityState.V2.getValue())) {
                authorityCountState2++;
            }
            // 需登录接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Admin.AuthorityState.V1.getValue())) {
                authorityCountState1++;
            }
            // 放行接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Admin.AuthorityState.V0.getValue())) {
                authorityCountState0++;
            }
            // 总接口数
            if (StringUtils.isNotBlank(auth.getPid())) {
                authorityCount++;
            }
        }
        log.info("权限数据加载成功, 当前 [无需登录] 的接口数量为:    {} ", authorityCountState0);
        log.info("权限数据加载成功, 当前 [只需登录] 的接口数量为:    {}", authorityCountState1);
        log.info("权限数据加载成功, 当前 [需登录/授权] 的接口数量为: {}", authorityCountState2);
        log.info("权限数据加载成功, 当前 [所有接口] 的接口数量为:    {}", authorityCount);
    }


    @Override
    public AdminAuthority findFatherAuth(String uri) {
        Map<String, AdminAuthority> authMap = AUTH_MAP;
        AdminAuthority adminAuthority = AUTH_MAP.get(uri);
        if (adminAuthority == null) {
            return null;
        }
        for (AdminAuthority auth : authMap.values()) {
            if (adminAuthority.getPid().equals(auth.getId())) {
                return auth;
            }
        }
        return null;
    }
}

