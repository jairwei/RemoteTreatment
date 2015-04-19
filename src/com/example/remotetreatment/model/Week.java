package com.example.remotetreatment.model;

public class Week {
	private int weekday;// 星期几
	private int date;// 日期
	private boolean isHeader;//
	private boolean isInArea;// 是否在30天内
	private boolean hasData;// 是否有数据
	private String dateString;

	public static Week getHeader(int weekday) {
		Week week = new Week();
		week.setWeekday(weekday);
		week.isHeader = true;
		return week;
	}

	@Override
	public String toString() {
		String str = "";
		str += "dateString=" + dateString + ",";
		str += "weekday=" + weekday + ",";
		str += "date=" + date + ",";
		str += "isHeader=" + isHeader + ",";
		str += "isInArea=" + isInArea;
		return str;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

//	public boolean isInArea() {
//		return isInArea;
//	}
//
//	public void setInArea(boolean isInArea) {
//		this.isInArea = isInArea;
//	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
}
