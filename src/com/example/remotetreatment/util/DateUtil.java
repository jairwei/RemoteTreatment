package com.example.remotetreatment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

public class DateUtil {

	public static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String DEFAULT_DATETIME_FORMAT1 = "MM-dd HH:mm";
	public static String DEFAULT_DATETIME_FORMAT_FILE = "yyyy-MM-dd_HH.mm.ss.SSS";
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static String DEFAULT_SIMPLE_DATE_FORMAT = "MM-dd";
	public static String DEFAULT_SIMPLE_TIME_FORMAT = "HH:mm";
	public static String DEFAULT_SIMPLE_HOUR_FORMAT = "HH";
	public static String CHINESE_DATE_FORMAT = "yyyy年MM月dd日";

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}

	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String getDatetime() {
		return getDatetime(new Date());
	}

	public static String getDatetime(long ms) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
		return sdf.format(new Date(ms));
	}

	public static String getSimpleDatetime(long ms) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT1);
		return sdf.format(new Date(ms));
	}

	public static String getDatetime(Date d) {
		return getDatetime(d.getTime());
	}

	public static String getSimpleDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_SIMPLE_DATE_FORMAT);
		return sdf.format(date);
	}

	public static String getSimpleDateFile() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_FILE);
		return sdf.format(new Date());
	}

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(new Date());
	}

	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);
	}

	public static String getChineseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CHINESE_DATE_FORMAT);
		return sdf.format(date);
	}

	public static String getSimpleTime(String datetime) {
		if (TextUtils.isEmpty(datetime)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_SIMPLE_TIME_FORMAT);
		return sdf.format(parseDatetimeToDate(datetime));
	}

	public static String getSimpleTime(long time) {
		if (time <= 0) {
			return "";
		}
		Date date = new Date();
		date.setTime(time);
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_SIMPLE_TIME_FORMAT);
		return sdf.format(date);
	}

	public static Date parseDate(String d) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			return sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDatetime(String d) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
			return sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getHour(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_SIMPLE_HOUR_FORMAT);
			return Integer.parseInt(sdf.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long parseDatetimeToTime(String datetime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
			Date d = sdf.parse(datetime);
			return d.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Date parseDatetimeToDate(String datetime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
			return sdf.parse(datetime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date add(Date date, int type, int time) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(type, time);
			return new Date(cal.getTime().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int diffDate(Date date1, Date date2) throws ParseException {
		if (date1 == null || date2 == null) {
			return 0;
		}

		long ca = date1.getTime() - date2.getTime();
		if (ca <= 0) {
			return 0;
		}

		return (int) (ca / 1000 / 60 / 60 / 24);
	}
}
