package com.apolloglobal.ldap.dao;

import java.util.List;

public interface LdapProfileDAO {
	
	@SuppressWarnings("rawtypes")
	public List getCreateAttrContactsByDate();
	
	@SuppressWarnings("rawtypes")
	public List getContactsByDate(String createDate,String modifyDate);
	
	
	@SuppressWarnings("rawtypes")
	public List getAllContactNames();
			
	@SuppressWarnings("rawtypes")
	public List getAllContacts();
		
	@SuppressWarnings("rawtypes")
	public List getContactDetails(String commonName,String lastName);
				
}
