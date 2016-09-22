package com.lijinhuan.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lijinhuan.base.Iservice;
@Controller
@RequestMapping("/testAction")
public class TestAction extends AbstractAction{

	@Autowired
	private Iservice testService;
	@ResponseBody
	@RequestMapping("doTest")
	public String doTest(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return execute(request, response, testService, model);
	}
	
	@Override
	public String successView(Map<String, Object> retMap,
			Map<String, Object> otherMap) {
		Gson gson = new Gson();
		String string = gson.toJson(retMap.get("resp"));
		return string;
	}
}
