package top.ctong.commerce.smartcommerce.Util.email;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ctong.commerce.smartcommerce.Util.email.exception.UnsupportedEmailServiceProviderException;

import java.util.List;

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
 * 邮箱处理工具类
 * </p>
 * @author Clover
 * @email cloveryou02@163.com
 * @create 2022-08-07 2:50 AM
 */
@Slf4j
//@Component
public class EmailTemplate {

    @Setter(onMethod = @__(@Autowired))
    private List<SmtpHandler> smtpHandlers;

    /**
     * 发送邮箱
     * @return boolean 邮箱发送成功与否
     * @throws UnsupportedEmailServiceProviderException 不支持的邮箱供应商
     * @author Clover
     * @date 2022/8/7 3:25 AM
     */
    public boolean send() throws UnsupportedEmailServiceProviderException {
        SmtpHandler handler = getHandler("cloveryou02@163.com");
        assert handler != null : UnsupportedEmailServiceProviderException.class;
        SmtpConfig sc = handler.doHandle();
        if (sc == null) {
            return false;
        }
        log.info("邮箱发送成功！！");

        return true;
    }

    /**
     * 通过账号获取邮箱处理器
     * @param en 邮箱账号
     * @return SmtpHandler
     * @author Clover
     * @date 2022/8/7 3:24 AM
     */
    private SmtpHandler getHandler(String en) {
        for (SmtpHandler handler : smtpHandlers) {
            if (handler.canHandle(en)) {
                return handler;
            }
        }
        return null;
    }
}
