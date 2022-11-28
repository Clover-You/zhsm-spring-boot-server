package top.ctong.commerce.smartcommerce.utils;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

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
 * locale tools
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-11-26 23:06
 */
public class LocaleUtils {

    public static LocaleUtils contextHolder = new LocaleUtils();

    /**
     * 获取国际化消息
     * @param key 消息名称
     * @param args 参数
     * @return String
     * @author Clover You
     * @date 2022/11/26 23:13
     */
    public final String getMsg(String key, Object... args) {
        MessageSource messageSource = getMessageSource();
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

    private MessageSource MESSAGE_SOURCE;

    /**
     * 获取MessageSource
     * @return MessageSource
     * @author Clover You
     * @date 2022/11/28 15:50
     */
    public MessageSource getMessageSource() {
        return MESSAGE_SOURCE == null ? (MESSAGE_SOURCE = SpringUtils.contextHolder.getBean(MessageSource.class)) : MESSAGE_SOURCE;
    }

}
