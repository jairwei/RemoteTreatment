package com.example.remotetreatment.model;

import java.io.Serializable;

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

	private boolean isShowDetail;

	public static Doctor fromTest(int i) {
		Doctor doc = new Doctor();

		doc.setId(i);
		doc.setName("doctor" + i);
		doc.setTitle("title" + i);
		doc.setHospital("hostpital" + i);
		doc.setDept("dept" + 1);
		doc.setEducation("education" + 1);
		doc.setExpert("expert" + 1);
		doc.setRegistFee(10);
		doc.setStar(i);
		doc.setPartTime("暂无");
		doc.setIntro("简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介");

		return doc;
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

	public boolean isShowDetail() {
		return isShowDetail;
	}

	public void setShowDetail(boolean isShowDetail) {
		this.isShowDetail = isShowDetail;
	}
}
