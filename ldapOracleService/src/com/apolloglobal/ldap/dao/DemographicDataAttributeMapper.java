package com.apolloglobal.ldap.dao;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apolloglobal.xsd.Account;
import org.apolloglobal.xsd.Address;
import org.apolloglobal.xsd.DemographicDataDocument1;
import org.apolloglobal.xsd.DemographicDataDocumentDemographicData;
import org.apolloglobal.xsd.Email;
import org.apolloglobal.xsd.Person;
import org.apolloglobal.xsd.Phone;
import org.springframework.ldap.core.AttributesMapper;

public class DemographicDataAttributeMapper implements AttributesMapper{

	public Object mapFromAttributes(Attributes attributes) throws NamingException {
		
		DemographicDataDocument1 demoDataDocument =DemographicDataDocument1.Factory.newInstance();
		DemographicDataDocumentDemographicData demoData = demoDataDocument.addNewDemographicData();
		
		Person person = demoDataDocument.getDemographicData().addNewPersonData();
		
		
		//Person person =  Person.Factory.newInstance();		
		//demoData.setPersonData(person);
		//Person person = demoData.getPersonData();
		//Person person =  Person.Factory.newInstance();
		
		String commonName = (String)attributes.get("cn").get();
		
		if(commonName != null)
			person.setGivenName(commonName);
			
		String lastName = (String)attributes.get("sn").get();
		if(lastName != null)
			person.setSurname(lastName);
		
		demoData.setPersonData(person);
				
		Account  account = demoDataDocument.getDemographicData().addNewAccountsArray1();
		account.setAccountName("vin9");
		account.setPassword("123456");
		
		Address address = demoDataDocument.getDemographicData().addNewAddressDataArray1();
		address.setAddress1("8 street");
		address.setAddress2("ste 8");
		address.setCity("Chicago");
		address.setState("IL");
		address.setPostalCode("60601");
		address.setCountry("USA");
		address.setAddressType("Home");
		Email email = demoDataDocument.getDemographicData().addNewEmailDataArray1();
		email.setAddress("vin9@email.com");
		email.setEmailType("personal");
		
		Phone phone = demoDataDocument.getDemographicData().addNewPhoneDataArray1();
		phone.setCountryCode("1");
		phone.setAreaCode("312");
		phone.setNumber("111-1111");
		phone.setType("home");
		
		
		return demoData;
	}

}
