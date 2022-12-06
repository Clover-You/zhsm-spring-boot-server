package top.ctong.commerce.smartcommerce.enums;

import lombok.Getter;
import top.ctong.commerce.smartcommerce.exceptions.FrequentOperationException;

import java.util.HashMap;
import java.util.Map;

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
 * 响应枚举
 * </p>
 * @author Clover You
 * @email 2621869236@qq.com
 * @create 2022-08-07 2:20 AM
 */
public enum RespStatus {

    /**
     * 响应成功状态，状态码：200
     */
    OK(200, "successful"),

    FAIL(500, "failure"),

    /**
     * frequent operation
     */
    FREQUENT_OPERATION(10000, "operation.frequent"),

    /**
     * email send success
     */
    EMAIL_SENT_SUCCESSFULLY(200, "email_sent_successfully"),

    /**
     * unsupported login method
     */
    UNSUPPORTED_LOGIN_METHOD(501, "unsupported_login_method"),

    /**  params correlation error **/

    VERIFY_CODE_ERROR(10100, "verify_code_error"),

    EMAIL_HAS_BEEN_REGISTERED(10101, "email_has_been_registered");

    /**
     * 响应状态码
     */
    @Getter
    private int code;

    /**
     * 响应消息
     */
    @Getter
    private String msg;

    /**
     * 获取到当前状态json字符串
     * @return json str
     */
    public final Map<String, Object> json() {
        HashMap<String, Object> json = new HashMap<>();
        json.put("code", this.code);
        json.put("msg", this.msg);
        return json;
    }

    RespStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
