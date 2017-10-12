package com.hansion.utils;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/6/29 10:47
 */
public class StringUtils {


    /**
     * 获取随机指定长度的字母组成的字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String randomString = "abcdefghijklmnopqrstuvwxyz";
        int len = randomString.length();
        for (int i = 0; i < length; i++) {
            sb.append(randomString.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    /**
     * 获取count以内的随机数
     * @param count
     * @return
     */
    public static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

}
