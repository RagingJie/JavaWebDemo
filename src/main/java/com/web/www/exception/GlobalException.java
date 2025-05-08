package com.web.www.exception;

import com.web.www.commom.enums.ResultStatusEnum;
import com.web.www.commom.model.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    /**
     * 处理请求方式不支持的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return Result.fail(ResultStatusEnum.NOT_SUPPORTED.getCode(), ResultStatusEnum.NOT_SUPPORTED.getMessage(), e.getMessage());
    }

    /**
     * 处理请求体解析异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return Result.fail(ResultStatusEnum.NOT_PARSE_JSON_REQUEST_BODY.getCode(), ResultStatusEnum.NOT_PARSE_JSON_REQUEST_BODY.getMessage(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> data = new HashMap<>();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            data.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        e.printStackTrace();
        return Result.fail(ResultStatusEnum.DATA_CHECK_FAIL.getCode(), ResultStatusEnum.DATA_CHECK_FAIL.getMessage(), data);
    }


    @ExceptionHandler(value = BusinessRuntimeException.class)
    public Result handleBusinessRuntimeException(BusinessRuntimeException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage(), e.getData());
    }


    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.fail(ResultStatusEnum.UNKNOWN_ERROR.getCode(), ResultStatusEnum.UNKNOWN_ERROR.getMessage(), e.getMessage());
    }

}
