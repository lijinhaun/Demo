package com.lijinhuan.test.dao;

import com.lijinhuan.test.model.Test;

public interface TestDao {
	public final static String SERVICENAME="com.lijinhuan.test.dao.impl.TestDaoImpl";
	public void save(Test test);
}
