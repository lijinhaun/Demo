package com.xzsoft.xip.attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;;

/**
 * Http附件应用Servlet类。处理附件上传，下载，删除等请求操作。
 * 
 */
public class XztHtpAttServer extends HttpServlet {
	// 设置文件的存放位置（默认存放在项目的根下）
	private String path = "/";
	// 设置文件的大小
	private int fileSize = 1024 * 1024 * 20;

	private Map fileNames = new HashMap();

	/**
	 * 直接调用doPost
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// super.doGet(req, resp);
		doPost(req, resp);
	}

	/**
	 * 在处理post请求的时候，根据参数op来确定要执行的具体操作，当op为download代表下载附件操作 。为 upload代表上传附件操作。为
	 * delete代表删除操作。
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operation = req.getParameter("op");
		String userName = req.getParameter("user");
		String password = req.getParameter("password");
		String attName = req.getParameter("attName");
		String secCode = req.getParameter("attCat");
		try {
			AttServerConfigBean.checkUser(userName, password);
		} catch (Exception e) {
			resp.setContentType("text/xml");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			/*out.println("<result><flag>0</flag><msg>".concat(e.getMessage())
					.concat("</msg></result>"));*/
			out.print("false");
			out.close();
			return;
		}
		String fileName = AttServerConfigBean.getAttachmentPath()
				.concat(secCode).concat(File.separator)
				.concat(attName);
		// 删除附件
		if ("delete".equals(operation)) {
			File attFile = new File(AttServerConfigBean.getAttachmentPath().concat(secCode).replace('\\', File.separatorChar));
			if (attFile.exists()) {
				File file = new File(fileName);
				boolean fg = file.delete();//判断文件是否删除成功
				if(fg){
					resp.setContentType("text/html");
					resp.setCharacterEncoding("utf-8");
					PrintWriter pw = resp.getWriter();
					pw.println("<result><flag>1</flag><msg>删除附件成功</msg></result>");
					pw.close(); 
				 }else{
					    resp.setContentType("text/html");
						resp.setCharacterEncoding("utf-8");
						PrintWriter pw = resp.getWriter();
						pw.print("false");
						pw.close();
				 }
				
				return;
			}else{
				resp.setContentType("text/html");
				resp.setCharacterEncoding("utf-8");
				PrintWriter pw = resp.getWriter();
				pw.print("false");
				/*pw.println("<result><flag>0</flag><msg>附件不存在无法删除</msg></result>");*/
				pw.close();
				return;
			}
		}
		// 下载附件
		if ("download".equals(operation)) {
			File attFile = new File(fileName);
			if (!attFile.exists()) {
				resp.setContentType("text/html");
				resp.setCharacterEncoding("utf-8");
				PrintWriter pw = resp.getWriter();
				pw.println("<result><flag>0</flag><msg>附件不存，无法下载</msg></result>");
				pw.print("false");
				pw.close();
				return;
			}
			// 读取文件流
			ServletOutputStream out = resp.getOutputStream();
			FileInputStream fis = new FileInputStream(attFile);
			// 下载文件
			// 设置响应头和下载保存的文件名
			resp.setContentType("application/x-msdownload");
			resp.setHeader("Content-Disposition", "attachment; filename="
					+ attName + "");
			// 文件太大时内存不能一次读出,要循环
			byte data[] = new byte[1024];
			while (fis.read(data) > 0) {
				out.write(data);
			}
			fis.close();
			out.close();
		}
		// 上传附件
		if ("upload".equals(operation)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置上传工厂的限制
				factory.setSizeThreshold(fileSize);
				factory.setRepository(new File(AttServerConfigBean
						.getAttachmentPath()));
				// 创建一个上传文件的ServletFileUpload对象
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 设置最大的上传限制 1024*1024*20=20M
				upload.setSizeMax(1024 * 1024 * AttServerConfigBean
						.getMaxFileSize());
				// 处理HTTP请求，items是所有的表单项
				List items = upload.parseRequest(req);
				// 遍历所有的表单项
				for (Iterator it = items.iterator(); it.hasNext();) {
					FileItem item = (FileItem) it.next();
					//因为传过来的文件去掉了扩展名所以需要在普通表单中进行处理
						String name = item.getFieldName();
						/*String value = item.getString("utf-8");
						this.fileNames.put(name, value);*/
						
						   File attFile = new File(AttServerConfigBean.getAttachmentPath().concat(secCode).replace('\\', File.separatorChar));
						    
						    if (!attFile.exists()) {
							   attFile.mkdirs();
              	 		     }
	                       /* if( attFile.exists() ){
	                        	attFile.delete();
	                        }*/
						    
						    File uploadFile = new File(fileName);
							FileOutputStream fos = new FileOutputStream(uploadFile);
							// 如果上传文件域对应文件的内容已经在内存中
							/*if (item.isInMemory()) {
								fos.write(item.get());
							} else {*/
								// 获取上传文件内容的输入流
								InputStream is = item.getInputStream();
								byte[] buffer = new byte[1024];
								int len;
								// 读取上传文件的内容，并将其写入服务器的文件中
								while ((len = is.read(buffer)) > 0) {
									fos.write(buffer, 0, len);
								}
								// 关闭输入流和输出流
								is.close();
								fos.close();
				/*	}*/
				}
			} catch (FileUploadException e) {
				resp.setContentType("text/html");
				resp.setCharacterEncoding("utf-8");
				PrintWriter pw = resp.getWriter();
				pw.print("false");
				//pw.println("<result><flag>0</flag><msg>附件上传出错："+e.getMessage()+"</msg></result>");
				pw.close();
				return;
			} catch (UnsupportedEncodingException e) {
				resp.setContentType("text/html");
				resp.setCharacterEncoding("utf-8");
				PrintWriter pw = resp.getWriter();
				pw.print("false");
				//pw.println("<result><flag>0</flag><msg>附件上传出错："+e.getMessage()+"</msg></result>");
				pw.close();
				return;
			} catch (IOException e) {
				resp.setContentType("text/html");
				resp.setCharacterEncoding("utf-8");
				PrintWriter pw = resp.getWriter();
				pw.print("false");
				//pw.println("<result><flag>0</flag><msg>附件上传出错："+e.getMessage()+"</msg></result>");
				pw.close();
				return;
			}
			resp.setContentType("text/html");
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println("<result><flag>1</flag><msg>附件上传成功</msg></result>");
			pw.close();
			return;
		}
	}

}
