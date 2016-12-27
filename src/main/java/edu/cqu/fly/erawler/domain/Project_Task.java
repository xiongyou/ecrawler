package edu.cqu.fly.erawler.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_crawler_project_task")
public class Project_Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="_project_task_id")
	private int projectTaskID;
	
	@Column(name="_project_id")
	private int projectID;

	@Column(name="_url")
	private String URL;
	
	
	@Column(name="_status")
	private int status;

	@Column(name="_website")
	private String website;
	
	@Column(name="_dataobj")
	private String dataobj;	

	
	
	public int getProjectTaskID() {
		return projectTaskID;
	}
	public void setProjectTaskID(int projectTaskID) {
		this.projectTaskID = projectTaskID;
	}
	public String getDataobj() {
		return dataobj;
	}
	public void setDataobj(String dataobj) {
		this.dataobj = dataobj;
	}

	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
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

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website1) {
		website = website1;
	}


}
