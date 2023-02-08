package top.ctong.commerce.smartcommerce.service;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * service 持有人
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2023-02-08 21:52
 */
@Component
public class ServiceHolder {

    @Getter
    private static UserService userService;

    @Getter
    private static EmailService emailService;

    public ServiceHolder(UserService userService, EmailService emailService) {
        ServiceHolder.userService = userService;
        ServiceHolder.emailService = emailService;
    }

}
