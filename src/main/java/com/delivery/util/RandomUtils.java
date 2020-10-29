package com.delivery.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Yvan
 * @Description 生成随机数的工具类
 * @Classname RandomUtils
 * @Date 2020/10/29 14:06
 */
public class RandomUtils {

    private static Random random = new Random();
    /**
     * 获取验证码
     *
     * @param size  长度
     * @param scope 范围
     * @return String
     */
    public static String getCode(int size, int scope) {
        List<Integer> list = new ArrayList<>();
        boolean[] bool = new boolean[scope];
        StringBuilder str = new StringBuilder(size);
        int i = 0;
        while (i < size) {
            int nextInt = random.nextInt(scope);
            if (!bool[nextInt]) {
                str.append(nextInt);
                i++;
                bool[nextInt] = true;
            }
        }
        return str.toString();
    }
}
