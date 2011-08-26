package com.apolloglobal.batch.audit.dao.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apolloglobal.batch.audit.dao.JobControlDaoImpl;
import com.apolloglobal.batch.audit.model.JobControl;
import com.apolloglobal.batch.audit.model.JobControlDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:/com/apolloglobal/ldap/ldapOracleContext.xml")
public class TestJobControlDao {
	
	@Autowired
	@Qualifier("jobControlDao")
	private JobControlDaoImpl jobControlDao;	
	
	@Test
	public  void testSaveJobControl(){
		JobControl jc = new JobControl();
		jc.setJobDescription("Testing 1");
		Timestamp time = new Timestamp(new Date().getTime());
		jc.setJobTime(new Timestamp(new Date().getTime()));
		jc.setJobStartTime(new Timestamp(new Date().getTime()));
		jc.setJobEndTime(new Timestamp(new Date().getTime()));
		jc.setJobTotal(new Integer(0));
		jc.setJobStatus(new String("processing"));
		int i = jobControlDao.saveJobControl(jc);
		System.out.println("save  success: "+i);
	}
	
	@Test
	public  void testSaveJobControlDetail(){
		List<JobControlDetail> details = new ArrayList();
		
		for(int i=0;i<10;i++){
			JobControlDetail jcd = new JobControlDetail();
			jcd.setJobId(new Integer(4));
			jcd.setJobDetailDescriptiopn("Testing detail "+i);
			jcd.setJobDetailStatus(new String("success"));
			details.add(jcd);
		}
		
		int[]  i = jobControlDao.saveJobControlDetails(details);
		System.out.println("update success: "+i);
	}
	
	@Test
	public  void testUpdateJobControl(){
		JobControl jc = new JobControl();
		jc.setId(new Integer(4));
		jc.setJobDescription("Testing 1");
		Timestamp time = new Timestamp(new Date().getTime());
		jc.setJobEndTime(new Timestamp(new Date().getTime()));
		jc.setJobTotal(new Integer(10));
		jc.setJobStatus(new String("complete"));
		int i = jobControlDao.updateJobControl(jc);
		System.out.println("update success: "+i);
	}
	
}
