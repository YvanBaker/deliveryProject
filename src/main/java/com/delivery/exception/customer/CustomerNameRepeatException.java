package com.delivery.exception.customer;

/**
 * @author Yvan
 * @Description 用户姓名重复异常
 * @Classname CustomerNameRepeatException
 * @Date 2020/10/26 10:49
 */
public class CustomerNameRepeatException extends Exception {

    private static final long serialVersionUID = 7774227724540794108L;

    public CustomerNameRepeatException() {
        super();
    }

    public CustomerNameRepeatException(String message) {
        super(message);
    }

    public CustomerNameRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNameRepeatException(Throwable cause) {
        super(cause);
    }
}
