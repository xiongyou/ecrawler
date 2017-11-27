package edu.cqu.fly.crawler.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import edu.cqu.fly.crawler.config.StdConfig;
import edu.cqu.fly.crawler.config.iConfig;
import edu.cqu.fly.crawler.service.CommonService;
import edu.cqu.fly.crawler.service.TaskService;
import edu.cqu.fly.crawler.util.SystemUtils;
import edu.cqu.fly.erawler.domain.GeneratedKey;
import edu.cqu.fly.erawler.domain.OutputData;
import edu.cqu.fly.erawler.domain.TaskData;
import edu.cqu.fly.erawler.domain.TaskInfo;

@RequestMapping("/task")
@Controller
public class TaskController {

	@Resource(name = "commonService")
	CommonService commonService;

	@Resource(name = "taskService")
	TaskService taskService;
	// int lowestVersion=0;//220;
	String configPath = TaskController.class.getClassLoader().getResource("config.xml").getPath();
	StdConfig config = new StdConfig(configPath);
	int lowestVersion = Integer.parseInt(config.getXpathText("config/lowestVersion/text()"));

	@RequestMapping("/uploadData")
	// 客户端可以直接提交一个对象，服务器端能够自动解析
	public void uploadData(@RequestParam(value = "ClientID", required = false) String clientID,
			HttpServletRequest request, HttpServletResponse response) {

		// 上传数据
		int taskDataID = Integer.valueOf(request.getParameter("TaskDataID"));
		int productInnerId = Integer.valueOf(request.getParameter("productInnerId"));
		TaskData taskData = commonService.findEntityById(taskDataID, TaskData.class);
		int taskID = Integer.valueOf(request.getParameter("TaskID"));
		// data.setTaskID(taskID);
		// data.setURL(request.getParameter("URL"));
		String failedInfo = request.getParameter("FailedInfo");
		taskData.setFailedInfo(failedInfo);
		if (failedInfo.equals("访问超时")) {
			taskData.setIsRedistribute(0);
		}
		String data = request.getParameter("Data");
		//taskData.setData(data);
		// data.setClientid(clientID);
		taskData.setStatus(1);
		Date finishedTime = new Date();
		taskData.setFinishedTime(finishedTime);

		OutputData outputData = new OutputData();
		outputData = taskService.getOutputDataFromJson(data);
		outputData.setTaskDataID(taskDataID);
		outputData.setProductInnerId(productInnerId);
		outputData.setProductURL(request.getParameter("URL"));
		String keyword = request.getParameter("keyword");
		if (keyword == "") {
			outputData.setKeyword(null);
		} else {
			outputData.setKeyword(keyword);
		}

		outputData.setErrorInfo(failedInfo);
		outputData.setWebsite(request.getParameter("website"));
		outputData.setExtractTime(finishedTime);

		JSONObject json = new JSONObject();
		if (commonService.saveEntity(taskData) != null && commonService.saveEntity(outputData) != null) {
			TaskInfo taskInfo = commonService.findEntityById(taskID, TaskInfo.class);
			taskInfo.setStatus(2);
			taskInfo.setFinishedTime(finishedTime);
			commonService.saveEntity(taskInfo);
			json.put("msg", "上传成功");
			json.put("success", true);
		} else {
			json.put("msg", "上传失败");
			json.put("success", false);
		}
		SystemUtils.jsonResponse(response, json.toJSONString());

	}

	@RequestMapping("/getTask")
	@ResponseBody
	public void getTask(@RequestParam(value = "ClientID", required = false) String clientID, HttpServletRequest request,
			HttpServletResponse response) {
		GeneratedKey gk = taskService.scheduleATask(clientID);

		TaskInfo task = gk.getTaskInfo();
		JSONObject json = new JSONObject();
		if (task == null) {
			json.put("msg", "获取任务失败");
			json.put("success", false);
			json.put("task", "[]");
		} else {
			json.put("msg", "获取任务成功");
			json.put("success", true);
			json.put("task", task);
			json.put("TaskDataID", gk.getTaskdataid());
		}
		SystemUtils.jsonResponse(response, json.toJSONString());
	}
	
	@RequestMapping("/getProxyTask")
	@ResponseBody
	public void getProxyTask(@RequestParam(value = "ClientID", required = false) String clientID, HttpServletRequest request,
			HttpServletResponse response) {
		GeneratedKey gk = taskService.scheduleAProxyTask(clientID);

		TaskInfo task = gk.getTaskInfo();
		JSONObject json = new JSONObject();
		if (task == null) {
			json.put("msg", "获取任务失败");
			json.put("success", false);
			json.put("task", "[]");
		} else {
			json.put("msg", "获取任务成功");
			json.put("success", true);
			json.put("task", task);
			json.put("TaskDataID", gk.getTaskdataid());
		}
		SystemUtils.jsonResponse(response, json.toJSONString());
	}

	@RequestMapping("/version")
	@ResponseBody
	public void validVersion(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			int clientVersion = Integer.parseInt(request.getParameter("version"));
			if (clientVersion < lowestVersion) {
				json.put("msg", "程序连接失败，请联系管理员更新程序！");
				json.put("success", false);
			} else {
				json.put("msg", "程序连接成功");
				json.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "服务器端程序异常，" + e.toString());
			json.put("success", false);
		} finally {
			SystemUtils.jsonResponse(response, json.toJSONString());
		}
	}

}
