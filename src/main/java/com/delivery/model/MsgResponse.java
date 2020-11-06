package com.delivery.model;

import java.io.Serializable;

/**
 * @author Yvan
 * @Description 返回信息处理
 * @Classname MsgResponse
 * @Date 2020/10/22 11:46
 */
public class MsgResponse implements Serializable {

    private static final long serialVersionUID = 276095951241401867L;

    /**
     * 状态码 200成功 400失败
     */
    private String code;
    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;


    public MsgResponse() {
    }

    public MsgResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功对象
     *
     * @return MsgResponse 成功
     */
    public static MsgResponse buildSuccess() {
        return new MsgResponse("200", null, null);
    }

    /**
     * 返回成功对象
     *
     * @param data 数据
     * @return MsgResponse 成功
     */
    public static MsgResponse buildSuccess(Object data) {
        return new MsgResponse("200", null, data);
    }

    /**
     * 返回成功对象
     *
     * @param msg 消息
     * @return MsgResponse 成功
     */
    public static MsgResponse buildSuccess(String msg) {
        return new MsgResponse("200", msg, null);
    }

    /**
     * 返回成功对象
     *
     * @param msg  消息
     * @param data 数据
     * @return MsgResponse 成功
     */
    public static MsgResponse buildSuccess(String msg, Object data) {
        return new MsgResponse("200", msg, data);
    }


    /**
     * 返回错误对象
     *
     * @return MsgResponse 错误
     */
    public static MsgResponse buildError() {
        return new MsgResponse("400", null, null);
    }

    /**
     * 返回错误对象
     *
     * @param msg 消息
     * @return MsgResponse 错误
     */
    public static MsgResponse buildError(String msg) {
        return new MsgResponse("400", msg, null);
    }

    public static MsgResponse buildError(String msg, Object data) {
        return new MsgResponse("400", msg, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String codec) {
        this.code = codec;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }
}
