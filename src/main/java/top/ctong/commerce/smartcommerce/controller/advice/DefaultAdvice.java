package top.ctong.commerce.smartcommerce.controller.advice;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.ctong.commerce.smartcommerce.utils.R;
import top.ctong.commerce.smartcommerce.enums.RespStatus;
import top.ctong.commerce.smartcommerce.exceptions.BaseException;

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
 * 前端默认异常处理器
 * </p>
 * @author Clover
 * @email cloveryou02@163.com
 * @create 2022-08-07 3:46 AM
 */
@ControllerAdvice("top.ctong.commerce.smartcommerce.controller")
public class DefaultAdvice {

    @Setter(onMethod = @__(@Autowired))
    private MessageSource messageSource;
    /**
     * 默认异常处理器
     * @author Clover
     * @date 2022/8/7 3:48 AM
     */
    @ExceptionHandler({BaseException.class})
    public R baseExceptionHandler(BaseException e) {
        Locale locale = LocaleContextHolder.getLocale();
        RespStatus hs = e.getHs();
        String message = messageSource.getMessage(hs.getMsg(), e.getI18nArgs(), locale);

        return R.fail();
    }

    @ExceptionHandler({Exception.class})
    public R defaultExceptionHandler(Exception e) {
        return R.fail();
    }
}
