package edu.cqu.fly.crawler.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class SystemUtils {
	
	private final static Logger logger = Logger.getLogger(SystemUtils.class);
	public final static boolean BUTTON_AUTHORITY_CHECK = false;
	
	
	
	public static void jsonResponse(HttpServletResponse response, String json){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (Exception e) {
			logger.error("返回json错误", e);
		}
	}
	
	 public static Properties getfileCfg(String filename){
	    	Properties prop = new Properties();
	    	try {
	    		String path = SystemUtils.class.getClassLoader().getResource("").toURI().getPath();
	    		InputStream in = new BufferedInputStream (new FileInputStream(path+"/"+filename));
	        	prop.load(in);     ///加载属性列表
	        	return prop;
			} catch (Exception e) {
				return null;
			}
	    	
	    }
	
	
}
