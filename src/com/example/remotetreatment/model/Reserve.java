package com.example.remotetreatment.model;

import java.io.Serializable;

public class Reserve implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private int id;
	private long time;
	private Doctor doctor;
	private int status = STATUS_RESERVE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
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

	public static final int STATUS_RESERVE = 0;// 进行中
	public static final int STATUS_NOT_PAY = 1;// 尚未支付
	public static final int STATUS_COMPLATE = 2;// 已成功就诊
	public static final int STATUS_CANCEL = 3;// 已取消预约
}
