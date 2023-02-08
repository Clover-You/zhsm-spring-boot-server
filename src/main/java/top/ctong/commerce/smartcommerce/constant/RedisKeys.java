package top.ctong.commerce.smartcommerce.constant;

import java.io.Serializable;

/**
 * <p>
 * redis 缓存key
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-02-08 22:13
 */
public final class RedisKeys implements Serializable {

    private static final long serialVersionUID = -2228306420087489975L;

    /**
     * 用户登录信息
     */
    public static final String USER_SECURITY_DATA = "WEB:USER-SECURITY-DATA";

}
