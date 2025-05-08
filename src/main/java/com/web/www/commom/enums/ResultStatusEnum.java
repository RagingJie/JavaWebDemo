package com.web.www.commom.enums;

public enum ResultStatusEnum {

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    DATA_CHECK_FAIL(300, "数据校验失败"),
    NOT_FOUND(404, "未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_SUPPORTED(415, "请求方式不支持"),
    NOT_ACCEPTABLE(406, "不可接受"),
    NOT_PARSE_JSON_REQUEST_BODY(405, "不能正确解析请求体"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "冲突"),
    GONE(410, "已删除"),
    INTERNAL_SERVER_ERROR(500, "服务器错误"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP版本不受支持"),
    EXPORT_ERROR(50001, "导出错误"),
    IMPORT_ERROR(50002, "导人错误"),
    FILE_NOT_FOUND(50003, "文件不存在或该文件正被其他程序占用"),
    UNKNOWN_ERROR(600, "未知错误");
    private Integer code;
    private String message;

    ResultStatusEnum(int code, String message) {
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
