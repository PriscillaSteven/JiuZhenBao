package com.mall.jiuzhenbao.utils;

import com.mall.jiuzhenbao.message.ISpogMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Date utility 
 * @author Neo.Li
 */
@Component
public class DateUtils {
	
	@Autowired
	private ISpogMessages spogMessages;
	
	private enum DateFormat{
		TIME_FORMAT("time_format"),
		DATE_FORMAT("date_format"),
		DATETIME_FORMAT("dateTime_format");
		
		private String value;
		
		DateFormat(String value){
			this.value = value;
		}
		
		String getValue() {
			return this.value;
		}
	}
	/**
	 * Format input date to custom date time String
	 * @param date
	 * @return
	 */
	public String getDateTimeFormatDate(Date date) {
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), getUserZoneId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getFormat(DateFormat.DATETIME_FORMAT));
		return ldt.format(formatter);
	}
	
	public Date getDateTimeFormatDateFromString(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat(getFormat(DateFormat.DATETIME_FORMAT));
		try {
			return  formatter.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Format input date to custom date String
	 * @param date
	 * @return
	 */
	public String getDateFormatDate(Date date) {
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), getUserZoneId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getFormat(DateFormat.DATE_FORMAT));
		return ldt.format(formatter);
	}
	
	/**
	 * Format input date to custom time String
	 * @param date
	 * @return
	 */
	public String getTimeFormatDate(Date date) {
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), getUserZoneId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getFormat(DateFormat.TIME_FORMAT));
		return ldt.format(formatter);
	}
	
	/**
	 * Get UTC now time by system default time zone
	 * @return
	 */
	public static Date getSystemUTCNowTime() {
		return Date.from(LocalDateTime.now(ZoneOffset.UTC).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * The method will return custom date format
	 * If user is not set format, return system properties date format.
	 * @param dateFormat
	 * @return
	 */
	private String getFormat(DateFormat dateFormat) {
		// TODO Get user format
		String dateTimeFormat = spogMessages.getMessage(dateFormat.getValue());
		return dateTimeFormat;
	}
	
	/**
	 * The method will return custom time zone.
	 * If user is not set time zone, Return system default zone.
	 * @return
	 */
	private ZoneId getUserZoneId() {
		// TODO Get user zoneId
		return ZoneId.systemDefault();
	}
	/**
	 * This method is used to change local time to UTC time when get Date from UI
	 * Convert local time to UTC time
	 * @param localDate
	 * @return
	 */
	public static Date toUTCTime(Date localDate) {
		if(localDate == null){
			return null;
		}
		LocalDateTime ldt = LocalDateTime.ofInstant(localDate.toInstant(), ZoneOffset.UTC);
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * This method is used to change UTC time to local time before return Date to UI
	 * Convert UTC time to local time
	 * @param utcDate
	 * @return
	 */
	public static Date fromUTCTime(Date utcDate) {
		if(utcDate == null){
			return null;
		}
		LocalDateTime ldt = LocalDateTime.ofInstant(utcDate.toInstant(), ZoneOffset.systemDefault());
		return Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
	}

	/**
	 * build the date for time range with specified Date
	 * isBefore = true means: take the date by baseDate forward with timeRangeLimited
	 * isBefore = false means: take the date by baseDate backward with timeRangeLimited
	 * @param baseDate
	 * @param isBefore
	 * @param timeRangeLimited
	 * @return target Date
	 */
	public static Date getSpecifiedDayAfterOrBefore(Date baseDate, Boolean isBefore, long timeRangeLimited){
		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		int day = c.get(Calendar.DAY_OF_YEAR);
		if (isBefore){
			//for source time range is 180 days
			//for organization time range is 30 days
			c.set(Calendar.DAY_OF_YEAR, (int) (day - timeRangeLimited/(60*60*24)));
		}else{

			long differenceBetweenCurrentUTCTimeAndSprcifiedTime = (getSystemUTCNowTime().getTime()-baseDate.getTime())/(60*60*24);
			long afterDate = differenceBetweenCurrentUTCTimeAndSprcifiedTime<(timeRangeLimited/(60*60*24))?differenceBetweenCurrentUTCTimeAndSprcifiedTime:timeRangeLimited/(60*60*24);
			//for source time range is 180 days
			//for organization time range is 30 days
			c.set(Calendar.DAY_OF_YEAR,(int) (day + afterDate));
		}

		return c.getTime();
	}
	
	public static Date convertSecondToMilliSecond(Date date){
		if(date == null) return null;
		date.setTime(date.getTime() * 1000);
		return date;
	}
	
	public static Date convertMilliSecondToSecond(Date date){
		if(date == null) return null;
		date.setTime(date.getTime() / 1000);
		return date;
	}

	public static Date convertFromUTCTimeAndToSecond(Date date){
		return convertMilliSecondToSecond(fromUTCTime(date));
	}
	
	public static Date convertToMilliSecondAndToUTCTime(Date date){
		return toUTCTime(convertSecondToMilliSecond(date));
	}

	/**
	 * To build a time forward from now on for 24 hours.
	 * @param baseDate
	 * @return
	 */
	public static Date timeForward24Hours(Date baseDate){
		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		c.set(Calendar.HOUR_OF_DAY, (int) (hour - 24));
		return c.getTime();
	}

	/**
	 * To build a time forward from now on for 7 days / 14 days / 1 month / 1 year.
	 * @param baseDate
	 * @return
	 */
	public static Date timeForwardBaseOnRange(Date baseDate, String rangeType){
		if (rangeType == null || baseDate == null){
			//TODO
			return new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(baseDate);
		switch (rangeType){
			case "7D":
				int day = c.get(Calendar.DAY_OF_MONTH);
				c.set(Calendar.DAY_OF_MONTH, (int) (day - 7));
				break;
			case "14D":
				int days = c.get(Calendar.DAY_OF_MONTH);
				c.set(Calendar.DAY_OF_MONTH, (int) (days - 14));
				break;
			case "1M":
				int month = c.get(Calendar.MONTH);
				c.set(Calendar.MONTH, (int) (month - 1));
				break;
			case "3M":
				int month3M = c.get(Calendar.MONTH);
				c.set(Calendar.MONTH, (int) (month3M - 3));
				break;
			case "1Y":
				int year = c.get(Calendar.YEAR);
				c.set(Calendar.YEAR, (int) (year - 1));
				break;
			default:
				break;
		}
		return c.getTime();
	}
}
