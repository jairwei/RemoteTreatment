package com.example.remotetreatment.model;

import java.io.Serializable;

public class Report implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String time;

	public static Report fromTest(int i) {
		Doctor d = new Doctor();
		d.setName("周**" + i);

		Report report = new Report();
		report.setId(i);
		report.setTime("2015-10-12 12:12-13:13");
		report.setTitle("血常规" + i);
		return report;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
