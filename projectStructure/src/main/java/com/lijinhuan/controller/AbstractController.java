package com.lijinhuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lijinhuan.base.AbstractHandler;

public class AbstractController extends AbstractHandler{

	@Override
	public String successView(Map<String, Object> retMap,
			Map<String, Object> otherMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> preProcess(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fillModel(Map<String, Object> retMap) {
		// TODO Auto-generated method stub
		
	}

}
