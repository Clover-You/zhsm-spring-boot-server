package top.ctong.commerce.smartcommerce.exceptions;

import lombok.Data;
import top.ctong.commerce.smartcommerce.enums.RespStatus;

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
 * 业务异常基类
 * </p>
 * @author Clover You
 * @email cloveryou02@163.com
 * @create 2022-11-26 16:44
 */
@Data
public abstract class BaseException extends RuntimeException{

    private static final long serialVersionUID = 352653175534763518L;

    private RespStatus hs;

    private Object[] i18nArgs;

    public BaseException(RespStatus status, Object ...arg) {
        super(status.getMsg());
        this.hs = status;
        this.i18nArgs = arg;
    }

    public BaseException(String msg, RespStatus status, Object ...arg) {
        super(msg);
        this.hs = status;
        this.i18nArgs = arg;
    }
}
