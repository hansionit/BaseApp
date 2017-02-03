package com.roboocean.baseapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类，包括格式化获取时间，获取当时时、分、秒等方法。
 */
public class DateUtil {
	
	
	/**
	 * 获取时间字符串，格式为：yyyy.mm.dd HH:mm
	 * 
	 * @param date Date类的实例
	 * 
	 * @return dataStr String类的实例
	 */
	public static String getStringFromCurrentTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 获取格式：yyyy年mm月dd日 HH:mm.
	 * 
	 * @param date
	 *            the date
	 * @return the date cn
	 */
	public static String getDateCN(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 获取当前时间的小时数
	 * 
	 * @return int 小时
	 */
	public static int getCurrentHour(){
		long time= System.currentTimeMillis();
		final Calendar mCalendar= Calendar.getInstance();
		mCalendar.setTimeInMillis(time);
		return mCalendar.get(Calendar.HOUR_OF_DAY);

	}
	
	/**
	 * 获取当前时间的分钟
	 * 
	 * @return int 分
	 */
	public static int getCurrentMin(){
		long time= System.currentTimeMillis();
		final Calendar mCalendar= Calendar.getInstance();
		mCalendar.setTimeInMillis(time);
		return mCalendar.get(Calendar.MINUTE);
	}
	
	/**
	 * 获取当前时间的秒
	 * 
	 * @return int 秒
	 */
	public static int getCurrentSec(){
		long time= System.currentTimeMillis();
		final Calendar mCalendar= Calendar.getInstance();
		mCalendar.setTimeInMillis(time);
		return mCalendar.get(Calendar.SECOND);
	}
	
	/**
	 * 分钟转换小时（整数值）
	 * 
	 * @param minuter
	 * @return int 小时
	 */
	public static int minCastToHour(int minuter){
		return minuter/60;
	}
	
	/**
	 * 分钟转换小时（求余值）
	 * 
	 * @param minuter
	 * @return int 求余值
	 */
	public static int minCastToHourMore(int minuter){
		return minuter%60;
	}
	
	/**
	 * 小时转换分钟
	 * 
	 * @param hour
	 * @return int 分钟
	 */
	public static int hourCastToMin(int hour){
		return hour*60;
	}

	// 将秒转化成小时分钟秒
	public static String FormatMiss(int miss) {
		String hh = miss / 3600 > 9 ? miss / 3600 + "" : "0" + miss / 3600;
		String mm = (miss % 3600) / 60 > 9 ? (miss % 3600) / 60 + "" : "0" + (miss % 3600) / 60;
		String ss = (miss % 3600) % 60 > 9 ? (miss % 3600) % 60 + "" : "0" + (miss % 3600) % 60;
		return hh + ":" + mm + ":" + ss;
	}
}
