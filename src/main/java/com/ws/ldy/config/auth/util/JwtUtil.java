package com.ws.ldy.config.auth.util;

import com.ws.ldy.common.utils.JsonUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.vo.AdminUserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

/***
 *   jwt 工具类
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 19:13
 */
@SuppressWarnings("ALL")
public class JwtUtil {

    // 主题
    private static final String SUBJECT = "xijia";

    // jwt的token有效期，
    //private static final long EXPIRITION = 1000L * 60 * 60 * 24 * 7;//7天
    private static final long EXPIRITION = 1000L * 60 * 30;   // 半小时

    // 加密key（黑客没有该值无法篡改token内容）
    private static final String APPSECRET_KEY = "xijia";

    // 用户url权限列表key
    private static final String AUTH_CLAIMS = "auth";
    private static final String AUTH_USER = "user";

    /**
     *   生成token
     *
     * @param user 用户信息
     * @param auth 用户权限
     * @return java.lang.String
     * @date 2020/7/6 0006 9:26
     */
    public static String createToken(AdminUserVO userVO) {
        // 重新赋值权限数据
        List<String> authList = userVO.getAuthList();
        // 清除部分数据
        userVO.setAuthList(null);
        // 添加权限数据版本号,当发生改变时，直接刷新token信息
        userVO.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);
        String token = Jwts
                .builder()
                // 主题
                .setSubject(SUBJECT)
                // TODO 用户信息加密
                // 添加jwt自定义值
                .claim(AUTH_CLAIMS, authList)
                .claim(AUTH_USER, JsonUtil.toJSONStringNoNull(userVO))
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                // 加密方式,加密key
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }


    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public static AdminUserVO getUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        // user
        AdminUserVO user = JsonUtil.parseEntity(claims.get(AUTH_USER).toString(), AdminUserVO.class);
        // auth
        Object obj = claims.get(AUTH_CLAIMS);
        List list = obj == null ? null : JsonUtil.parseList(JsonUtil.toJSONString(obj));
        user.setAuthList(list);
        return user;
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        System.out.println("过期时间: " + claims.getExpiration());
        return claims.getExpiration().before(new Date());
    }
}