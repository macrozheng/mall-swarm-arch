package com.macro.mall.tiny.common.exception;


import com.macro.mall.tiny.common.api.IErrorCode;

/**
 * @auther macrozheng
 * @description 自定义API异常
 * @date 2024/1/17
 * @github https://github.com/macrozheng
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
