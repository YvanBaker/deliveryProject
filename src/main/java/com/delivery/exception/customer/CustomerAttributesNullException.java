package com.delivery.exception.customer;

/**
 * @author Yvan
 * @Description 必要参数为空
 * @Classname CustomerAttributesNullException
 * @Date 2020/10/26 13:29
 */
public class CustomerAttributesNullException extends Exception {

    private static final long serialVersionUID = -482508931477237431L;

    public CustomerAttributesNullException() {
        super();
    }

    public CustomerAttributesNullException(String message) {
        super(message);
    }

    public CustomerAttributesNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAttributesNullException(Throwable cause) {
        super(cause);
    }

    public CustomerAttributesNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
