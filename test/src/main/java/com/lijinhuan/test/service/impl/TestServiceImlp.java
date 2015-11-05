package com.lijinhuan.test.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.Header;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lijinhuan.test.dao.TestDao;
import com.lijinhuan.test.model.Test;
import com.lijinhuan.test.service.TestService;
import com.mysql.jdbc.log.Log;
@Service(TestServiceImlp.SERVICENAME)
public class TestServiceImlp implements TestService{
	private static final Logger logger = Logger.getLogger(TestServiceImlp.class);
	
	@Autowired
	private TestDao testDao;
	
	public void save(Test test) {
		testDao.save(test);
		CloseableHttpClient client = HttpClients.createDefault();
	}
	public void upload(InputStream in,String fileName){
		HttpPost post = new HttpPost("http://localhost:8080/testHttp/demo?username=lijinhuan&password=ljh910618&type=upload&fileName="+fileName);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.addBinaryBody(fileName, in);
		post.setEntity(multipartEntityBuilder.build());
		CloseableHttpClient http = new DefaultHttpClient();
		CloseableHttpResponse response = null;
		try {
			response = http.execute(post);
			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
				HttpEntity httpEntity = response.getEntity();
				if(httpEntity == null){
					System.out.println("服务器上传失败");
					logger.info("服务器上传失败");
				}
			}else{
				logger.info("服务器连接失败");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
	}

}
