package com.web.www.commom.enums;

public enum SysUserStatusEnum {
    PASSWORD_ERROR(80001, "密码错误"),
    USER_NOT_EXIST(80002, "用户不存在"),
    USER_DISABLED(80003, "用户被禁用")
    ;

    private final Integer code;
    private final String message;

    SysUserStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
