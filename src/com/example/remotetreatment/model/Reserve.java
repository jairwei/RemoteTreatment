package com.example.remotetreatment.model;

import java.io.Serializable;

public class Reserve implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private int id;
	private String time;
	private Doctor doctor;
	private int status = STATUS_RESERVE;

	public static Reserve fromTest(int status, int i) {
		Doctor d = new Doctor();
		d.setName("周**" + i);

		Reserve reserve = new Reserve();
		reserve.setId(i);
		reserve.setTime("2015-10-12 12:12-13:13");
		reserve.doctor = d;
		reserve.status = 1;
		return reserve;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static final int STATUS_ALL = 0;// 全部
	public static final int STATUS_RESERVE = 1;// 进行中
	public static final int STATUS_NO_PAY = 2;// 尚未支付
	public static final int STATUS_COMPLATE = 3;// 已成功就诊
	public static final int STATUS_CANCEL = 4;// 已取消预约
}
