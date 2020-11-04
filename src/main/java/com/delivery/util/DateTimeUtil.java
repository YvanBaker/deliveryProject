package com.delivery.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wenJ
 * @Description 字符串转date
 * @Classname StringToDateTime
 * @Date 2020/11/2 0:02
 */
public class DateTimeUtil {
    public static Date StringToDateTime(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(parse.getTime());
    }
}
