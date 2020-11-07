package com.delivery.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.delivery.model.MsgResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Yvan
 * @Description 拦击器返回信息的方法
 * @Classname SendMsgUtil
 * @Date 2020/11/6 9:31
 */
public class SendMsgUtil {

    /**
     * 发送消息 text/html;charset=utf-8
     * @param response
     * @param str
     * @throws Exception
     */
    public static void sendMessage(HttpServletResponse response, String str) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(str);
        writer.close();
        response.flushBuffer();
    }

    /**
     * 将某个对象转换成json格式并发送到客户端
     * @param response
     * @param msg
     * @throws Exception
     */
    public static void sendJsonMessage(HttpServletResponse response, String msg) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(MsgResponse.buildError(msg), SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        writer.close();
        response.flushBuffer();
    }
}