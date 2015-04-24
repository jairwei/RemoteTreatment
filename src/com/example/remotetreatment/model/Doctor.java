package com.example.remotetreatment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.remotetreatment.util.DateUtil;

public class Doctor implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private int id;
	private String avatar;
	private String name;
	private int star;
	private String title; // 职称
	private String hospital;// 医院
	private String dept;// 科室
	private String education;// 学历
	private int registFee;// 挂号费
	private String expert; // 擅长
	private String partTime;// 社会兼职
	private String intro; // 简介

	private String age;
	private String gender; // M男，F女

	private float score;
	private int comment;
	private HashMap<String, List<String>> dates;

	public static Doctor fromTest(int i) {
		Doctor d = new Doctor();

		d.setId(i);
		d.setName("周**" + i);
		d.setHospital("海总医院");
		d.setDept("神经病科");
		d.setTitle("主任医师");
		d.setEducation("博士");
		d.setExpert("擅长擅长擅长擅长擅长擅长擅长" + i);
		d.setRegistFee(10 * i + 10);
		d.setStar(i);
		d.setPartTime("社会兼职社会兼职社会兼职社会兼职社会兼职社会兼职" + i);
		d.setIntro("简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介" + i);
		d.setScore(i);
		d.setComment(i);

		Date today = new Date();

		Date date1 = DateUtil.add(today, Calendar.DATE, i);
		Date date2 = DateUtil.add(today, Calendar.DATE, i + 1);
		HashMap<String, List<String>> dates = new HashMap<String, List<String>>();

		List<String> times = new ArrayList<String>();
		times.add("19:00-19:15");
		times.add("19:15-19:30");
		times.add("20:45-21:00");
		dates.put(DateUtil.getDate(date1), times);
		dates.put(DateUtil.getDate(date2), times);

		d.setDates(dates);

		return d;
	}

	public List<String> getDateString() {
		if (getDates() == null || getDates().isEmpty()) {
			return null;
		}

		List<String> dateStrings = new ArrayList<String>();
		dateStrings.addAll(getDates().keySet());
		return dateStrings;
	}

	public HashMap<String, List<String>> getDates() {
		return dates;
	}

	public void setDates(HashMap<String, List<String>> dates) {
		this.dates = dates;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExpert() {
		return expert;
	}

	public void setExpert(String expert) {
		this.expert = expert;
	}

	public int getRegistFee() {
		return registFee;
	}

	public void setRegistFee(int registFee) {
		this.registFee = registFee;
	}

	public String getPartTime() {
		return partTime;
	}

	public void setPartTime(String partTime) {
		this.partTime = partTime;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
