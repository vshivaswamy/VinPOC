package com.apolloglobal.scheduler.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.apolloglobal.batch.audit.dao.JobControlDaoImpl;
import com.apolloglobal.batch.audit.model.JobControl;
import com.apolloglobal.batch.audit.model.JobControlDetail;
import com.apolloglobal.ldap.dao.LdapProfileDaoImpl;
import com.apolloglobal.service.oracle.EnqueueServiceImpl;

public class JobSchedulerImpl implements JobScheduler{
	
	private JobControlDaoImpl jobControlDao;
	
	public void setJobControlDao(JobControlDaoImpl jobControlDao) {
		this.jobControlDao = jobControlDao;
	}

	public boolean runJob(Date date){
		boolean status = false;
		ScheduleUpdater r = new ScheduleUpdater();
		r.setJobControlDao(jobControlDao);
		r.setJobDate(date);
		
		Thread t = new Thread(r);
		synchronized (t) {
			t.run();
			status = true;
		}
		
		return status;
	}
	
	
	public class ScheduleUpdater implements Runnable {
		private JobControlDaoImpl jobControlDao;

		public void setJobControlDao(JobControlDaoImpl jobControlDao) {
			this.jobControlDao = jobControlDao;
		}

		private Date jobDate;

		public void setJobDate(Date jobDate) {
			this.jobDate = jobDate;
		}
		
		

		@SuppressWarnings("rawtypes")
		public void run(){
			try{
				int jobTotal = 0;
				List<JobControlDetail> details = null;
				//Now update the audit tables
				JobControl jc = new JobControl();
				jc.setJobDescription("Scheduler batch test 1");
				Timestamp time = getTimeStamp();
				jc.setJobTime(new Timestamp(jobDate.getTime()));
				jc.setJobStartTime(time);
				jc.setJobEndTime(time);
				jc.setJobTotal(new Integer(jobTotal));
				jc.setJobStatus(new String("processing"));
				
				int id = jobControlDao.saveJobControl(jc);
				jc.setId(id);
				System.out.println("Updated the Batch table");
				
				BeanFactory factory = getBeanFactory();
				LdapProfileDaoImpl ldapProfileDao = (LdapProfileDaoImpl)factory.getBean("ldapProfileDao");
				//"20110819000000Z","20110819000000Z"
				List contactList = ldapProfileDao.getContactsByDate(gmtDateToString(), gmtDateToString());
		    	System.out.println("Contact Details recieved size:  " + contactList.size());
		    	
		    	jobTotal = contactList.size();
		    	
		    	if(contactList.size() > 0){
			    	//Write to oracle AQ queue table
			    	EnqueueServiceImpl enqueueService = (EnqueueServiceImpl)factory.getBean("enqueueService");
			    	details = new ArrayList();
		    		
			    	for( int i = 0 ; i < contactList.size(); i++){
						System.out.println("Contact Details:  " + contactList.get(i));
						boolean status = enqueueService.enqueueMessage( contactList.get(i).toString(), "queue_from");
						
						JobControlDetail jcd = new JobControlDetail();
		    			jcd.setJobId(jc.getId());
		    			jcd.setJobDetailDescriptiopn(contactList.get(i).toString());
		    			if(status)
		    				jcd.setJobDetailStatus(new String("success"));
		    			else
		    				jcd.setJobDetailStatus(new String("failure"));
		    			details.add(jcd);
		    			
						System.out.print("Enqueue status: "+status);
					}
		    	}
		    	
	    		jc.setJobStatus("complete");
	    		time = getTimeStamp();
	    		jc.setJobTime(new Timestamp(jobDate.getTime()));
	    		jc.setJobEndTime(time);
	    		jc.setJobTotal(jobTotal);
	    		
	    		jobControlDao.updateJobControl(jc);
	    		
		    	if(details.size()>0){
		    		System.out.println("Details size: "+details.size());
		    		int[]  i = jobControlDao.saveJobControlDetails(details);
		    		System.out.println("update details success: "+i);
		    	}
		    		
			}
	    	catch (Exception e) {
				System.out.println("Error occured " + e.getCause());
			}
		}
		
	}
	
			
	/**
	 * Method to retrieve ApplicationContext
	 * @return
	 */
	private BeanFactory getBeanFactory(){
		BeanFactory factory = null;
		try{
			Resource resource = new ClassPathResource("com/apolloglobal/ldap/ldapOracleContext.xml");
			factory = new XmlBeanFactory(resource);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}
	
	/**
	 * @return
	 */
	private Timestamp getTimeStamp(){
		return  new Timestamp(new Date().getTime());
	}
	
	private String gmtDateToString(){
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyyMMddhhmmss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT")); 
		StringBuffer sb = new StringBuffer();
		sb.append(dateFormatGmt.format(new Date()));
		sb.append("Z");
		return sb.toString();
	}
}
