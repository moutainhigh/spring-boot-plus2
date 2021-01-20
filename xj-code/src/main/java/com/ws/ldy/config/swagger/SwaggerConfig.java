package com.ws.ldy.config.swagger;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.admin.model.entity.AdminUser;
import com.ws.ldy.modules.sys.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.sys.admin.service.AdminUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/***
 * 接口文档配置信息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/20 0020 9:06
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminAuthorityService adminAuthorityService;

    // 定义分隔符
    private static final String splitor = ";";


    @Bean
    public Docket sysBaseApi() {
        // 名称
        String groupName = "BASE--通用模块";
        // 扫包路径
        String basePackage = "com.ws.ldy.modules.third";
        return this.createDocket(groupName, basePackage);
    }


    @Bean
    public Docket sysAdminApi() {
        // 名称
        String groupName = "ADMIN--系统模块";
        // 扫包路径
        String basePackage = "com.ws.ldy.modules.sys.admin"
                + splitor + "com.ws.ldy.modules.sys.gc"
                + splitor + "com.ws.ldy.modules.sys.pay"
                + splitor + "com.ws.ldy.modules.sys.xj";
        return this.createDocket(groupName, basePackage);
    }


    @Bean
    public Docket ywAdminApi() {
        // 名称
        String groupName = "业务管理端";
        // 扫包路径
        String basePackage = "com.ws.ldy.modules.yw";
        return this.createDocket(groupName, basePackage);
    }


    @Bean
    public Docket ywClientApi() {
        // 名称
        String groupName = "业务用户端";
        // 扫包路径
        String basePackage = "com.ws.ldy.client";
        return this.createDocket(groupName, basePackage);
    }


    /**
     * 创建swagger 目录
     * @return
     */
    public Docket createDocket(String groupName, String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                // 全局参数 -> 默认token参数
                .globalOperationParameters(getGlobalParameter("wangsong"))
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.ws.ldy.modules.pets"
                .apis(basePackage(basePackage))   // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title(groupName)
                        .description("restful Swagger API 文档")
                        .termsOfServiceUrl("https://gitee.com/wslxm/spring-boot-plus2")
                        .version("1.0.0")
                        .contact(new Contact("王松", "https://gitee.com/wslxm/spring-boot-plus2", "1270696548@qq.com"))
                        .build());
    }


    /**
     * swagger全局header参数添加
     * @author wangsong
     * @date 2021/1/20 0020 14:45
     * @return java.util.List<springfox.documentation.service.Parameter>
     * @version 1.0.0
     */
    private List<Parameter> getGlobalParameter(String username) {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        // 管理端默认账号
        parameterBuilder
                .name(BaseConstant.Sys.TOKEN)                // key
                .scalarExample(getAdminUserToken(username))  // value-默认token值
                .description("请求头参数")                    // 描叙
                .modelRef(new ModelRef("string"))      // 字符串参数
                .parameterType("header")                    // 请求头参数
                .order(-1)                                  // 靠前
                .required(false)                            // 非必传
                .build();
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(parameterBuilder.build());
        return parameters;
    }


    /**
     * 获取全局参数token值，默认swagger token
     * @param username
     * @return
     */
    public String getAdminUserToken(String username) {
        // 1、判断账号
        AdminUser user = adminUserService.getOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
        if (user == null) {
            log.info("管理端账号注入swagger 失败，没有获取到账号信息");
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        // 登录成功
        // 4、获取权限列表,保存权限-未禁用,管理端(登录+认证的)
        List<String> authList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(user.getId());
        // 5、生成jwt
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(user.getId());
        jwtUser.setUsername(user.getUsername());
        jwtUser.setFullName(user.getFullName());
        jwtUser.setType(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue());
        jwtUser.setHead(user.getHead());
        jwtUser.setPhone(user.getPhone());
        // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
        jwtUser.setAuthList(authList);
        jwtUser.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);
        String jwtToken = JwtUtil.createToken(jwtUser);
        log.info("管理端账号注入swagger 默认token成功，当前账号为:{} 账号姓名为：{} " + user.getUsername(), user.getFullName());
        return jwtToken;
    }


    //=============================================================================================
    //=============================================================================================
    //========================== 让swagger支持配置多个 包路径 ========================================
    //=============================================================================================
    //=============================================================================================

    /**
     * 让swagger支持配置多个 包路径
     * @author wangsong
     * @param basePackage
     * @date 2020/12/29 0029 11:18
     * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
     * @version 1.0.0
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
