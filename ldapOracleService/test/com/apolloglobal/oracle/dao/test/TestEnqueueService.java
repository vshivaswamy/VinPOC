package com.apolloglobal.oracle.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apolloglobal.service.oracle.EnqueueServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:/com/apolloglobal/ldap/ldapOracleContext.xml")
public class TestEnqueueService {

	@Autowired
	@Qualifier("enqueueService")
	private EnqueueServiceImpl enqueueService;	
	
	@Test
	public void testEnqueueMessage(){
		String message = "Test message 1.";
		String queueName = "queue_from";
		boolean status = enqueueService.enqueueMessage(message, queueName);
		System.out.println("Enqueued message successfully: "+status);
	}
}
