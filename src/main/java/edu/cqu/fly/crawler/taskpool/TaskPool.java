package edu.cqu.fly.crawler.taskpool;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.cqu.fly.crawler.dao.CommonDao;
import edu.cqu.fly.erawler.domain.TaskInfo;

@Service("taskPool")
@Transactional
public class TaskPool {
	

	@Resource(name = "commonDao")
	CommonDao commonDao;
	
	public static List<TaskInfo> taskInfoList=new ArrayList<TaskInfo>();
	
	public List<TaskInfo> getTaskInfoList() {
		return taskInfoList;
	}

	public void setTaskInfoList(List<TaskInfo> taskInfoList) {
		TaskPool.taskInfoList = taskInfoList;
	}
	
	
	/**
	 * 获取一个任务池列表
	 * @param size
	 */
	public  void generateTaskPool(int size){
		List<TaskInfo> taskInfos = commonDao.readEntitiesByJPQL("from TaskInfo taskinfo where taskinfo.status=?1 order by rand() limit ?2", TaskInfo.class, 0,size);
		taskInfoList.addAll(taskInfos);
	}
	
	/**
	 * 追加新的任务列表
	 * @param size
	 * @return
	 */
	
	public  boolean appendTasks(int size){
		List<TaskInfo> newTaskInfoList=commonDao.readEntitiesByJPQL("from TaskInfo taskinfo where taskinfo.status=?1 limit "+size, TaskInfo.class, 0);
		if(newTaskInfoList != null){
			taskInfoList.addAll(newTaskInfoList);
			return true;
		}
		else{
			return false;
		}		
	}
	
	public void removeTask(TaskInfo taskInfo){
		TaskPool.taskInfoList.remove(taskInfo);
	}
	
	

}
