package edu.cqu.fly.erawler.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="taskinfo")
public class TaskInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5150784180264978966L;

	@Id
	@Column(name="TaskInfoID")
	private int taskID;
	
	@Column(name="ProjectID")
	private String projectID;
	
	@Column(name="URL")
	private String URL;
	
	@Column(name="Status")
	private int status;
	
	@Column(name="ClientID")
	private String UserID;
	
	@Column(name="Website")
	private String website;
	
	@Column(name="DataObj")
	private String dataobj;
	
	@Column(name="Keyword")
	private String keyword;
	
	@Column(name="DistributedTime")
	private Date distributedTime;
	
	@Column(name="FinishedTime")
	private Date finishedTime;
	
	@Column(name="productInnerId")
	private int productInnerId;
	
	public Date getDistributedTime() {
		return distributedTime;
	}
	public void setDistributedTime(Date distributedTime) {
		this.distributedTime = distributedTime;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website1) {
		website = website1;
	}
	public String getDataObj() {
		return dataobj;
	}
	public void setDataObj(String dataObj) {
		dataobj = dataObj;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Date getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	public int getProductInnerId() {
		return productInnerId;
	}
	public void setProductInnerId(int productInnerId) {
		this.productInnerId = productInnerId;
	}
	
	
}
