package com.apolloglobal.ldap.dao.test;

import java.util.List;

import org.apolloglobal.xsd.DemographicDataDocumentDemographicData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apolloglobal.ldap.dao.LdapProfileDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:/com/apolloglobal/ldap/ldapOracleContext.xml")


public class TestLdapProfileDao {

	@Autowired
	@Qualifier("ldapProfileDao")
	private LdapProfileDaoImpl ldapProfileDao;	
	
	@Test
	public  void testContactsByDate(){
		try {
			@SuppressWarnings("rawtypes")
			List contactList = ldapProfileDao.getContactsByDate("20110819000000Z","20110819000000Z");
			for( int i = 0 ; i < contactList.size(); i++){
				System.out.println("Contact Details:  " + contactList.get(i));
				
			}
		} catch (DataAccessException e) {
			System.out.println("Error occured " + e.getCause());
		}
	}
	
	
	@Test
	public  void testContactDetails(){
		try {
			List contactList = ldapProfileDao.getContactDetails("vinay","Shivaswamy");
			System.out.println("Contact Details size:  " + contactList.size());
			for( int i = 0 ; i < contactList.size(); i++){
				System.out.println("Contact Details:  " + contactList.get(i));
				//EnqueueServiceImpl enqService = new EnqueueServiceImpl();
				//boolean status = enqService.enqueueMessage( contactList.get(i).toString(), "queue_from");
				//System.out.print("Enqueue status: "+status);
			}
		} catch (DataAccessException e) {
			System.out.println("Error occured " + e.getCause());
		}
	}
	
	public  void testAllContactDetails(){
		try {
			List allContactList = ldapProfileDao.getAllContacts();
			for( int i = 0 ; i < allContactList.size(); i++){
				//ContactDTO contact = (ContactDTO)contactList.get(i);
				DemographicDataDocumentDemographicData demoData = (DemographicDataDocumentDemographicData)allContactList.get(i);
				System.out.println("Contact Name " + i +": "+ demoData.toString());
				
			}
			
		} catch (DataAccessException e) {
			System.out.println("Error occured " + e.getCause());
		}
	}
}
