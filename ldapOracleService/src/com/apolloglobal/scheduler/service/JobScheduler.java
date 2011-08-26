package com.apolloglobal.scheduler.service;

import java.util.Date;

public interface JobScheduler {
	public boolean runJob(Date date);
}
