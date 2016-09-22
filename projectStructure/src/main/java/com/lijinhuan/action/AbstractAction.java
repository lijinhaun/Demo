package com.lijinhuan.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lijinhuan.base.AbstractHandler;
import com.lijinhuan.util.HttpUtils;

public class AbstractAction extends AbstractHandler{

	@Override
	public String successView(Map<String, Object> retMap,
			Map<String, Object> otherMap) {
		return null;
	}
	
	@Override
	public Map<String, Object> preProcess(HttpServletRequest request) {
		Map<String, Object> reqMap = HttpUtils.getRequestParameters(request);
		return reqMap;
	}

	@Override
	public void fillModel(Map<String, Object> retMap) {
	}

}
