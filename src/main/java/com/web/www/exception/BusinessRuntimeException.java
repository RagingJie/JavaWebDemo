package com.web.www.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessRuntimeException extends RuntimeException {

    private Integer code;
    private String message;
    private Object data;

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
