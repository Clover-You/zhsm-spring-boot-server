package top.ctong.commerce.smartcommerce.components.email;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

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
 * 发件内容
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-01-04 10:27
 */
@Data
public class EmailConfig implements Serializable {

    private static final long serialVersionUID = 4197170826796091568L;

    /**
     * 标题
     */
    private String subject;

    /**
     * 发件人
     */
    private String sender;

    /**
     * 内容
     */
    private String content;

    /**
     * 收件人
     */
    @Setter(AccessLevel.NONE)
    private String[] recipients;

    public void setRecipients(String... recipient) {
        this.recipients = recipient;
    }

}
