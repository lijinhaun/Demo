package com.xzsoft.xip.attachment;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ClassName:HttpAttServerListener
 * Function: TODO
 *
 * @author   guoxf
 * @version  Ver 1.0
 * @since    Ver 1.0
 * @Date	 2014年7月15日下午3:44:27
 *
 */
public class HttpAttServerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		String realPath = sc.getRealPath(File.separator);
		File configFile = new File(realPath.concat(File.separator).concat("WEB-INF").concat(File.separator).concat("attServerConfig.xml"));
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(configFile);
			Element root = doc.getRootElement();
			String userName = root.element("userName").getText();
			String password = root.element("password").getText();
			String basePath = root.element("basePath").getText();
			String maxFileSize = root.element("maxFileSize").getText();
			if(basePath==null || "".equals(basePath.trim())){
				basePath = realPath.concat("attachment").concat(File.separator);
			}
			AttServerConfigBean.setUserName(userName);
			AttServerConfigBean.setPassword(password);
			AttServerConfigBean.setAttachmentPath(basePath);
			AttServerConfigBean.setMaxFileSize(Integer.parseInt(maxFileSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
