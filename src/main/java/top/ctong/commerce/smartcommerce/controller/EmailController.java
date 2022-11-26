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
//        String cacheKey = RedisKeys.USER_REGISTER_EMAIL_CODE.KEY() + dto.getEmailNo();
//        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps(cacheKey);
//        String cacheEmailCode = ops.get();
//
//

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", "smtp.qq.com");

        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        Transport transport = session.getTransport();

        transport.connect("smtp.qq.com", "2621869236@qq.com", "ptnzfqgkyhwmdhgb");

        MimeMessage message = new MimeMessage(session);

        message.addFrom(new InternetAddress[]{new InternetAddress("2621869236@qq.com")});
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("2621869236@qq.com"));

        message.setSubject("标题");
        // 邮件的文本内容
        message.setContent("<h1> Hello world! </h1>", "text/html;charset=UTF-8");

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();

//        smtp.qq.com
        // 授权码：ptnzfqgkyhwmdhgb
        return R.ok();
    }

}
