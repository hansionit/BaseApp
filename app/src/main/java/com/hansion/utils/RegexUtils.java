package com.hansion.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/4/14 16:40
 */
public  class RegexUtils {

    public static long onlyGetNum(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String num = m.replaceAll("").trim();
        return Integer.parseInt(num);
    }

    /**
     * 判断字符串是否是数字(不包括小数、负数)
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否是数字(包括小数、负数)
     * @param str
     * @return
     */
    public static boolean isNumer(String str){
        Pattern pattern = Pattern.compile("^[-+]?\\d*\\.?\\d*$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 判断一个字符串可能是手机号码
     */
    public static boolean isMobileNum(String mobiles) {
        String regex = "1[3|5|7|8|][0-9]{9}";
        return mobiles.matches(regex);
    }



}
