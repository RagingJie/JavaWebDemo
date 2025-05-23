package com.web.www.commom.model;


import com.web.www.commom.enums.SysCommonStatusEnum;

public class Result {

    // 响应码
    private Integer code;
    // 响应信息
    private String message;
    // 响应数据
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(SysCommonStatusEnum.SUCCESS.getCode(), SysCommonStatusEnum.SUCCESS.getMessage(), data);
    }

    public static Result success(String message, Object data) {
        return new Result(SysCommonStatusEnum.SUCCESS.getCode(), message, data);
    }

    public static Result success(Integer code, String message) {
        return new Result(SysCommonStatusEnum.SUCCESS.getCode(), message, null);
    }

    public static Result success(String message) {
        return new Result(SysCommonStatusEnum.SUCCESS.getCode(), message, null);
    }

    public static Result success(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result fail(Object data) {
        return new Result(SysCommonStatusEnum.FAIL.getCode(), SysCommonStatusEnum.FAIL.getMessage(), data);
    }

    public static Result fail(String message, Object data) {
        return new Result(SysCommonStatusEnum.FAIL.getCode(), message, data);
    }

    public static Result fail(Integer code, String message) {
        return new Result(SysCommonStatusEnum.FAIL.getCode(), message, null);
    }

    public static Result fail(String message) {
        return new Result(SysCommonStatusEnum.FAIL.getCode(), message, null);
    }

    public static Result fail(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
