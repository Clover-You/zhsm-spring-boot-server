package top.ctong.commerce.smartcommerce.service;

import javax.validation.constraints.NotNull;

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
 * 用户服务
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-12-06 21:46
 */
public interface UserService {

    /**
     * 通过用户名查找用户是否存在
     * @param username 用户名
     * @return Boolean
     * @author Clover You
     * @date 2022/12/6 21:48
     */
    Boolean checkExistByUsername(@NotNull String username);

    /**
     * 通过邮箱注册账号
     * @param emailNo 邮箱
     * @param password 密码
     * @return Boolean
     * @author Clover You
     * @date 2022/12/6 21:58
     */
    Boolean registerByEmail(@NotNull String emailNo, @NotNull String password);
}
