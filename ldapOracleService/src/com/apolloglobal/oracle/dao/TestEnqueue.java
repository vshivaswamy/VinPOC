package com.apolloglobal.oracle.dao;

import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.apolloglobal.dao.EnqueueFromQueueSP;

public class TestEnqueue {

		
	DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@advijy-07:1522:orcl");
        ds.setUsername("vinay");
        ds.setPassword("vinay");
	
        return ds;
	}
	
	private static void printMap(Map results) {
        for (Iterator it = results.entrySet().iterator(); it.hasNext(); ) {
            System.out.println(it.next());  
        }
    }

	public static void main(String[] args) {
		TestEnqueue te = new TestEnqueue();
		DataSource ds = te.getDataSource();
		
		EnqueueFromQueueSP sproc = new EnqueueFromQueueSP(ds);
        Map results = sproc.execute();
        System.out.println("executed the SP successfully");
        printMap(results);

	}
    

}
