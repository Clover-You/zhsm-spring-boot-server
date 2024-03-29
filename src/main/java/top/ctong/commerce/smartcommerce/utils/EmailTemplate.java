package top.ctong.commerce.smartcommerce.utils;

import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
 * 邮箱工具
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-11-26 14:24
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "zhsm.email")
public class EmailTemplate {

    @Setter
    private String host;

    @Setter
    private String protocol;

    @Setter
    private String username;

    @Setter
    private String pass;

    @Setter
    private String sender;

    @Data
    public static class EmailConfig {

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
        private String[] recipients;

        public void setRecipients(String ...recipient) {
            this.recipients = recipient;
        }
    }

    public void send(EmailConfig config) throws MessagingException, AddressException {
        Properties properties = System.getProperties();
        properties.setProperty("mail.transport.protocol", this.protocol);
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(log.isDebugEnabled());

        Transport transport = session.getTransport();

        transport.connect(this.host, this.username, this.pass);

        MimeMessage message = new MimeMessage(session);
        message.addFrom(new InternetAddress[]{new InternetAddress(
            config.getSender() == null ? this.username :
                config.getSender() + "<" + this.username + ">"
        )});

        // 将收件人地址转 Address[]
        Address[] recipients = Arrays.stream(config.getRecipients()).map(it -> {
            try {
                return new InternetAddress(it);
            } catch (AddressException e) {
                throw new RuntimeException(e);
            }
        }).toArray(InternetAddress[]::new);

        message.setRecipients(Message.RecipientType.TO, recipients);
        message.setSubject(config.getSubject(), "UTF-8");
        message.setContent(config.getContent(), "text/html;charset=UTF-8");

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
