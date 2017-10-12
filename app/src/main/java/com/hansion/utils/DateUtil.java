package com.hansion.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类，包括格式化获取时间，获取当时时、分、秒等方法。
 */
public class DateUtil {


	/**
	 * 将毫秒值转化为 年-月-日 时：分：秒
	 * @param time
	 * @return
     */
	public static String timeMillis2String(Long time){
		Date d = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		return sdf.format(d);
	}

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

	/**
	 * 计算已经过去的某一年的某一个月的天数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonthPast(int year, int month) {
		if (year <= 0 || month <= 0 || month > 12) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH) + 1;
		int day1 = calendar.get(Calendar.DAY_OF_MONTH);
		if (year > year1) {
			return 0;
		} else if (year == year1 && month > month1) {
			return 0;
		}
		return getDayOfMonth(year, month);
	}

	/**
	 * 返回某一年某一个月的天数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonth(int year, int month) {
		if (year <= 0 || month <= 0 || month > 12) {
			return 0;
		}
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: {
				return 31;
			}
			case 4:
			case 6:
			case 9:
			case 11: {
				return 30;
			}
			case 2: {
				if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
					return 29;
				} else {
					return 28;
				}
			}
			default:
				return 0;
		}
	}

	/**
	 * 检查某一年的某一天存在，避免如2017.2.29
	 * 必须已经经过了
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean checkDatePast(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH) + 1;
		int day1 = calendar.get(Calendar.DAY_OF_MONTH);
		if (year > year1) {
			return false;
		} else if (year == year1) {
			if (month > month1) {
				return false;
			} else if (month == month1) {
				if (day > day1) {
					return false;
				}
			}
		}
		return checkDate(year, month, day);
	}

	public static boolean checkDate(int year, int month, int day) {
		if (year <= 0 || month <= 0 || day <= 0) {
			return false;
		}
		if (month == 1 ||
				month == 3 ||
				month == 5 ||
				month == 7 ||
				month == 8 ||
				month == 10 ||
				month == 12) {
			if (day <= 31) {
				return true;
			} else {
				return false;
			}
		} else if (month == 4 ||
				month == 6 ||
				month == 9 ||
				month == 11) {
			if (day <= 30) {
				return true;
			}
			return false;
		} else {
			if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
				if (day <= 29) {
					return true;
				}
				return false;
			}
			if (day <= 28) {
				return true;
			}
			return false;
		}
	}

}
