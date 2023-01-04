package top.ctong.commerce.smartcommerce.components.email;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@Component
@EnableConfigurationProperties(EmailConfigurationProperties.class)
public class EmailTemplate {

    private final EmailConfigurationProperties globalConfig;

    EmailTemplate(EmailConfigurationProperties conf) {
        globalConfig = conf;
    }

    public void send(EmailConfig config) throws MessagingException, AddressException {
        Properties properties = System.getProperties();
        properties.setProperty("mail.transport.protocol", globalConfig.getProtocol());
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(log.isDebugEnabled());

        Transport transport = session.getTransport();

        transport.connect(globalConfig.getHost(), globalConfig.getUsername(), globalConfig.getPass());

        MimeMessage message = new MimeMessage(session);
        message.addFrom(new InternetAddress[]{new InternetAddress(
            config.getSender() == null ? globalConfig.getUsername() :
                config.getSender() + "<" + globalConfig.getUsername() + ">"
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
