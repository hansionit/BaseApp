package com.hansion.utils;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/3/17 15:43
 */
public class NoFastClickUtils {

    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    private static long lastClickTime_short;
    public static boolean isFastDoubleClick_short() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime_short;
        if ( 0 < timeD && timeD < 200) {
            return true;
        }
        lastClickTime_short = time;
        return false;
    }


    private static long lastClickTime_long;
    public static boolean isFastDoubleClick_long() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime_long;
        if (0 < timeD && timeD < 3000) {
            return true;
        }
        lastClickTime_long = time;
        return false;
    }
}
