package edu.cqu.fly.crawler.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cqu.fly.crawler.service.CommonService;
import edu.cqu.fly.erawler.domain.Project;

@RequestMapping("/project")
@Controller
public class ProjectController  {

	@Resource(name="commonService")
	CommonService commonService;
	
	@RequestMapping("/add")
	public void add(Project project,HttpServletRequest request,HttpServletResponse response){
		
		commonService.addEntity(project);
	}
	
	@RequestMapping("/update")
	public void update(Project project,HttpServletRequest request,HttpServletResponse response){
		
		commonService.saveEntity(project);
	}
	
	@RequestMapping("/delete")
	public void delete(Project project,HttpServletRequest request,HttpServletResponse response){
		
		commonService.removeEntityById(project.getProjectID(),Project.class);
	}
	
	@RequestMapping("/query")
	public Project query(Project project,HttpServletRequest request,HttpServletResponse response){
		
		return commonService.findEntityById(project.getProjectID(), Project.class);
	}
}