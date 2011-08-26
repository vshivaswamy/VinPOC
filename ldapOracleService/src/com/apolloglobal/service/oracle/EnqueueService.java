package com.apolloglobal.service.oracle;

public interface EnqueueService {
	
	public boolean enqueueMessage(String message, String queueName);
}
