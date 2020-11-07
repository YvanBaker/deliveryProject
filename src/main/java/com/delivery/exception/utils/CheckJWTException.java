package com.delivery.exception.utils;

/**
 * @author Yvan
 * @Description checkJWT 异常
 * @Classname CheckJWTException
 * @Date 2020/11/6 9:41
 */
public class CheckJWTException extends RuntimeException {
    private static final long serialVersionUID = 3929335993115415194L;

    public CheckJWTException() {
    }

    public CheckJWTException(String message) {
        super(message);
    }

    public CheckJWTException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckJWTException(Throwable cause) {
        super(cause);
    }

    public CheckJWTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
