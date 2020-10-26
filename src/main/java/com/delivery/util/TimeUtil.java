package com.delivery.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Yvan
 * @Description TODO
 * @Classname TimeUtil
 * @Date 2020/10/14 19:04
 */
public class TimeUtil {
    /**
     * 获取当前时间
     * @author fujianian
     * @Date 2020/10/22 9:41
     * @return String
     */
    public static String localtime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    /**
     * 日期减日期
     * @author fujianian
     * @Date 2020/10/26 15:31
     * @return boolean
     */
    public static boolean datedate(Date insteadDate) {
        long nowDate = System.currentTimeMillis();
        long day = 0;
        day = insteadDate.getTime() - nowDate;
        if (day<=0){
            return false;
        }else{
            return true;
        }
    }

}
