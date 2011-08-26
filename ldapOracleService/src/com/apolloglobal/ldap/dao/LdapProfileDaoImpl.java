package com.apolloglobal.ldap.dao;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.GreaterThanOrEqualsFilter;
import org.springframework.ldap.filter.OrFilter;


public class LdapProfileDaoImpl implements  LdapProfileDAO{
	private LdapTemplate ldapTemplate;
	
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	} 

	
	@SuppressWarnings("rawtypes")
	public List getCreateAttrContactsByDate(){
		return ldapTemplate.search("", "(objectclass=person)", 1, new String[]{"createTimestamp"}, new ContextMapper(){   
	        @Override  
	        public Object mapFromContext(Object ctx)   
	        {   
	            DirContextAdapter context = (DirContextAdapter)ctx;   
	            return context.getStringAttributes("createTimestamp");   
	        } }); 
	}
	
	@SuppressWarnings("rawtypes")
	public List getContactsByDate(String createDate,String modifyDate){
		OrFilter orFilter = new OrFilter();
		orFilter.or(new GreaterThanOrEqualsFilter("createTimestamp", createDate));
		orFilter.or(new GreaterThanOrEqualsFilter("modifyTimestamp",modifyDate));
		
		//System.out.println("LDAP Query " + andFilter.encode());
		/*return ldapTemplate.search("", orFilter.encode(),new AttributesMapper() {
			public Object mapFromAttributes(Attributes attrs)
			throws NamingException {
				return attrs.get("mail").get();
			}
		});*/
		return ldapTemplate.search("", orFilter.encode(),new DemographicDataAttributeMapper());
		
	}
	
	@SuppressWarnings("rawtypes")
	public List getAllContactNames() {
		return ldapTemplate.search("", "(objectclass=person)",
				new AttributesMapper() {
					public Object mapFromAttributes(Attributes attrs)
							throws NamingException {
						return attrs.get("cn").get();
					}
				});
	}
	
	@SuppressWarnings("rawtypes")
	public List getAllContacts() {
		return ldapTemplate.search("", "(objectclass=person)", new DemographicDataAttributeMapper());
	}

	@SuppressWarnings("rawtypes")
	public List getContactDetails(String commonName,String lastName){
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter("objectclass","person"));
		//andFilter.and(new EqualsFilter("cn",commonName));
		//andFilter.and(new EqualsFilter("sn",lastName));
		andFilter.and(new GreaterThanOrEqualsFilter("createTimestamp", "20110820034111Z"));
		andFilter.and(new GreaterThanOrEqualsFilter("modifyTimestamp", "20110820034111Z"));
		
		//System.out.println("LDAP Query " + andFilter.encode());
		return ldapTemplate.search("", andFilter.encode(),new AttributesMapper() {
			public Object mapFromAttributes(Attributes attrs)
			throws NamingException {
				return attrs.get("mail").get();
			}
		});
		//return ldapTemplate.search("", andFilter.encode(),new DemographicDataAttributeMapper());
		
	}
		
}
