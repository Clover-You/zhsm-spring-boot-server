package top.ctong.commerce.smartcommerce.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ctong.commerce.smartcommerce.Util.R;
import top.ctong.commerce.smartcommerce.enums.RedisKeys;
import top.ctong.commerce.smartcommerce.model.dto.UserRegisterCodeDto;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
 * 邮箱前端控制器
 * </p>
 * @author Clover You
 * @email 2621869236@qq.com
 * @create 2022-08-07 2:40 AM
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Setter(onMethod = @__(@Autowired))
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送用户注册验证码
     * @param dto 请求参数
     * @author Clover You
     * @date 2022/8/7 2:56 AM
     */
    @PostMapping("/user/register/send_register_code")
    public R userRegisterCode(UserRegisterCodeDto dto) throws MessagingException {

        return R.ok();
    }

}
