package com.apolloglobal.batch.audit.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.quartz.JobDetail;



public class JobControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8769615781847367823L;
	
	private  Integer id;
	
	private Timestamp jobTime;
	
	private Timestamp jobStartTime;
	
	private Timestamp jobEndTime;
	
	private Integer jobTotal;
		
	private String jobStatus;
	
	private String jobDescription;
	
	private List<JobDetail> details;

	
	public JobControl() {
		super();
	}


	public JobControl(Timestamp jobTime, Timestamp jobStartTime,
			Timestamp jobEndTime, String jobStatus, String jobDescription,
			List<JobDetail> details) {
		super();
		this.jobTime = jobTime;
		this.jobStartTime = jobStartTime;
		this.jobEndTime = jobEndTime;
		this.jobStatus = jobStatus;
		this.jobDescription = jobDescription;
		this.details = details;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Timestamp getJobTime() {
		return jobTime;
	}


	public void setJobTime(Timestamp jobTime) {
		this.jobTime = jobTime;
	}


	public Timestamp getJobStartTime() {
		return jobStartTime;
	}


	public void setJobStartTime(Timestamp jobStartTime) {
		this.jobStartTime = jobStartTime;
	}


	public Timestamp getJobEndTime() {
		return jobEndTime;
	}


	public void setJobEndTime(Timestamp jobEndTime) {
		this.jobEndTime = jobEndTime;
	}


	public String getJobStatus() {
		return jobStatus;
	}


	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}


	public String getJobDescription() {
		return jobDescription;
	}


	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Integer getJobTotal() {
		return jobTotal;
	}


	public void setJobTotal(Integer jobTotal) {
		this.jobTotal = jobTotal;
	}

	
	public List<JobDetail> getDetails() {
		return details;
	}


	public void setDetails(List<JobDetail> details) {
		this.details = details;
	}
	
	
	
}
