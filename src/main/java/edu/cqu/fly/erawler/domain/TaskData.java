package edu.cqu.fly.erawler.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taskdata")
public class TaskData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TaskDataID")
	private int TaskDataID;
	
	@Column(name="TaskInfoID")
	private int TaskID;
	
	@Column(name="URL")
	private String URL;
	
	@Column(name="Status")
	private int Status;
	
	@Column(name="FailedInfo")
	private String FailedInfo;
	
	@Column(name="Data")
	private String Data;
	
	@Column(name="ClientID")
	private String clientid;
	
	@Column(name="StartTime")
	private Date startTime;
	
	@Column(name="FinishedTime")
	private Date finishedTime;	
	
	@Column(name="IsRedistribute")
	private int isRedistribute;
	
	public int getTaskID() {
		return TaskID;
	}

	public void setTaskID(int taskID) {
		TaskID = taskID;
	}

	public int getTaskDataID() {
		return TaskDataID;
	}

	public void setTaskDataID(int taskDataID) {
		TaskDataID = taskDataID;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getFailedInfo() {
		return FailedInfo;
	}

	public void setFailedInfo(String failedInfo) {
		FailedInfo = failedInfo;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	public int getIsRedistribute() {
		return isRedistribute;
	}

	public void setIsRedistribute(int isRedistribute) {
		this.isRedistribute = isRedistribute;
	}
	
	
	
}
