package com.lijinhuan.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.lijinhuan.common.Constants;

public abstract class AbstractHandler {
	public String execute(HttpServletRequest request,HttpServletResponse response,Iservice service,ModelMap model){
		return execute(request, response, service, model, null);
	}
	public String execute(HttpServletRequest request,HttpServletResponse response,Iservice service,ModelMap model,Map<String,Object> otherMap){
		try {
			//Ԥ����
			Map<String, Object> reqMap = preProcess(request);
			if(otherMap != null){
				reqMap.putAll(otherMap);
			}
			//���ô���service
			Map<String, Object> retMap = process(service,reqMap);
			//��������(�첽֪ͨ����)
			postProcess();
			//��retMap���ݷ���modelMap
			fillModel(retMap);
			//��ʽ����������
			formatSubtleData(model);
			return successView(retMap,otherMap);
		} catch (Exception e) {
			model.addAttribute(Constants.PARAM_RET_CODE, Constants.RET_FAIL);
			model.addAttribute(Constants.PAPRAM_RET_MSG, e.getMessage());
			return "error";
		}
		
	}
	/**
	 * ��ʽ���ֻ��ŵ�������Ϣ
	 * @param model
	 */
	public void formatSubtleData(ModelMap model) {
		
	}
	public abstract String successView(Map<String, Object> retMap, Map<String, Object> otherMap);
	public Map<String,Object> process(Iservice service, Map<String, Object> reqMap){
		Map<String, Object> retMap = null;
		try {
			retMap = service.doProcess(reqMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	public abstract Map<String, Object> preProcess(HttpServletRequest request);
	/**
	 * �첽֪ͨ����
	 */
	public void postProcess() {
		
	}
	/**
	 * retMap���ݷ���model�з���
	 * @param retMap
	 */
	public abstract void fillModel(Map<String, Object> retMap) ;
}
