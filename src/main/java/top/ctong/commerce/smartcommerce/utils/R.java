package top.ctong.commerce.smartcommerce.utils;

import top.ctong.commerce.smartcommerce.enums.RespStatus;

import java.io.Serializable;
import java.util.HashMap;

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
 * 统一响应数据结构
 * </p>
 * @author Clover You
 * @email 2621869236@qq.com
 * @create 2022-08-07 2:16 AM
 */
public final class R extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = -1079895209012455122L;

    private R(RespStatus http, Object ...args) {
        super(http.json());
        this.put("msg", LocaleUtils.contextHolder.getMsg(http.getMsg(), args));
    }

    private R(RespStatus http, Object data, Object ...args) {
        super(http.json());
        this.put("data", data);
        this.put("msg", LocaleUtils.contextHolder.getMsg(http.getMsg(), args));
    }

    private R(RespStatus http, Object data) {
        this(http, data, null);
    }

    /**
     * 请求响应成功
     * @return request status
     */
    public static R ok() {
        return new R(RespStatus.OK);
    }

    /**
     * 请求失败
     * @return error
     */
    public static R fail() {
        return new R(RespStatus.FAIL);
    }

    /**
     * 自定义请求响应成功
     * @return request status
     */
    public static R ok(RespStatus http) {
        return new R(http);
    }

    /**
     * 自定义请求失败
     * @return error
     */
    public static R fail(RespStatus http) {
        return new R(http);
    }

    /**
     * 自定义请求响应成功
     * @return request status
     */
    public static R ok(RespStatus http, Object data) {
        return new R(http, data);
    }

    /**
     * 自定义请求失败
     * @return error
     */
    public static R fail(RespStatus http, Object data) {
        return new R(http, data);
    }
}


