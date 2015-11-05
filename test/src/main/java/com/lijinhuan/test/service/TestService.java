package com.lijinhuan.test.service;

import java.io.InputStream;

import com.lijinhuan.test.model.Test;

public interface TestService {

	public static final String SERVICENAME = "com.lijinhuan.service.impl.TestServiceImpl";
	public void save(Test test);
	public void upload(InputStream in,String filename);
}
