package com.delivery.util;

/**
 * @author Yvan
 * @Description 类型工具类
 * @Classname TypeUtil
 * @Date 2020/10/18 16:19
 */
public class TypeUtil {

    private final static String SPACE = " ";

    /**
     * 是否是有效的字符串（null 或 空 false）
     *
     * @param input 输入
     * @return true
     */
    public static boolean isValidString(String input) {
        input = input.replaceAll("^[\\s\\u3000]{1,}([^\\s\\u3000].*[^\\s\\u3000])[\\s\\u3000]{1,}$", "$1");
        return input != null && !"".equals(input);
    }
}
