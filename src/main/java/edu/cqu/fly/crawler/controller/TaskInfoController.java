package edu.cqu.fly.crawler.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.cqu.fly.crawler.service.CommonService;
import edu.cqu.fly.erawler.domain.TaskInfo;

@Controller
@RequestMapping("/task-info")
public class TaskInfoController {
	@Resource(name="commonService")
	CommonService commonService;
	
	@RequestMapping("/add")
	@ResponseBody
	public void add(TaskInfo taskInfo,HttpServletRequest request,HttpServletResponse response){
		
		commonService.addEntity(taskInfo);
	}
	
	@RequestMapping("/update")
	public void update(TaskInfo taskInfo,HttpServletRequest request,HttpServletResponse response){
		
		commonService.saveEntity(taskInfo);
	}
	
	@RequestMapping("/delete")
	public void delete(TaskInfo taskInfo,HttpServletRequest request,HttpServletResponse response){
		
		commonService.removeEntityById(taskInfo.getTaskID(),TaskInfo.class);
	}
	
	@RequestMapping("/query")
	public ModelAndView query(TaskInfo taskInfo,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		TaskInfo i = commonService.findEntityById(taskInfo.getTaskID(), TaskInfo.class);
		return mav;
		
	}
}
