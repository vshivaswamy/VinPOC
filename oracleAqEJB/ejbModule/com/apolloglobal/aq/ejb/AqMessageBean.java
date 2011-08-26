package com.apolloglobal.aq.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

//import com.arjuna.ats.jta.UserTransaction;

/**
 * Message-Driven Bean implementation class for: AqMessageBean
 *
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "connectionFactoryProperties", propertyValue = "jdbc_connect_string=jdbc:oracle:thin:vinay/vinay@advijy-07:1522:orcl,host=advijy-07,user=vinay,password=vinay,port=1522,sid=orcl,driver=thin"),
				@ActivationConfigProperty(propertyName="destinationProperties", propertyValue="owner=vinay,name=from_queue"),
			    @ActivationConfigProperty(propertyName="userName", propertyValue="vinay"),
			    @ActivationConfigProperty(propertyName="password", propertyValue="vinay"),
			    @ActivationConfigProperty(propertyName="ConnectionFactoryClassName", propertyValue="oracle.jms.AQjmsConnectionFactory"),
			    @ActivationConfigProperty(propertyName="QueueConnectionFactoryClassName", propertyValue="oracle.jms.AQjmsQueueConnectionFactory")
		})
@ResourceAdapter("oracleaq.rar")

@TransactionManagement(TransactionManagementType.BEAN)
public class AqMessageBean implements MessageListener {

	 //@Resource
	 //private SessionContext sessionContext;
	 
    /**
     * Default constructor. 
     */
    public AqMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	//final UserTransaction transaction = sessionContext.getUserTransaction();
    	try {
   		// 	transaction.begin();
   		 	if (message instanceof TextMessage) {
   		 		System.out.println("************************************************************");
   		 		TextMessage text = (TextMessage)message;
   		 		System.out.println("In the AqMessageBean and message recieved is : "+ text.getText());;
   		 		System.out.println("************************************************************");
   		 	} else {
   		 		System.out.println("**************************************** Unknown************");
   		 	}
   		 	
   		 	//TODO: Call the Neptune web service here
   		 	
   		 	//TODO: write the retry service for failure
   		 	
   		 	//transaction.commit();
   		 } 
    	catch (Exception e) {
   		 	try {
   		 		//transaction.rollback();y
   		 	} 
   		 	catch (IllegalStateException e1) {
   		 		e1.printStackTrace();
   		 	}
   		 	catch (SecurityException e1) {
   		 		e1.printStackTrace();
   		 	} 
   		 	/*catch (SystemException e1) {
   		 		e1.printStackTrace();
   		 	}*/
   		 	e.printStackTrace();
   		 }
    	
        
    }

}
