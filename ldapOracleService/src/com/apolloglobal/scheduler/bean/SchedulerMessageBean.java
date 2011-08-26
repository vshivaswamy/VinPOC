package com.apolloglobal.scheduler.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.apolloglobal.ldap.dao.LdapProfileDaoImpl;
import com.apolloglobal.scheduler.service.JobSchedulerImpl;
import com.apolloglobal.service.oracle.EnqueueServiceImpl;

/**
 * Message-Driven Bean implementation class for: SchedulerMessageBean
 *
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "cronTrigger", propertyValue = "0 0/1 * * * ?"
		) }) 
		
@ResourceAdapter("quartz-ra.rar")   

public class SchedulerMessageBean implements Job {

	private JobSchedulerImpl jobScheduler = null;
	
    public JobSchedulerImpl getJobScheduler() {
		return jobScheduler;
	}

	public void setJobScheduler(JobSchedulerImpl jobScheduler) {
		this.jobScheduler = jobScheduler;
	}

	/**
     * Default constructor. 
     */
    public SchedulerMessageBean() {}

	/**
     * @see Job#execute(JobExecutionContext)
     */
    @SuppressWarnings("rawtypes")
	public void execute(JobExecutionContext arg0) {
    	Date now = new Date();
       System.out.println("Quartz job executed!"+ now.toString());   
    	
    	/*try{
    		Resource resource = new ClassPathResource("com/apolloglobal/ldap/ldapOracleContext.xml");
			BeanFactory factory = new XmlBeanFactory(resource);
			System.out.println("Quartz job - Got the bean factory");
			LdapProfileDaoImpl ldapProfileDao = (LdapProfileDaoImpl)factory.getBean("ldapProfileDao");
	    	List contactList = ldapProfileDao.getContactsByDate("20110819000000Z","20110819000000Z");
	    	System.out.println("Contact Details recieved size:  " + contactList.size());
	    	if(contactList.size()==0)
	    		return;
	    	
	    	EnqueueServiceImpl enqueueService = (EnqueueServiceImpl)factory.getBean("enqueueService");
			for( int i = 0 ; i < contactList.size(); i++){
				System.out.println("Contact Details:  " + contactList.get(i));
				boolean status = enqueueService.enqueueMessage( contactList.get(i).toString(), "queue_from");
				System.out.print("Enqueue status: "+status);
			}
		}
    	catch (Exception e) {
			System.out.println("Error occured " + e.getCause());
    	}*/
       try{
   			Resource resource = new ClassPathResource("com/apolloglobal/ldap/ldapOracleContext.xml");
			BeanFactory factory = new XmlBeanFactory(resource);
			System.out.println("Quartz job - Got the bean factory");
			JobSchedulerImpl jobScheduler = (JobSchedulerImpl)factory.getBean("jobScheduler");
			jobScheduler.runJob(now);
       }
       catch (Exception e) {
			System.out.println("Error occured " + e.getCause());
   		}
       
    }
	
	

}
