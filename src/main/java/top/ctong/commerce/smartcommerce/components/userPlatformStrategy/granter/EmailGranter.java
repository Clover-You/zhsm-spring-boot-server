package top.ctong.commerce.smartcommerce.components.userPlatformStrategy.granter;

import org.springframework.stereotype.Component;
import top.ctong.commerce.smartcommerce.components.userPlatformStrategy.UserPlatformGranter;
import top.ctong.commerce.smartcommerce.utils.R;

import java.io.Serializable;

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
 * Copyright 2022 Clover You.
 * <p>
 * 邮箱
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-12-02 15:46
 */
@Component
public class EmailGranter implements Serializable, UserPlatformGranter {

    private static final long serialVersionUID = 4064314388959468507L;

    /**
     * 用户注册
     * @return R
     * @author Clover You
     * @date 2022/12/2 15:32
     */
    @Override
    public R register() {
        return null;
    }
}
