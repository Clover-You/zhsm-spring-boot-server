package top.ctong.commerce.smartcommerce.Util.email.handlers;

import org.springframework.stereotype.Component;
import top.ctong.commerce.smartcommerce.Util.email.SmtpConfig;
import top.ctong.commerce.smartcommerce.Util.email.SmtpHandler;

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
 * 网易邮箱处理
 * </p>
 * @author Clover
 * @email cloveryou02@163.com
 * @create 2022-08-07 3:20 AM
 */
@Component
public class NetEaseEmailHandler implements SmtpHandler {

    /**
     * 是否能处理当前邮箱
     * @param en 邮箱号码
     * @return boolean 是否能处理
     * @author Clover You
     * @date 2022/8/7 2:47 AM
     */
    @Override
    public boolean canHandle(String en) {
        return en.endsWith("@163.com");
    }

    /**
     * 处理器
     * @author Clover You
     * @date 2022/8/7 3:21 AM
     */
    @Override
    public SmtpConfig doHandle() {
        return null;
    }
}
