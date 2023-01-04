package top.ctong.commerce.smartcommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.ctong.commerce.smartcommerce.components.email.EmailConfig;
import top.ctong.commerce.smartcommerce.components.email.EmailTemplate;

import javax.mail.*;
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
 * 邮件测试
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-11-26 14:03
 */
@SpringBootTest
public class EmailTest {

    @Test
    @DisplayName("邮件发送")
    void sendEmail() throws MessagingException {

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        Transport transport = session.getTransport();
        // qq
        transport.connect("smtp.qq.com", "2621869236@qq.com", "ptnzfqgkyhwmdhgb");
        // 网易  FAOBZNCFYJJGLQPN
//        transport.connect("smtp.163.com", "cloveryou02@163.com", "FAOBZNCFYJJGLQPN");
        MimeMessage message = new MimeMessage(session);

        message.addFrom(new InternetAddress[]{new InternetAddress("2621869236@qq.com")});
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("cloveryou02@163.com"));

        message.setSubject("标题");
        // 邮件的文本内容
        message.setContent("<h1> Hello world! </h1>", "text/html;charset=UTF-8");

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

    @Autowired
    private EmailTemplate emailTemplate;

    @Test
    @DisplayName("EmailTemplate 测试")
    void templateTest() throws MessagingException {
        EmailConfig config = new EmailConfig();
        config.setRecipients("2621869236@qq.com", "cloveryou02@163.com");
        config.setContent("hello world");
        config.setSubject("测试标题");
        emailTemplate.send(config);
    }

}
