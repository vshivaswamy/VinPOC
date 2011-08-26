package com.apolloglobal.batch.audit.model;

import java.io.Serializable;

public class JobControlDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679998573806592101L;
	
	private Integer jobId;
	
	private Integer jobDetailId;
	
	private String jobDetailStatus;
	
	private String jobDetailDescriptiopn;

	public JobControlDetail() {
		super();
	}

	public JobControlDetail(Integer jobId, Integer jobDetailId,
			String jobDetailStatus, String jobDetailDescriptiopn) {
		super();
		this.jobId = jobId;
		this.jobDetailId = jobDetailId;
		this.jobDetailStatus = jobDetailStatus;
		this.jobDetailDescriptiopn = jobDetailDescriptiopn;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getJobDetailId() {
		return jobDetailId;
	}

	public void setJobDetailId(Integer jobDetailId) {
		this.jobDetailId = jobDetailId;
	}

	public String getJobDetailStatus() {
		return jobDetailStatus;
	}

	public void setJobDetailStatus(String jobDetailStatus) {
		this.jobDetailStatus = jobDetailStatus;
	}

	public String getJobDetailDescriptiopn() {
		return jobDetailDescriptiopn;
	}

	public void setJobDetailDescriptiopn(String jobDetailDescriptiopn) {
		this.jobDetailDescriptiopn = jobDetailDescriptiopn;
	}
	
	
}
