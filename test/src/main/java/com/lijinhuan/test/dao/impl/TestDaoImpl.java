package com.lijinhuan.test.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lijinhuan.test.dao.TestDao;
import com.lijinhuan.test.mapper.TestMapper;
import com.lijinhuan.test.model.Test;
@Component(TestDao.SERVICENAME)
public class TestDaoImpl implements TestDao{

	@Autowired
	private TestMapper testMapper;
	
	public void save(Test test) {
		testMapper.save(test);
	}
}
