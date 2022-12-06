package top.ctong.commerce.smartcommerce.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ctong.commerce.smartcommerce.components.userPlatformStrategy.UserPlatformGranter;
import top.ctong.commerce.smartcommerce.components.userPlatformStrategy.UserPlatformStrategyBuilder;
import top.ctong.commerce.smartcommerce.enums.RespStatus;
import top.ctong.commerce.smartcommerce.exceptions.NotFoundStrategyException;
import top.ctong.commerce.smartcommerce.exceptions.ParamsErrorException;
import top.ctong.commerce.smartcommerce.exceptions.UnsupportedLoginMethodException;
import top.ctong.commerce.smartcommerce.model.dto.UserRegisterDto;
import top.ctong.commerce.smartcommerce.utils.R;
import top.ctong.commerce.smartcommerce.model.dto.UserLoginDto;

import javax.validation.Valid;

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

//    @Setter(onMethod = @__(@Autowired))
//    private EmailService emailService;

    @Setter(onMethod = @__(@Autowired))
    private UserPlatformStrategyBuilder platformStrategyBuilder;

    /**
     * 用户登录
     * @author Clover You
     * @date 2022/8/7 2:36 AM
     */
    @PostMapping("/login")
    public R login(@Valid UserLoginDto dto) {
        return R.ok();
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
    public R register(UserRegisterDto dto) throws UnsupportedLoginMethodException, ParamsErrorException {
        try {
            // 通过平台获取对应的登录方式
            UserPlatformGranter granter = platformStrategyBuilder.getGranter(dto.getMethod());
            return granter.register(dto.getUsername(), dto.getPassword(), dto.getVerifyCode());
        } catch (NotFoundStrategyException e) {
            throw new UnsupportedLoginMethodException(RespStatus.UNSUPPORTED_LOGIN_METHOD);
        }
    }

}
