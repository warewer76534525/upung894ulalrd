package com.ocbcmcd.monitoring.query.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ocbcmcd.monitoring.domain.Log;
import com.ocbcmcd.monitoring.query.ILogQuery;

@Service
public class LogQuery implements ILogQuery {

	@Override
	public List<Log> getLogs() {
		List<Log> logs = new ArrayList<Log>();
		Log log1 = new Log("Upload XXX.txt", Log.SUCCESS, new Date());
		Log log2 = new Log("Upload BBB.txt", Log.SUCCESS, new Date());
		Log log3 = new Log("Upload BBB.txt", Log.ERROR, new Date());
		
		logs.add(log1);
		logs.add(log2);
		logs.add(log3);
		
		return logs;
	}

}
