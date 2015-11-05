package com.lijinhuan.test.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lijinhuan.test.model.Test;
import com.lijinhuan.test.service.TestService;

@Controller
@RequestMapping("/testAction.do")
public class TestHandler {
	Logger logger = Logger.getLogger(TestHandler.class);
			
	@Autowired
	private TestService testService;		
	public TestHandler(){
		System.out.println("aaaa----------------------------------");
	}
	@RequestMapping(params="method=uploadPage")
	public String toFileUploadPage(){
		return "/WEB-INF/jsp/fileUpload";
	}
	
	@RequestMapping(params="method=upload")
	public void upload(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 if(isMultipart){
			 DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			 ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			 servletFileUpload.setHeaderEncoding("utf-8");
			 try {
				java.util.List<FileItem> list = (java.util.List<FileItem>) servletFileUpload.parseRequest(request);
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					FileItem fileItem = (FileItem) iterator.next();
					try {
						System.out.println(URLDecoder.decode(fileItem.getName(), "UTF-8"));
						testService.upload(fileItem.getInputStream(),URLDecoder.decode(fileItem.getName(), "UTF-8"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			 } catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
	}
	
	@RequestMapping(params="method=test")
	public String test(){
		Test test = new Test();
		test.setId(UUID.randomUUID().toString());
		test.setTestName("test1");
		test.setTestAge(1);
		testService.save(test);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8080/testHttp/demo");
		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		String res ="";
		try {
			res = EntityUtils.toString(entity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res+"-------------");
		return "index";
	}
}
