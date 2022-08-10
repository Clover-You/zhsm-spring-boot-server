package top.ctong.commerce.smartcommerce.service.impl;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ctong.commerce.smartcommerce.Util.email.EmailTemplate;
import top.ctong.commerce.smartcommerce.Util.email.exception.UnsupportedEmailServiceProviderException;
import top.ctong.commerce.smartcommerce.service.EmailService;

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
 * 邮箱服务
 * </p>
 * @author Clover
 * @email cloveryou02@163.com
 * @create 2022-08-07 3:02 AM
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Setter(onMethod = @__(@Autowired))
    private EmailTemplate emailTemplate;

    /**
     * 发送用户注册邮箱
     * @return boolean 发送成功与否
     * @author Clover You
     * @date 2022/8/7 3:13 AM
     */
    @Override
    public boolean userRegisterCode() throws UnsupportedEmailServiceProviderException {
        return emailTemplate.send();
    }
}
