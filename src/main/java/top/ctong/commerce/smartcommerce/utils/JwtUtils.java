package top.ctong.commerce.smartcommerce.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import top.ctong.commerce.smartcommerce.model.UserModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2023 Clover You.
 * <p>
 * Jwt 工具类
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-01-04 14:15
 */
@Configuration
public class JwtUtils {

    /**
     * 密钥
     */
    public static String SECRET;

    /**
     * 过期时间
     */
    public static Integer EXPIRE_TIME;

    /**
     * 请求头名称
     */
    public static String TOKEN_HEADER;

    public static String TOKEN_HEAD;

    public JwtUtils(@Value("${zhsm.jwt.secret}") String SECRET,
                    @Value("${zhsm.jwt.expire-time}") Integer EXPIRE_TIME,
                    @Value("${zhsm.jwt.token-header}") String TOKEN_HEADER,
                    @Value("${zhsm.jwt.token-head}") String TOKEN_HEAD) {
        JwtUtils.EXPIRE_TIME = EXPIRE_TIME;
        JwtUtils.SECRET = SECRET;
        JwtUtils.TOKEN_HEADER = TOKEN_HEADER;
        JwtUtils.TOKEN_HEAD = TOKEN_HEAD;
    }

    /**
     * 获取系统过期时间
     * @return long
     * @author Clover You
     * @date 2023/1/4 14:59
     */
    public static long getDefaultExpireTime() {
        return System.currentTimeMillis() + (EXPIRE_TIME * 6000);
    }

    /**
     * 根据用户信息生成jwt
     * @param userModel 用户信息
     * @return String jwt
     * @author Clover You
     * @date 2023/1/4 14:41
     */
    public static String generatorToken(UserModel userModel) {
        Map<String, Object> jwtArgs = new Jackson2HashMapper(false).toHash(userModel);
        jwtArgs.remove("@class");
        return generatorToken(jwtArgs);
    }

    /**
     * 生成jwt
     * @param params 负载数据
     * @return String
     * @author Clover You
     * @date 2023/1/4 15:00
     */
    public static String generatorToken(Map<String, Object> params) {
        Map<String, Object> header = new HashMap<>();
        header.put("type", "Jwt");
        header.put("alg", "HS256");

        return JWT.create()
            .withHeader(header)
            .withPayload(params)
            .withExpiresAt(new Date(getDefaultExpireTime()))
            .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 在jwt中获取用户名
     * @param token jwt token
     * @return String
     * @author Clover You
     * @date 2023/2/8 23:14
     */
    public static String getUserNameFormJwt(String token) {
        Map<String, Claim> claims = getClaims(token);
        return claims.get("userId").asString();
    }

    /**
     * 获取jwt数据
     * @param token jwt token
     * @return Map<Object>
     * @author Clover You
     * @date 2023/2/8 23:20
     */
    private static Map<String, Claim> getClaims(String token) {
        return JWT.decode(token).getClaims();
    }

}
