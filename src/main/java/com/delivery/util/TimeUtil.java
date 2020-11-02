package com.delivery.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    * @author fujianian
     * return String
     * @Date 2020/10/22 9:41
    */
    public static String localtime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static boolean timeReduce(String offWorkTime,String startWorkTime)throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        Date offWork=sdf.parse(offWorkTime);
        Date work=sdf.parse(startWorkTime);
        long time=offWork.getTime()-work.getTime();
        if(time>0){
            return true;
        }else {
            return false;
        }
    }

}
