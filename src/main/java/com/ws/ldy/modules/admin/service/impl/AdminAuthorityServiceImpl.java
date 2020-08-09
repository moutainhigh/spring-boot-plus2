package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.ClassUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.mapper.AdminAuthorityMapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleAuthMapper;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminRoleAuth;
import com.ws.ldy.modules.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminAuthorityServiceImpl extends BaseIServiceImpl<AdminAuthorityMapper, AdminAuthority> implements AdminAuthorityService {
    /**
     * url权限注解扫包范围 TODO 应该为直接用代码获取当前项目的路径
     */
    private final static String PACKAGE_NAME = "com.ws.ldy";



    @Autowired
    private AdminRoleAuthMapper adminRoleAuthMapper;
    /**
     *   添加接口--扫描包下所有类
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @Override
    @Transactional
    public void refreshAuthority() {
        // 扫描包，获得包下的所有类
        List<Class<?>> classByPackageName = ClassUtil.getClasses(PACKAGE_NAME);

        // 当前当前数据库已经存在的所有url权限列表--> key=url，value=对象，获取后移除Map中已取出，最后剩下的全部删除
        Map<String, AdminAuthority> authorityMap = new HashMap();
        List<AdminAuthority> authorityList = this.list();
        if (authorityList != null && authorityList.size() > 0) {
            authorityList.forEach(item -> authorityMap.put(item.getUrl(), item));
        }

        // 遍历所有类
        for (Class<?> classInfo : classByPackageName) {
            // 类上存在 @Api 注解 + @RequestMapping 的类进行下一步操作
            Api apiClass = classInfo.getDeclaredAnnotation(Api.class);
            RequestMapping requestMappingClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
            if (apiClass == null || requestMappingClass == null) {
                continue;
            }
            // 判断当前类是否需要保存到接口权限内（目前：PC_ADMIN=平台 ） 需要
            if (apiClass.description().equals(BaseConstant.InterfaceType.PC_ADMIN)) {
                String url = requestMappingClass.value()[0];
                System.out.println("当前类信息-->" + apiClass.value() + "-->" + apiClass.tags()[0] + " --> " + url);
                if (authorityMap.containsKey(url)) {
                    // 存在修改
                    AdminAuthority updAuthority = authorityMap.get(url);
                    updAuthority.setUrl(url);                               // 接口URL
                    updAuthority.setDesc(apiClass.tags()[0]);               // 接口描叙
                    this.updateById(updAuthority);
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, updAuthority);
                    // 移除Map中已取出
                    authorityMap.remove(url);
                } else {
                    // 不存在新添加
                    AdminAuthority addAuthority = new AdminAuthority();
                    addAuthority.setPid("");                              // 请求Pid
                    addAuthority.setMethod("");                           // 请求方式
                    addAuthority.setUrl(url);                             // 接口URL
                    addAuthority.setDesc(apiClass.tags()[0]);             // 接口描叙
                    this.save(addAuthority);
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, addAuthority);
                }
            }
        }
        // 删除多余数据
        if (authorityMap.size() > 0) {
            List<String> delIds = new ArrayList<>();
            authorityMap.forEach((k, v) -> {
                delIds.add(v.getId());
            });
            this.removeByIds(delIds);
        }
    }


    /**
     *     添加指定类的所有接口权限到athorityList
     *
     * @param classInfo    当前类
     * @param authorityMap 当前数据库存在权限
     * @param authority    类的权限数据
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    private void putMethods(Class<?> classInfo, Map<String, AdminAuthority> authorityMap, AdminAuthority authority) {
        // 获取类的所有方法
        Method[] methods = classInfo.getDeclaredMethods();

        //循环添加方法级权限
        for (Method method : methods) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (requestMapping == null || apiOperation == null) {
                log.info(method.getDeclaringClass().getName() + "." + method.getName() + "方法没有@ApiOperation 或 @RequestMapping注解");
                continue;
            }
            // url
            String url = authority.getUrl() + requestMapping.value()[0];
            // 请求方式
            String requestMethod = requestMapping.method()[0].name();
            // 描叙
            String desc = apiOperation.value();
            log.info("方法:{} URL:{} 请求方式:{} 描叙:{} ", method.getDeclaringClass().getName() + "." + method.getName(), url, requestMethod, desc);
            // 存在修改，不存在新添加
            if (authorityMap.containsKey(url)) {
                // 获取已经有权限（根据权限名）
                AdminAuthority updAuthority = authorityMap.get(url);
                updAuthority.setPid(authority.getId());   // 类权限id（父级id）
                updAuthority.setDesc(desc);               // 权限描叙
                updAuthority.setUrl(url);                 // 接口url
                updAuthority.setMethod(requestMethod);    // 请求方式
                this.updateById(updAuthority);
                // 移除Map中已取出
                authorityMap.remove(url);
            } else {
                AdminAuthority addAuthority = new AdminAuthority();
                addAuthority.setPid(authority.getId());   // 类权限id（父级id）
                addAuthority.setDesc(desc);               // 权限描叙
                addAuthority.setUrl(url);                 // 接口url
                addAuthority.setMethod(requestMethod);    // 请求方式
                this.save(addAuthority);
            }
        }
    }



    /**
     * 获取用户的url权限列表，给指定角色的有的权限数据赋予选中状态
     *
     * @param roleId
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findByRoleIdAuthorityChecked(String roleId) {
        // 获取当前角色拥有的url权限列表
        List<AdminRoleAuth> roleIds = adminRoleAuthMapper.selectList(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId,roleId));
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(i -> i.getAuthId()).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有url,请求方式排序
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>().orderByAsc(AdminAuthority::getMethod));
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
}

