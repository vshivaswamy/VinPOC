package com.apolloglobal.oracle.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class EnqueueFromQueueSP extends StoredProcedure{
	
	private static final String SPROC_NAME = "ENQUEUE_FROM_QUEUE";
	private static final String INPUT_TEXT = "TEXT_INPUT";
	private static final String MESSAGE_ID = "MSG_ID";

	public EnqueueFromQueueSP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnqueueFromQueueSP(DataSource ds) {
		super(ds, SPROC_NAME);
		declareParameter(new SqlParameter(INPUT_TEXT, Types.VARCHAR));
		declareParameter(new SqlOutParameter(MESSAGE_ID, Types.VARCHAR));
		compile();
	}

	public EnqueueFromQueueSP(JdbcTemplate jdbcTemplate, String name) {
		super(jdbcTemplate, name);
		// TODO Auto-generated constructor stub
	}

	public Map execute(String message) {
		String str = "Testing from spring SP call..";
		Map inputs = new HashMap();
        inputs.put(INPUT_TEXT, message);
        return super.execute(inputs);

    }



}
