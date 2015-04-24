package com.example.remotetreatment.model;

import java.io.Serializable;

public class Presc implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private int id;
	private String time;
	private Doctor doctor;
	private String result;

	public static Presc fromTest(int i) {
		Doctor d = new Doctor();
		d.setName("周**" + i);

		Presc presc = new Presc();
		presc.setId(i);
		presc.setTime("2015-10-12 12:12-13:13");
		presc.setDoctor(d);
		presc.setResult("没啥毛病" + i);
		return presc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
