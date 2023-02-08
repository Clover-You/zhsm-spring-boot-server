package top.ctong.commerce.smartcommerce.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import top.ctong.commerce.smartcommerce.exceptions.PassErrorException;
import top.ctong.commerce.smartcommerce.exceptions.UserNotFoundException;
import top.ctong.commerce.smartcommerce.model.UserDetailsModel;
import top.ctong.commerce.smartcommerce.model.UserModel;
import top.ctong.commerce.smartcommerce.model.vo.UserInfoVo;

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
    String registerByEmail(@NotNull String emailNo, @NotNull String password);


    /**
     * 用户邮箱登录
     * @param email 邮箱
     * @param pass 密码
     * @return UserModel
     * @author Clover You
     * @date 2023/1/4 13:28
     */
    UserModel loginByEmailAndPass(@NotNull String email, @NotNull String pass) throws UserNotFoundException, PassErrorException;

    /**
     * 通过用户id查找用户信息
     * @param userId 用户id
     * @return UserModel
     * @author Clover You
     * @date 2023/2/8 21:58
     */
    UserModel queryUserById(@NotNull String userId);

    /**
     * 获取当前登录用户完整信息
     * @return UserInfoVo
     * @author Clover You
     * @date 2023/2/8 22:20
     */
    UserInfoVo currentUserInfo();

    /**
     * 通过用户ID获取用户信息
     * @param userId 用户id
     * @return UserDetails
     * @author Clover You
     * @date 2023/2/8 22:51
     */
    UserDetailsModel userModelWrapperDetail(String userId);

}
