package com.thanos.common;
/**
 * Title: gcore-studio 
 * Description: gcore-studio 
 * Copyright: Copyright (c) 2006
 * Company: genecore
 * 
 * @author date13
 * @version 1.0
 * @Last Modify
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Title: dcicn-i-studio Description: dcicn-i-studio Copyright: Copyright (c)
 * 2006 Company: dcicn
 * 
 * @author date13
 * @version 1.01
 * @Last Modify 2006-3-29
 */
public class DatetimeUtil {
	public static String DATETIME_OUTPATTERN = "yyyy-MM-dd HH:mm:ss";// 日期加时间的默认输出模式

	public static String TIME_OUTPATTERN = "HH:mm:ss";// 时间的默认输出模式

	public static String DATE_OUTPATTERN = "yyyy-MM-dd";// 日期的默认输出模式

	public final static String DATE_INPATTERN = "yyyy-MM-dd";// 时间的默认解析模式,暂不提供其他解析模式

	public final static String DATETIME_INPATTERN = "yyyy-MM-dd HH:mm:ss";// 时间加日期的默认解析模式,暂不提供其他解析模式

	public final static String TIME_INPATTERN = "HH:mm:ss";// 时间的默认解析模式,暂不提供其他解析模式

	public static String TIME_ZONE = "Asia/Shanghai";// 默认时区,暂不提供设置

	public static Locale COUNTRY = Locale.CHINESE;// 默认国家,暂不提供设置

    public static String DATETIME_14_PATTERN = "yyyyMMddHHmmss";// 14位时间模式

	public static String DATE_08_PATTERN = "yyyyMMdd";

	/**
	 * 得到当前日期和时间,输出模式为默认
	 * 
	 * @return String
	 */
	public static String getDateTime(){
		try{
			return getDateTimePattern(null, null);
		}catch(ParseException e){
			return "";
		}
	}

	/**
	 * 得到指定的日期和时间,输出模式为默认
	 * 
	 * @param String
	 *            指定的日期和时间
	 * @return String
	 */
	public static String getDateTime(String dateTime) {
		try{
			return getDateTimePattern(dateTime, null);
		}catch(ParseException e){
			
			return dateTime;
		}
	}
	
	/**
	 * 得到当前日期和时间,指定输出模式
	 * 
	 * @return String
	 */
	public static String getDateTimePattern(String pattern) throws ParseException {
		return getDateTimePattern(null, pattern);
	}
	/**
	 * 得到指定的日期和时间,可指定输出模式
	 * 
	 * @param String
	 *            指定的日期和时间
	 * @param String
	 *            指定的输出模式
	 * @return String
	 * @exception ParseException
	 */
	public static String getDateTimePattern(String dateTime, String pattern)
			throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (pattern == null || pattern.equals("")) {
			pattern = DATETIME_OUTPATTERN;
		}
		if (dateTime != null && !dateTime.equals("")) {
			c.setTime(new SimpleDateFormat(DATETIME_INPATTERN).parse(dateTime));
		}
		return (new SimpleDateFormat(pattern)).format(c.getTime());
	}

	/**
	 * 得到当前日期,输出模式为默认
	 *
	 * @return String
	 */
	public static String getDate(){
		try{
			return getDatePattern(null, null);
		}catch(ParseException e){
			return "";
		}
	}

	/**
	 * 得到指定的日期,输出模式为默认
	 *
	 * @param String
	 *            指定的日期
	 * @return String
	 */
	public static String getDate(String date) {
		try{
			return getDatePattern(date, null);
		}catch(ParseException e){
			return date;
		}
	}
	/**
	 * 得到当前日期,指定输出模式
	 *
	 * @return String
	 */
	public static String getDatePattern(String pattern) throws ParseException {
		return getDatePattern(null,pattern);
	}

	/**
	 * 得到指定的日期,可指定输出模式
	 *
	 * @param String
	 *            指定的日期
	 * @param String
	 *            指定的输出模式
	 * @return String
	 * @exception ParseException
	 */
	public static String getDatePattern(String date, String pattern)
			throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (pattern == null || pattern.equals("")) {
			pattern = DATE_OUTPATTERN ;
		}
		if (date != null && !date.equals("")) {
			c.setTime(new SimpleDateFormat(DATE_INPATTERN).parse(date));
		}
		return (new SimpleDateFormat(pattern)).format(c.getTime());
	}

	/**
	 * 得到当前时间,输出模式为默认
	 *
	 * @return String
	 */
	public static String getTime(){
		try{
			return getTimePattern(null, null);
		}catch(ParseException e){
			return "";
		}
	}

	/**
	 * 得到指定的时间,输出模式为默认
	 *
	 * @param String
	 *            指定的时间
	 * @return String
	 * @exception ParseException
	 */
	public static String getTime(String time){
		try{
			return getTimePattern(time, null);
		}catch(ParseException e){
			return time;
		}
	}
	/**
	 * 得到当前时间,指定输出模式
	 *
	 * @return String
	 */
	public static String getTimePattern(String pattern) throws ParseException {
		return getTimePattern(null, pattern);
	}
	/**
	 * 得到指定的时间,可指定输出模式
	 *
	 * @param String
	 *            指定的时间
	 * @param String
	 *            指定的输出模式
	 * @return String
	 * @exception ParseException
	 */
	public static String getTimePattern(String time, String pattern)
			throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (pattern == null || pattern.equals("")) {
			pattern = TIME_OUTPATTERN;
		}
		if (time != null && !time.equals("")) {
			c.setTime(new SimpleDateFormat(TIME_INPATTERN).parse(time));
		}
		return (new SimpleDateFormat(pattern)).format(c.getTime());
	}

	/**
	 * 得到当前年
	 *
	 * @return int
	 */
	public static int getYear(){
		try{
			return getYear(null);
		}catch(ParseException e){
			return -1;
		}
	}

	/**
	 * 得到指定日期的年
	 *
	 * @param String
	 *            指定的日期
	 * @return int
	 * @exception ParseException
	 */
	public static int getYear(String date) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (date != null && !date.equals("")) {
			c.setTime(new SimpleDateFormat(DATE_INPATTERN).parse(date));
		}
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到当前月
	 *
	 * @return int
	 */
	public static int getMonth(){
		try{
			return getMonth(null);
		}catch(ParseException e){
			return -1;
		}
	}

	/**
	 * 得到指定日期的月
	 *
	 * @param String
	 *            指定的日期
	 * @return int
	 * @exception ParseException
	 */
	public static int getMonth(String date) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (date != null) {
			c.setTime(new SimpleDateFormat(DATE_INPATTERN).parse(date));
		}
		return c.get(Calendar.MONTH) + 1;
	}

	public static int getMonth(String date, String format) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (date != null) {
			c.setTime(new SimpleDateFormat(format).parse(date));
		}
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前天
	 *
	 * @return int
	 */
	public static int getDay(){
		try{
			return getDay(null);
		}catch(ParseException e){
			return -1;
		}
	}

	/**
	 * 得到指定日期的天
	 *
	 * @param String
	 *            指定的日期
	 * @return int
	 * @exception ParseException
	 */
	public static int getDay(String date) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (date != null) {
			c.setTime(new SimpleDateFormat(DATE_INPATTERN).parse(date));
		}
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到当前小时
	 *
	 * @return int
	 */
	public static int getHour() {
		try{
			return getHour(null);
		}catch(ParseException e){
			return -1;
		}
	}

	/**
	 * 得到指定日期的小时
	 *
	 * @param String
	 *            指定的时间
	 * @return int
	 * @exception ParseException
	 */
	public static int getHour(String time) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (time != null) {
			c.setTime(new SimpleDateFormat(TIME_INPATTERN).parse(time));
		}
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到当前分钟
	 *
	 * @return int
	 */
	public static int getMinute(){
		try{
			return getMinute(null);
		}catch(ParseException e){
			return -1;
		}
	}

	/**
	 * 得到指定日期的分钟
	 *
	 * @param String
	 *            指定的时间
	 * @return int
	 * @exception ParseException
	 */
	public static int getMinute(String time) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (time != null) {
			c.setTime(new SimpleDateFormat(TIME_INPATTERN).parse(time));
		}
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 得到当前秒
	 *
	 * @return int
	 */
	public static int getSecond(){
		try{
			return getSecond(null);
		}catch(ParseException e){
			return -1;
		}
	}

	/**
	 * 得到指定日期的秒
	 *
	 * @param String
	 *            指定的时间
	 * @return int
	 * @exception ParseException
	 */
	public static int getSecond(String time) throws ParseException {
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (time != null) {
			c.setTime(new SimpleDateFormat(TIME_INPATTERN).parse(time));
		}
		return c.get(Calendar.SECOND);
	}
	
	public static GregorianCalendar getDateInstance(String date){
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (date != null && !date.equals("")) {
			try {
				c.setTime(new SimpleDateFormat(DATE_OUTPATTERN).parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public static GregorianCalendar getDateTimeInstance(String datetime){
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		if (datetime != null && !datetime.equals("")) {
			try {
				c.setTime(new SimpleDateFormat(DATETIME_OUTPATTERN).parse(datetime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public static String dateAsString(GregorianCalendar c){
		if(c != null){
			return c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH);	
		}else{
			return null;
		}
	} 
	
	public static String dateAsString(Date date){
		return dateAsString(date,null);
	}
	
	public static String dateAsString(Date date,String pattern){
		if (pattern == null || pattern.equals("")) {
			pattern = DATE_OUTPATTERN ;
		}
		if(date != null && !date.equals("")){
			return (new SimpleDateFormat(pattern)).format(date);
		}else{
			return null;
		}
	}
	
	public static Date stringToDateWithStart(String dateTime){
		try {
			return stringToDate(dateTime+" 00:00:00",DATETIME_INPATTERN);
		} catch (Exception e) {
			Console.writeErr(e);
		}
		return null;
	}
	
	public static Date stringToDateWithEnd(String dateTime){
		try {
			return stringToDate(dateTime+" 23:59:59",DATETIME_INPATTERN);
		} catch (Exception e) {
			Console.writeErr(e);
		}
		return null;
	}
	
	public static Date stringToDate(String date){
		try {
			return stringToDate(date,DATE_INPATTERN);
		} catch (Exception e) {
			Console.writeErr(e);
		}
		return null;
	}
	
	public static Calendar stringToCalendar(String date){
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		try {
			c.setTime(stringToDate(date,DATE_INPATTERN));
			return c;
		} catch (Exception e) {
			Console.writeErr(e);
		}
		return null;
	}
	
	public static Calendar stringToCalendar(String date,String pattern){
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		try {
			c.setTime(stringToDate(date,pattern));
			return c;
		} catch (Exception e) {
			Console.writeErr(e);
		}
		return null;
	}
	
	public static Date stringToDate(String dateTime,String pattern) {
		try {
			if (pattern == null || pattern.equals("")) {
                pattern = DATETIME_INPATTERN ;
			}
			if (dateTime != null && !dateTime.equals("")) {
				return new SimpleDateFormat(pattern).parse(dateTime);
			}
		} catch (Exception e) {
			Console.writeErr(e);
		}
		
		return null;
	}
	
	public static Date stringToDateTime(String dateTime){
		try {
			return stringToDate(dateTime,DATETIME_INPATTERN);
		} catch (Exception e) {
			Console.writeErr(e);
		}
		return null;
	}
	
	public static String dateTimeAsString(Date date){
		return dateTimeAsString(date,null);
	}
	
	public static String dateTimeAsString(Date date,String pattern){
		if (pattern == null || pattern.equals("")) {
			pattern = DATETIME_OUTPATTERN ;
		}
		if(date != null){
			return (new SimpleDateFormat(pattern)).format(date);
		}else{
			return null;
		}
	}
	
	public static int compareToCurrent(String dateTime,String pattern) throws ParseException{
		return new Date().compareTo(stringToDate(dateTime,pattern));
	}
	
	public static int compareToCurrent(String dateTime) throws ParseException{
		return new Date().compareTo(stringToDate(dateTime,null));
	}
	
	public static int compareToCurrent(Date dateTime) throws ParseException{
		return new Date().compareTo(dateTime);
	}
	
	public static int compareToDate(String date1,String date2) throws ParseException{
		return stringToDate(date1,null).compareTo(stringToDate(date2,null));
	}
	
	//比较两个日期的大小,指定模式
	public static int compareToDate(String date1,String date2,String pattern) throws ParseException{
		return stringToDate(date1,pattern).compareTo(stringToDate(date2,pattern));
	}
	
	public static long dateDiff(String date1,String date2) throws ParseException{
		return (stringToDate(date1,null).getTime() - stringToDate(date2,null).getTime())/60/60/1000/24;
	}
	
	public static long dateDiff(String date1,String date2,String pattern) throws ParseException{
		return (stringToDate(date1,pattern).getTime() - stringToDate(date2,pattern).getTime())/60/60/1000/24;
	}
	
	public static Calendar convertDate(Date date){
		GregorianCalendar c = new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
		c.setTime(date);
		return c;
	}
	
	public static Calendar getCurCalendar(){
		return new GregorianCalendar(TimeZone
				.getTimeZone(TIME_ZONE), COUNTRY);
	}

	public static Date dateAdd(Date date, int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR,days);
		return cal.getTime();
	}

	/**
	 *
	 * @param time 秒转时间: 00:00:00
	 * @return
     */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return null;
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	private static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 使用的例子
	 */
	public static void main(String[] args) {
		try {
			System.out.println(DatetimeUtil.getDateTime());
			System.out.println(DatetimeUtil.getDateTimePattern("yyyy年MM月dd日 HH时mm分ss秒 a"));
			System.out.println(DatetimeUtil.getDateTimePattern("2006-1-1 12:11:12",
					"yyyy年MM月dd日 HH时mm分ss秒 a"));
			System.out.println(DatetimeUtil.getDateTimePattern("2006-1-1 12:11:12",
			"yyyy@@@@@@MM@@dd@@"));
			System.out.println(DatetimeUtil.getDateTimePattern("2006-1-1 12:11:12",
			"yyyy年MM月dd日 HH时mm分ss秒 a"));
			System.out.println(DatetimeUtil.getDate());
			System.out.println(DatetimeUtil.getDate("2006-3-21"));
			System.out.println(DatetimeUtil.getDatePattern("2006-3-21",
					"EEE, d MMM yyyy HH:mm:ss Z"));
			System.out.println(DatetimeUtil.getTime());
			System.out.println(DatetimeUtil.getTime("15:21:11"));
			System.out.println(DatetimeUtil.getTimePattern("10:21:11", "a"));
			System.out.println(DatetimeUtil.getYear());
			System.out.println(DatetimeUtil.getYear("2006-01-01"));
			System.out.println(DatetimeUtil.getMonth());
			System.out.println(DatetimeUtil.getMonth("2006-03-01"));
			System.out.println(DatetimeUtil.getDay());
			System.out.println(DatetimeUtil.getDay("2006-01-01"));
			Calendar c = DatetimeUtil.getCurCalendar();
			c.add(Calendar.MINUTE, 1);
			System.out.println(c);
			System.out.println(DatetimeUtil.compareToCurrent("2005-01-01 12:00:00"));
			//System.out.println(DateTimeUtil.manageDateTime(DateTimeUtil.getDateTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}
}
