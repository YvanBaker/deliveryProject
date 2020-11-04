package com.delivery.exception.customer;

/**
 * @author Yvan
 * @Description 地址数量超出预定值
 * @Classname AddressNumberException
 * @Date 2020/11/4 10:34
 */
public class AddressNumberException extends Exception{
    public AddressNumberException() {
    }

    public AddressNumberException(String message) {
        super(message);
    }

    public AddressNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNumberException(Throwable cause) {
        super(cause);
    }

    public AddressNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
