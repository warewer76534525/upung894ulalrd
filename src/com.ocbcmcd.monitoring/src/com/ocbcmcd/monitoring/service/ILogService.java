package com.ocbcmcd.monitoring.service;

import java.util.List;

import com.ocbcmcd.monitoring.domain.Log;

public interface ILogService {

	void log(List<Log> logs);

}
