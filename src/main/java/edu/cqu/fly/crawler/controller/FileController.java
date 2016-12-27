package edu.cqu.fly.crawler.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileController {
	public void receiveFile() {
		
//		HttpServletRequest request=null;
//		// 判断enctype属性是否为multipart/form-data  
//		boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
//		  
//		// Create a factory for disk-based file items  
//		DiskFileItemFactory factory = new DiskFileItemFactory();  
//		  
//		// 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存  
//		// 此方法是设置是否使用临时文件的临界值（单位：字节）  
//		int yourMaxMemorySize;
//		factory.setSizeThreshold(yourMaxMemorySize);  
//		  
//		// 与上一个结合使用，设置临时文件的路径（绝对路径）  
//		File yourTempDirectory;
//		factory.setRepository(yourTempDirectory);  
//		  
//		// Create a new file upload handler  
//		ServletFileUpload upload = new ServletFileUpload(factory);  
//		  
//		// 设置上传内容的大小限制（单位：字节）  
//		long yourMaxRequestSize = 0;
//		upload.setSizeMax(yourMaxRequestSize);  
//		  
//		// Parse the request  
//		List<?> items = upload.parseRequest(request);  
//		  
//		Iterator iter = items.iterator();  
//		while (iter.hasNext()) {  
//		    FileItem item = (FileItem) iter.next();  
//		  
//		    if (item.isFormField()) {  
//		        //如果是普通表单字段  
//		        String name = item.getFieldName();  
//		            String value = item.getString();  
//		            //...  
//		    } else {  
//		        //如果是文件字段  
//		        String fieldName = item.getFieldName();  
//		            String fileName = item.getName();  
//		            String contentType = item.getContentType();  
//		            boolean isInMemory = item.isInMemory();  
//		            long sizeInBytes = item.getSize();  
//		           // ...  
//		              
//		            boolean writeToFile = true;
//						// Process a file upload  
//		                if (writeToFile) {  
//		                    File uploadedFile = new File("");  
//		                    try {
//								item.write(uploadedFile);
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}  
//		                } else {  
//		                    InputStream uploadedStream = null;
//							try {
//								uploadedStream = item.getInputStream();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}  
//		                    //...  
//		                    try {
//								uploadedStream.close();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}  
//		                }  
//		    }  
//		}  
	
}
}
