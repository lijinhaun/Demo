package com.lijinhuan.service;

import java.util.Map;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lijinhuan.base.Iservice;
import com.lijinhuan.mapper.TestMapper;

@Service
public class TestService implements Iservice{

	@Autowired
	private TestMapper testMapper;
	
	public Map<String, Object> doProcess(Map<String,Object> reqMap) {
		 String id = (String) reqMap.get("id");
		 com.lijinhuan.model.Test test = (com.lijinhuan.model.Test) testMapper.queryById(Integer.valueOf(id));
		 reqMap.put("resp", test);
		 return reqMap;
	}

}
