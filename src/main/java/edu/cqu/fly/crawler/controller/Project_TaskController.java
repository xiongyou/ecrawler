


package edu.cqu.fly.crawler.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cqu.fly.crawler.service.CommonService;
import edu.cqu.fly.erawler.domain.Project;
import edu.cqu.fly.erawler.domain.Project_Task;

@RequestMapping("/project_task")
@Controller
public class Project_TaskController {

	@Resource(name="commonService")
	CommonService commonService;
	
	@RequestMapping("/add")
	public void add(Project_Task project_task,HttpServletRequest request,HttpServletResponse response){
		
		commonService.addEntity(project_task);
	}
	
	@RequestMapping("/update")
	public void update(Project_Task project_task,HttpServletRequest request,HttpServletResponse response){
		
		commonService.saveEntity(project_task);
	}
	
	@RequestMapping("/delete")
	public void delete(Project_Task project_task,HttpServletRequest request,HttpServletResponse response){
		
		commonService.removeEntityById(project_task.getProjectTaskID(),Project_Task.class);
	}
	
	@RequestMapping("/query")
	public Project_Task query(Project_Task project_task,HttpServletRequest request,HttpServletResponse response){
		
		return commonService.findEntityById(project_task.getProjectTaskID(), Project_Task.class);
	}
}

