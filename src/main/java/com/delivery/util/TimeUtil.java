package com.delivery.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yvan
 * @Description TODO
 * @Classname TimeUtil
 * @Date 2020/10/14 19:04
 */
public class TimeUtil {
    /**
    * @author fujianian
     * return String
     * @Date 2020/10/22 9:41
    */
    public static String localtime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

}
