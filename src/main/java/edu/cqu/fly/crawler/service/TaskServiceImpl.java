package edu.cqu.fly.crawler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu.cqu.fly.crawler.config.StdConfig;
import edu.cqu.fly.crawler.controller.TaskController;
import edu.cqu.fly.crawler.dao.CommonDao;
import edu.cqu.fly.crawler.dao.CommonDaoImpl;
//import edu.cqu.fly.crawler.taskpool.TaskPool;
import edu.cqu.fly.erawler.domain.GeneratedKey;
import edu.cqu.fly.erawler.domain.IPProxy;
import edu.cqu.fly.erawler.domain.OutputData;
import edu.cqu.fly.erawler.domain.TaskData;
import edu.cqu.fly.erawler.domain.TaskInfo;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends SystemServiceImpl implements TaskService {
	String configPath = TaskServiceImpl.class.getClassLoader().getResource("config.xml").getPath();

	StdConfig config = new StdConfig(configPath);
	int taskSize = Integer.parseInt(config.getXpathText("config/taskSize/text()"));
	@Resource(name = "commonDao")
	CommonDao commonDao;
	// TaskPool taskPool=new TaskPool();

	static List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
	
	static List<TaskInfo> proxyTaskInfoList = new ArrayList<TaskInfo>();
	

	public List<TaskInfo> getTaskInfoList() {
		return taskInfoList;
	}

	public void setTaskInfoList(List<TaskInfo> taskInfoList) {
		TaskServiceImpl.taskInfoList = taskInfoList;
	}

	/**
	 * 获取一个任务池列表
	 * 
	 * @param size
	 */
	public void generateTaskPool(int size) {
		if(commonDao==null)
			 System.out.println(0);
		List<TaskInfo> taskInfos = commonDao.readEntityByJPQL(
				"from TaskInfo taskinfo where taskinfo.status=?1 and taskinfo.website<>'TaoBao' and taskinfo.website<>'Tmall' order by project_priority desc,task_priority desc ", 0, size, TaskInfo.class, 0);//-- and taskinfo.website<>'TaoBao' and taskinfo.website<>'Tmall'
		taskInfoList.addAll(taskInfos);
	}

	public void generateProxyTaskPool(int size) {
		if(commonDao==null)
			 System.out.println(0);
		List<TaskInfo> taskInfos = commonDao.readEntityByJPQL(
				"from TaskInfo taskinfo where taskinfo.status=?1 and (taskinfo.website='TaoBao' or taskinfo.website='Tmall')  order by project_priority desc,task_priority desc", 0, size, TaskInfo.class, 0);
		proxyTaskInfoList.addAll(taskInfos);
	}
	/**
	 * 追加新的任务列表
	 * 
	 * @param size
	 * @return
	 * 
	 * 
	 * 		public boolean appendTasks(int size){ List<TaskInfo> taskInfos =
	 *         commonDao.readEntityByJPQL(
	 *         "from TaskInfo taskinfo where taskinfo.status=?1", 0, size,
	 *         TaskInfo.class, 0); if(taskInfos != null){
	 *         taskInfoList.addAll(taskInfos); return true; } else{ return
	 *         false; } }
	 */

	public void removeATask(TaskInfo taskInfo) {
		taskInfoList.remove(taskInfo);
	}

	public synchronized GeneratedKey getATask(String clientId) {
		GeneratedKey key = new GeneratedKey();

		if (taskInfoList.size() == 0) {
			generateTaskPool(taskSize);
		}
		/*
		 * if (taskInfoList.size() < 10) { appendTasks(100); }
		 */
		if (taskInfoList.size() != 0) {
			TaskInfo temp = taskInfoList.get(0);
			TaskInfo taskInfo = temp;
			TaskInfo res = null;

			TaskData taskData = new TaskData();
			TaskData resTaskData = new TaskData();

			taskInfo.setUserID(clientId);
			taskInfo.setStatus(1);
			Date date = new Date();
			taskInfo.setDistributedTime(date);

			res = commonDao.updateEntity(taskInfo);
			taskInfoList.remove(0);
			key.setTaskInfo(res);

			taskData.setStartTime(res.getDistributedTime());
			taskData.setClientid(res.getUserID());
			taskData.setTaskID(res.getTaskID());
			taskData.setURL(res.getURL());
			taskData.setIsRedistribute(-1);// 初始状态

			resTaskData = commonDao.saveEntity(taskData);
			key.setTaskdataid(resTaskData.getTaskDataID());
		}
		return key;
	}
	
	public synchronized GeneratedKey getAProxyTask(String clientId) {
		GeneratedKey key = new GeneratedKey();

		if (proxyTaskInfoList.size() == 0) {
			generateProxyTaskPool(taskSize);
		}
		/*
		 * if (taskInfoList.size() < 10) { appendTasks(100); }
		 */
		if (proxyTaskInfoList.size() != 0) {
			TaskInfo temp = proxyTaskInfoList.get(0);
			TaskInfo taskInfo = temp;
			TaskInfo res = null;

			TaskData taskData = new TaskData();
			TaskData resTaskData = new TaskData();

			taskInfo.setUserID(clientId);
			taskInfo.setStatus(1);
			Date date = new Date();
			taskInfo.setDistributedTime(date);

			res = commonDao.updateEntity(taskInfo);
			proxyTaskInfoList.remove(0);
			key.setTaskInfo(res);

			taskData.setStartTime(res.getDistributedTime());
			taskData.setClientid(res.getUserID());
			taskData.setTaskID(res.getTaskID());
			taskData.setURL(res.getURL());
			taskData.setIsRedistribute(-1);// 初始状态

			resTaskData = commonDao.saveEntity(taskData);
			key.setTaskdataid(resTaskData.getTaskDataID());
		}
		return key;
	}

	public GeneratedKey scheduleATask(String clientId) {

		return getATask(clientId);
	}
	
	public GeneratedKey scheduleAProxyTask(String clientId) {

		return getAProxyTask(clientId);
	}

	public OutputData getOutputDataFromJson(String json) {
		// TODO Auto-generated method stub
		OutputData outputData = (OutputData) JSONObject.toJavaObject(JSON.parseObject(json), OutputData.class);
		return outputData;
	}

	


}
