package top.ctong.commerce.smartcommerce.components.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
 * Copyright 2023 Clover You.
 * <p>
 * 邮箱发送配置
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-01-04 10:05
 */
@Data
@ConfigurationProperties("zhsm.email")
public class EmailConfigurationProperties {

    /**
     * 邮箱发送服务器地址
     */
    private String host;

    /**
     * 邮箱协议
     */
    private String protocol;

    /**
     * 发送用户
     */
    private String username;

    /**
     * 密钥
     */
    private String pass;

    /**
     * 发件人
     */
    private String sender;

}
