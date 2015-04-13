package com.example.remotetreatment.model;

public class Test {
	public String id;
	public String name;

	public Test(String i, String n) {
		id = i;
		name = n;
	}

	public static Test fromTest(int i) {
		Test t = new Test(String.valueOf(i), "name" + i);
		return t;
	}
}
