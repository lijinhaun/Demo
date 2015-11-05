package com.lijinhuan.test.model;

import java.io.Serializable;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class Test implements Serializable{
	Logger logger = Logger.getLogger(Test.class);
	private String id;
	private String testName;
	private int testAge;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getTestAge() {
		return testAge;
	}
	public void setTestAge(int testAge) {
		this.testAge = testAge;
	}
	@Override
	public String toString() {
		return "Test [id=" + id + ", testName=" + testName + ", testAge="
				+ testAge + "]";
	}
}
