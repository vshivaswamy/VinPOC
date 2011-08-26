package com.apolloglobal.service.oracle;

import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.apolloglobal.oracle.dao.EnqueueFromQueueSP;

public class EnqueueServiceImpl implements EnqueueService{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean enqueueMessage(String message, String queueName){
		boolean status = false;
		if(queueName.equalsIgnoreCase("queue_from")){
			EnqueueFromQueueSP qProc = new EnqueueFromQueueSP(getDataSource());
	        Map results = qProc.execute(message);
	        System.out.println("executed the SP successfully");
	        printMap(results);
	        if(results.containsKey("MSG_ID"))
	        	if(results.get("MSG_ID") != null)
	        		status = true;
		}
		
		
		return status;
	}
	
	/*private DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@advijy-07:1522:orcl");
        ds.setUsername("vinay");
        ds.setPassword("vinay");
	
        return ds;
	}*/
	
	public DataSource getDataSource() {
		return dataSource;
	}

	private static void printMap(Map results) {
        for (Iterator it = results.entrySet().iterator(); it.hasNext(); ) {
            System.out.println("Result back: "+it.next());  
        }
    }

}
