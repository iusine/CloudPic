package com.github.cloudpic.exception;

import lombok.Getter;

/**
 * 自定义错误码
 *
 */
@Getter
public enum StateCode {

    SUCCESS(200, "ok"),
    PARAMS_ERROR(400, "请求参数错误"),
    NOT_LOGIN_ERROR(401, "未登录"),
    NO_AUTH_ERROR(403, "无权限"),
    NOT_FOUND_ERROR(404, "请求资源不存在"),
    SYSTEM_ERROR(500, "系统内部异常"),
    OPERATION_ERROR(501, "操作失败");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态码信息
     */
    private final String message;

    StateCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
