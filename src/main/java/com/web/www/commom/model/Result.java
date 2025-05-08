package com.web.www.commom.model;


import com.web.www.commom.enums.ResultStatusEnum;

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
        return new Result(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), data);
    }

    public static Result success(String message, Object data) {
        return new Result(ResultStatusEnum.SUCCESS.getCode(), message, data);
    }

    public static Result success(String message) {
        return new Result(ResultStatusEnum.SUCCESS.getCode(), message, null);
    }

    public static Result success(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result fail(Object data) {
        return new Result(ResultStatusEnum.FAIL.getCode(), ResultStatusEnum.FAIL.getMessage(), data);
    }

    public static Result fail(String message, Object data) {
        return new Result(ResultStatusEnum.FAIL.getCode(), message, data);
    }

    public static Result fail(String message) {
        return new Result(ResultStatusEnum.FAIL.getCode(), message, null);
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
