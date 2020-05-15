package com.ws.ldy.config;

import com.ws.ldy.admin.mapper.AuthorityAdminMapper;
import com.ws.ldy.admin.mapper.RoleAuthAdminMapper;
import com.ws.ldy.admin.model.entity.AuthorityAdmin;
import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.common.utils.SignUtil;
import com.ws.ldy.config.constant.BaseConstant;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.config.result.Result;
import com.ws.ldy.config.result.ResultType;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO  URL权限, 验签（只针对 /public 接口），xss攻击防御，URL特殊字符转译，防盗链, 允许跨域，log4j接口调用日志记录
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:20
 */
@SuppressWarnings("all")
@Component
@Aspect
@Slf4j
public class Aop {
    //private static final Logger log = LoggerFactory.getLogger(Aop.class);
    @Resource
    private AuthorityAdminMapper authorityAdminDao;
    @Resource
    private RoleAuthAdminMapper roleAuthAdminDao;
    /**
     * 验签接口范围
     */
    private final static String path = "/public";

    /**
     * TODO  拦截所有模块下的 controller 接口, 环绕通知：可以实现前置通知，后置通知，返回通知，例外通知的所有功能
     *
     * @param jp 指定的连接点（拦截的业务方法）
     * @return java.lang.Object
     * @Around("execution(* com.ws.ldy.adminconsole.controller.*.*(..))")
     * @Around("execution(* com.ws.ldy..*.*(..))")
     * @author 王松
     * @mail 1720696548@qq.com
     * @date 2020/1/13 0013 20:18
     */
    @Around("execution(* com.ws.ldy.admin.controller.*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        //此处ip地址为需要访问服务器的ip及端口号
        //response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.1:8080");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Token,Accept, Connection, User-Agent, Cookie");
        response.setHeader("Access-Control-Max-Age", "3628800");

        // 请求参数
        Object[] args = jp.getArgs();
        //请求日志
        //log.info("URL:[{}] -----> REQUEST:[{}]", request.getServletPath(), args);
        log.info("请求URL:{} --> 请求参数:{}", request.getServletPath(), args);
        this.checkLogin(request);                // 登录验证+url 权限管理
        this.verify(request);                    // 验签
        this.AntiTheftChain(jp, request, args);  // 防盗链
        this.XssAttack(jp, request, args);       // Xss攻击
        Object obj = jp.proceed(args);           // 调用业务逻辑，并记录日志
        //响应日志
        // log.info("  URL:[{}] -----> REQUEST:[{}] \r\n---------------------------> RESPONSE:[{}]", request.getServletPath(), args, obj);
        return obj;
    }


    /**
     * TODO 1、判断是否登录
     */
    public void checkLogin(HttpServletRequest request) {
        // key = 放行接口URL，value = 放行接口描叙内容
        Map<String, String> interfaceMap = new HashMap<>();
        interfaceMap.put("/loginAdmin/login", "登录接口");
        //请求接口
        String interfaceUrl = request.getServletPath();
         //
        if (interfaceMap.containsKey(interfaceUrl)){
            //放行interfaceMap 内的接口
        }else{
            // 登录验证
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)) {
                //没有token
                throw new ErrorException(ResultType.ADMIN_IS_NO_TOKEN);
            }
            UserAdmin userAdmin = (UserAdmin) request.getSession().getAttribute(BaseConstant.SYS + token);
            if (userAdmin == null) {
                // token无效/登录失效
                throw new ErrorException(ResultType.ADMIN_IS_NO_LOGIN);
            }
            // 4、权限验证
            List<AuthorityAdmin> list = authorityAdminDao.findUserIdRoleAuthority(userAdmin.getId());
            Map<String, AuthorityAdmin> map = new HashMap<>();
            list.forEach(item -> map.put(item.getUrl().trim(), item));
            // 5、获取接口权限名称，判断是否有权限
            if (!map.containsKey(interfaceUrl.trim())) {
                //无权限
                throw new ErrorException(ResultType.SYS_IS_NO_AUTHORIZATION);
            }
        }

    }


    /**
     * TODO  2、url 权限管理
     */
    public void auth(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        // 1、获取方法, 在获取权限注解，jp=aop通知拦截获取的请求信息
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 获取请求方法
        Method targetMethod = methodSignature.getMethod();
        // 获取接口上的LdyAuthority注解
//        LdyAuthority annotation = targetMethod.getAnnotation(LdyAuthority.class);
//        // 3、无权限注解直接放行
//        if (annotation == null) {
//            return;
//        }

    }

    /**
     * TODO 2、验签
     */
    public Result verify(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (servletPath.contains(path)) {    // = if(servletPath.indexOf(path) == -1 )
            Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
            if (!SignUtil.verify(verifyMap)) {
                return error(ResultType.SYS_IS_NO_VISIT.getCode(), "验签失败");
            }
        }
        return success(0);
    }


    /**
     * TODO    3、Xss攻击处理
     */
    public void XssAttack(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            //是字符串且不是json数据进行转换， && !isJson(args[i].toString())
            if ((args[i] instanceof String && !isJson(args[i].toString()))) {  //  System.out.println(args[i].toString());
                // args[i] = StringEscapeUtils.escapeHtml(URLDecoder.decode(args[i].toString()));
                // args[i] = StringEscapeUtils.escapeHtml(args[i].toString());
                args[i] = htmlEncode(args[i].toString());
            }
        }
    }


    /**
     * TODO  4、防盗链处理
     * <p>
     * “如果是直接在浏览器里输入有referer的页面,返回是null,也就是说referer只有从别的页面点击连接来到这页的才会有内容。
     * referer为NULL就是手工,非null就是连接过来的。刷新的时候,会检查服务端是否会有更新,
     * 没有的话,则使用本机的缓存,也就是说,你刷新时得到的响应依然是 前一次...”
     */
    public Result AntiTheftChain(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        //获得url 来源,request.getServerName()，相当于当前服务器来源，ip 或 请求地址
        String referer = request.getHeader("referer");
        if (referer == null) {
            //允许url直接访问
            return success(0);
        }
        if (!referer.contains(request.getServerName())) {
            return error(ResultType.SYS_IS_NO_VISIT.getCode(), "切勿非法盗用资源");
        }
        //System.out.println("refer is" + "" + referer);
        return success(0);
    }


    /**
     * TODO  判断string 是否为json，Xss攻击判断
     */
    private boolean isJson(String content) {
        try {
            JSONArray.fromObject(content);
            //JSONObject.fromObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * TODO  xss 攻击过滤  ，慎用StringEscapeUtils.escapeHtml方法，中文及json数据会被转换
     *
     * @param source
     * @return java.lang.String
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 16:31
     */
    public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '"':
                    buffer.append("&quot;");
                    break;
                case 10:
                case 13:
                    break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }


    //TODO  返回成功,带数据+页数
    public <T> Result<T> success(T data, Integer count) {
        return new Result(ResultType.SYS_SUCCESS, data);
    }

    //TODO  返回成功,带数据-不带页数
    public <T> Result<T> success(T data) {
        return new Result(ResultType.SYS_SUCCESS, data);
    }

    // TODO 返回成功，-不带数据 -不带页数
    public Result<Void> success() {
        return new Result(ResultType.SYS_SUCCESS, null);
    }

    // TODO 返回失败（传入自定义枚举）
    public <T> Result<T> error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    // TODO 返回失败（传入自定义枚举）
    public <T> Result<T> error(ResultType resultType) {
        return new Result(resultType, null);
    }
}
