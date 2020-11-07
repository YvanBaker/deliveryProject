package com.delivery.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LEO15 on 2020/11/3.
 */
public class CutAddressUtil {

public static Map<String, String> addressResolution(String address){
    String regex="(?<province>[^省]+省|[^市]+市|.+自治区)(?<city>[^自治州]+自治州|[^市]+市|[^盟]+盟|[^地区]+地区|.+区划)(?<county>[^市]+市|[^县]+县|[^旗]+旗|.+区)?(?<town>[^街道]+街道|.+镇)?(?<village>.*)";
    Matcher m= Pattern.compile(regex).matcher(address);
    String province=null,city=null,county=null,town=null,village=null;
    Map<String,String> row=null;
    while(m.find()){
        row=new LinkedHashMap<String,String>();
        county=m.group("county");
        row.put("areas", county==null?"":county.trim());
        city=m.group("city");
        row.put("city", city==null?"":city.trim());
        province=m.group("province");
        row.put("prov", province==null?"":province.trim());
        town=m.group("town");
        row.put("town",town==null?"":town.trim());
        village=m.group("village");
        row.put("village",town==null?"":village.trim());
    }
    return row;
}
}

