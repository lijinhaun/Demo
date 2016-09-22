package com.lijinhuan.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
public class HttpUtils {

	/**
	 * 把HTTP中request的参数放入map
	 * @param req
	 * @return
	 */
	public static Map<String, Object> getRequestParameters(HttpServletRequest req){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		Map requestParameters = req.getParameterMap();
		for (Iterator iterator = requestParameters.keySet().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			String[] values =  (String[]) requestParameters.get(name);
			String valueStr = ""; 
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length-1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			paraMap.put(name, valueStr);
		}
		return paraMap;
	}
}
