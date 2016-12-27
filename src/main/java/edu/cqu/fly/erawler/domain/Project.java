package edu.cqu.fly.erawler.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_crawler_project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="_project_id")
	private int projectID;
	
	@Column(name="_project_status")
	private int projectStatus;

	public int getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}

	@Column(name="_project_name")
	private String projectName;
	
	@Column(name="_web_site")
	private String website;
	
	@Column(name="_project_description")
	private String projectDescription;
	
	@Column(name="_obj_name")
	private String ObjName;
	
	@Column(name="_key_word")
	private String keyword;
	
	@Column(name="_user_id")
	private int userID;
	
	@Column(name="_price")
	private double price;
	
	@Column(name="_start_time")
	private Date startTime;
	
	@Column(name="_end_time")
	private Date endedTime;
	
	@Column(name="_maxexecuting_time")
	private Date maxExecutingTime;
	
	@Column(name="_execute_period")
	private Date ExecutePeriod;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getObjName() {
		return ObjName;
	}

	public void setObjName(String objName) {
		ObjName = objName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndedTime() {
		return endedTime;
	}

	public void setEndedTime(Date endedTime) {
		this.endedTime = endedTime;
	}

	public Date getMaxExecutingTime() {
		return maxExecutingTime;
	}

	public void setMaxExecutingTime(Date maxExecutingTime) {
		this.maxExecutingTime = maxExecutingTime;
	}

	public Date getExecutePeriod() {
		return ExecutePeriod;
	}

	public void setExecutePeriod(Date executePeriod) {
		ExecutePeriod = executePeriod;
	}

}
