package com.apolloglobal.batch.audit.dao;


import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

/**
 * @author vshivasw
 * Abstract class for all Jdbc based Dao's
 */
public abstract class AbstractJdbcDao extends JdbcDaoSupport{
	
	public int update(String arg0){
		return getJdbcTemplate().update(arg0);
	}
	
	public int update(String arg0, Object[] arg1){
		return getJdbcTemplate().update(arg0, arg1);
	}
	
	public int update(String arg0, Object[] arg1, int[] arg2){
		return getJdbcTemplate().update(arg0, arg1, arg2);
	}
	
	public int update(PreparedStatementCreator arg0){
		return getJdbcTemplate().update(arg0);
	}
	
	public int update(PreparedStatementCreator arg0, KeyHolder arg1){
		return getJdbcTemplate().update(arg0, arg1);
	}
	
	public int update(String arg0, PreparedStatementSetter arg1){
		return getJdbcTemplate().update(arg0, arg1);
	}
	
	public int[] batchUpdate(String[] arg0){
		return getJdbcTemplate().batchUpdate(arg0);
	}
	
	public int[] batchUpdate(String arg0, BatchPreparedStatementSetter arg1){
		return getJdbcTemplate().batchUpdate(arg0, arg1);
	}
	
	public OracleSequenceMaxValueIncrementer getOracleSequenceIncrementer(String sequenceName){
		return new OracleSequenceMaxValueIncrementer(getDataSource(), sequenceName);
	}
}
