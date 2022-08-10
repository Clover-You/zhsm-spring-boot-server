package top.ctong.commerce.smartcommerce.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
 * 用户登录参数
 * </p>
 * @author Clover
 * @email cloveryou02@163.com
 * @create 2022-08-07 3:50 AM
 */
@Data
public class UserLoginDto {

    /**
     * 账号
     */
    private String acc;

    /**
     * 邮箱
     */
    @Email(message = "{userLoginDto.email.message}")
    private String email;

    /**
     * 手机号
     */
    private Long phoneNo;

    /**
     * 用户密码
     */
    private String pass;

    /**
     * 验证码缓存id
     */
    private String uuid;

    /**
     * 验证码结果
     */
    @NotBlank(message = "{userLoginDto.verifyCode.message}")
    private String verifyCode;
}
