package top.ctong.commerce.smartcommerce.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.ctong.commerce.smartcommerce.components.userPlatformStrategy.UserPlatformGranter;
import top.ctong.commerce.smartcommerce.components.userPlatformStrategy.UserPlatformStrategyBuilder;
import top.ctong.commerce.smartcommerce.enums.RespStatus;
import top.ctong.commerce.smartcommerce.exceptions.*;
import top.ctong.commerce.smartcommerce.model.UserDetailsModel;
import top.ctong.commerce.smartcommerce.model.dto.UserLoginDto;
import top.ctong.commerce.smartcommerce.model.dto.UserRegisterDto;
import top.ctong.commerce.smartcommerce.model.vo.UserInfoVo;
import top.ctong.commerce.smartcommerce.service.UserService;
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
 * 用户前端控制器
 * </p>
 * @author Clover You
 * @email 2621869236@qq.com
 * @create 2022-08-07 2:14 AM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Setter(onMethod = @__(@Autowired))
    private UserPlatformStrategyBuilder platformStrategyBuilder;

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    /**
     * 用户登录
     * @author Clover You
     * @date 2022/8/7 2:36 AM
     */
    @PostMapping("/login")
    public R login(@RequestBody UserLoginDto dto) throws UnsupportedLoginMethodException, UserNotFoundException, PassErrorException, ParamsErrorException {
        try {
            UserPlatformGranter granter = platformStrategyBuilder.getGranter(dto.getMethod());
            return granter.login(dto);
        } catch (NotFoundStrategyException e) {
            throw new UnsupportedLoginMethodException(RespStatus.UNSUPPORTED_LOGIN_METHOD);
        }
    }

    /**
     * 用户退出登录
     * @author Clover You
     * @date 2022/8/7 2:37 AM
     */
    @PostMapping("/signOut")
    public R signOut() {
        return R.ok();
    }

    /**
     * 用户注册
     * @author Clover You
     * @date 2022/8/7 2:39 AM
     */
    @PutMapping("/register")
    public R register(@RequestBody UserRegisterDto dto) throws UnsupportedLoginMethodException, ParamsErrorException {
        try {
            // 通过平台获取对应的登录方式
            UserPlatformGranter granter = platformStrategyBuilder.getGranter(dto.getMethod());
            return granter.register(dto.getUsername(), dto.getPassword(), dto.getVerifyCode());
        } catch (NotFoundStrategyException e) {
            throw new UnsupportedLoginMethodException(RespStatus.UNSUPPORTED_REGISTER_METHOD);
        }
    }

    /**
     * 获取当前登录用户的用户信息
     * @return UserInfoVo
     * @author Clover You
     * @date 2023/2/8 21:45
     */
    @GetMapping("/login-user-info")
    public UserInfoVo currentUserInfo() {
        return userService.currentUserInfo();
    }

}
