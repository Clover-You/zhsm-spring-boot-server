package top.ctong.commerce.smartcommerce.service.impl;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.ctong.commerce.smartcommerce.exceptions.FrequentOperationException;
import top.ctong.commerce.smartcommerce.utils.EmailTemplate;
import top.ctong.commerce.smartcommerce.enums.RedisKeys;
import top.ctong.commerce.smartcommerce.model.redis.RedisEmailCodeEntity;
import top.ctong.commerce.smartcommerce.service.EmailService;

import javax.mail.MessagingException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    private RedisTemplate<String, Object> redisTemplate;

    @Setter(onMethod = @__(@Autowired))
    private EmailTemplate emailTemplate;


    @Setter(onMethod = @__(@Autowired))
    private TemplateEngine templateEngine;

    /**
     * 发送用户注册邮箱
     * @param email 邮箱
     * @return boolean 发送成功与否
     * @author Clover You
     * @date 2022/8/7 3:13 AM
     */
    @Override
    public boolean userRegisterCode(String email) throws FrequentOperationException {
        String emailMd5 = DigestUtils.md5DigestAsHex(email.getBytes());
        String cacheKey = RedisKeys.USER_REGISTER_EMAIL_CODE.KEY() + emailMd5;

        BoundValueOperations<String, Object> ops = redisTemplate.boundValueOps(cacheKey);
        RedisEmailCodeEntity oldCode = (RedisEmailCodeEntity) ops.get();

        if (oldCode != null) {
            // 超过一分钟才可以再次刷新
            if (System.currentTimeMillis() - oldCode.getCreatAt() <= 60000) {
                throw new FrequentOperationException();
            }
        }

        Integer myCode = new Random().nextInt(900000) + 100000;
        Context context = new Context();
        context.setVariable("code", myCode);

        RedisEmailCodeEntity redisEmailCode = new RedisEmailCodeEntity();
        redisEmailCode.setCode(myCode.toString());
        redisEmailCode.setEmial(email);
        redisEmailCode.setCreatAt(System.currentTimeMillis());

        // 缓存30分钟
        ops.set(redisEmailCode, 30, TimeUnit.MINUTES);

        EmailTemplate.EmailConfig config = new EmailTemplate.EmailConfig();
        config.setSubject("新用户注册 -- 智慧商贸「学习项目」");
        config.setRecipients(email);
        config.setSender("Clover");
        config.setContent(
            templateEngine.process("email", context)
        );

        try {
            emailTemplate.send(config);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
