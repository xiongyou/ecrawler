package edu.cqu.fly.crawler.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cqu.fly.crawler.service.CommonService;
import edu.cqu.fly.erawler.domain.TaskData;
@RequestMapping("/task-data")
@Controller
public class TaskDataController {
	@Resource(name="commonService")
	CommonService commonService;
	
	@RequestMapping("/add")
	public void add(TaskData taskData,HttpServletRequest request,HttpServletResponse response){
		
		commonService.addEntity(taskData);
	}
	
	@RequestMapping("/update")
	public void update(TaskData taskData,HttpServletRequest request,HttpServletResponse response){
		
		commonService.saveEntity(taskData);
	}
	
	@RequestMapping("/delete")
	public void delete(TaskData taskData,HttpServletRequest request,HttpServletResponse response){
		
		commonService.removeEntityById(taskData.getTaskID(),TaskData.class);
	}
	
	@RequestMapping("/query")
	public void query(TaskData taskData,HttpServletRequest request,HttpServletResponse response){
		
		 commonService.findEntityById(taskData.getTaskID(), TaskData.class);
	}
}
