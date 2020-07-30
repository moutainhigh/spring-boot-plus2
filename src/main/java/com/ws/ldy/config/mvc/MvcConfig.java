package com.ws.ldy.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *  登录拦截、赋值文件读写权限、页面跳转
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:20
 */
@SuppressWarnings("all")
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 访问URL路径与 resources\templates 页面(.html）路径映射配置, 这里主要做单独的页面跳转
     * <p>
     * 如: 访问/login  为访问 xj_login.html 页面，自动指定了/ 后缀（.html） resources同webapp（也就是web根目录） templates同WEB-INF
     * </P>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 主页,
        registry.addViewController("/index").setViewName("modules/admin/xj-index");
        // 登录页
        registry.addViewController("/").setViewName("modules/admin/xj_login");
        registry.addViewController("/login").setViewName("modules/admin/xj_login");
        // 文本转符号
        registry.addViewController("/fh").setViewName("front/symbol/main");
    }


    /**
     * 静态资源访问路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //文件保存路径, 让程序可以访问到改路径, 访问 /File 读取项目跟路径的File目录
        registry.addResourceHandler("/File/**").addResourceLocations("file:File/");

        // 解决静态资源无法访问（可选）
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        // 直接在浏览器访问：根目录/swagger-ui.html
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        // 需要用到的webjars（包含js、css等）
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
