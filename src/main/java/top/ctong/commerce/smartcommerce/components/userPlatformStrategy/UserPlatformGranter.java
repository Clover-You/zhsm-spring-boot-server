package top.ctong.commerce.smartcommerce.components.userPlatformStrategy;

import top.ctong.commerce.smartcommerce.exceptions.ParamsErrorException;
import top.ctong.commerce.smartcommerce.model.dto.UserLoginDto;
import top.ctong.commerce.smartcommerce.utils.R;

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
 *
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-12-02 15:19
 */
public interface UserPlatformGranter {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param verifyCode 验证吗
     * @return R
     * @author Clover You
     * @date 2022/12/2 15:32
     */
    R register(String username, String password, String verifyCode) throws ParamsErrorException;

}
