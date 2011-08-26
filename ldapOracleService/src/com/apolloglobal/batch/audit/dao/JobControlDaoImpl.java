package com.apolloglobal.batch.audit.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

import com.apolloglobal.batch.audit.model.JobControl;
import com.apolloglobal.batch.audit.model.JobControlDetail;

public class JobControlDaoImpl  extends AbstractJdbcDao implements JobControlDao{
	
	
	private static final String INSERT_JOB_CTRL = 
		"Insert into LDAP_ORA_JOB_CTRL " +
		"(JOB_ID, JOB_TIME, JOB_START_TIME, JOB_END_TIME, JOB_STATUS, JOB_TOTAL, JOB_DESCRIPTION) " +
		"Values (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String INSERT_JOB_CTRL_DETAIL =	
		"Insert into LDAP_ORA_JOB_CTRL_DETAIL " +
		"(JOB_ID, JOB_DETAIL_ID, JOB_DETAIL_STATUS, JOB_DETAIL_DESCRIPTION) " +
		"Values (?, ?, ?, ?)";
	
	private static final String UPDATE_JOB_CTRL = " Update LDAP_ORA_JOB_CTRL set " +
			"	JOB_END_TIME = ?,  " +
			"	JOB_TOTAL = ?, " +
			" 	JOB_STATUS = ?  " +
			"	where job_id = ?";
	
	private static final String UPDATE_JOB_CTRL_DETAIL = "";
	
	private static final String SEQ_LDAP_ORA_JOB_CTRL = "SEQ_LDAP_ORA_JOB_CTRL";
	
	private static final String SEQ_LDAP_ORA_JOB_CTRL_DETAIL = "SEQ_LDAP_ORA_JOB_CTRL_DETAIL";
	
	public int saveJobControl(final JobControl jc){
		OracleSequenceMaxValueIncrementer incrementer = super.getOracleSequenceIncrementer(SEQ_LDAP_ORA_JOB_CTRL);
		Integer id = new Integer(incrementer.nextIntValue());
		super.update(INSERT_JOB_CTRL, new Object[] {id, jc.getJobTime(), jc.getJobStartTime(), jc.getJobEndTime(), jc.getJobStatus(), jc.getJobTotal(), jc.getJobDescription()});
		return id;
	}
	
	public int updateJobControl(final JobControl jc){
		return super.update(UPDATE_JOB_CTRL, new Object[] {jc.getJobEndTime(), jc.getJobTotal(), jc.getJobStatus(), jc.getId()});
	}
	
	
	public int[] saveJobControlDetails(List<JobControlDetail> details) {
		System.out.println("in the saveJobControlDetails: "+details.size());
		System.out.println("saveJobControlDetails jobId: "+details.get(0).getJobId());
		System.out.println("saveJobControlDetails jobStatus: "+details.get(0).getJobDetailStatus());
		System.out.println("saveJobControlDetails jobDetailId: "+details.get(0).getJobDetailId());
		
		OracleSequenceMaxValueIncrementer incrementer = super.getOracleSequenceIncrementer(SEQ_LDAP_ORA_JOB_CTRL_DETAIL);
		return super.batchUpdate(INSERT_JOB_CTRL_DETAIL, new JobControlDetailBatchSetter(details, incrementer));		
	}
	
	
	private final class JobControlDetailBatchSetter implements BatchPreparedStatementSetter{
		List<JobControlDetail>details;
		OracleSequenceMaxValueIncrementer incrementer;
		JobControlDetailBatchSetter(final List<JobControlDetail> details,OracleSequenceMaxValueIncrementer incrementer){
			this.details = details;
			this.incrementer = incrementer;
		}
		
		public int getBatchSize() {
			 return details.size();
		}
		
		public void setValues(PreparedStatement ps, int i) throws SQLException {
			ps.setInt(1, details.get(i).getJobId());
			ps.setInt(2, incrementer.nextIntValue());
			ps.setString(3, details.get(i).getJobDetailStatus());
			ps.setString(4, details.get(i).getJobDetailDescriptiopn());
        }
	}
	
}
