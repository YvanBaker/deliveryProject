package com.delivery.exception.customer;

/**
 * @author Yvan
 * @Description 用户为空异常
 * @Classname CustomerNullException
 * @Date 2020/10/26 14:00
 */
public class CustomerNullException extends Exception {

    private static final long serialVersionUID = 9064583321014122667L;

    public CustomerNullException() {
        super();
    }

    public CustomerNullException(String message) {
        super(message);
    }

    public CustomerNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNullException(Throwable cause) {
        super(cause);
    }

    public CustomerNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
