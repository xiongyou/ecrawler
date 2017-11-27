package edu.cqu.fly.crawler.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import edu.cqu.fly.crawler.config.StdConfig;
import edu.cqu.fly.crawler.service.CommonService;
import edu.cqu.fly.crawler.service.ProxyService;
import edu.cqu.fly.crawler.service.TaskService;
import edu.cqu.fly.crawler.util.SystemUtils;

@Controller
@RequestMapping("/Proxy")
public class IPProxyController {

	@Resource(name = "proxyService")
	ProxyService proxyService;

	
	@RequestMapping("/getProxy")
	public  void queryOldest(HttpServletRequest request, HttpServletResponse response) {
			try {
				response.getWriter().write(proxyService.getIp());
			} catch (IOException e) {
				e.printStackTrace();				
			}
		
	}
}
