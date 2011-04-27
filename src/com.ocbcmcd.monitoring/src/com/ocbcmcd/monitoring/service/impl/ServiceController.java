package com.ocbcmcd.monitoring.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ocbcmcd.monitoring.common.OsUtil;
import com.ocbcmcd.monitoring.service.IRestartStrategy;

public class ServiceController {
	public static final String SUCCESS = "SUCCESS";

	protected Log log = LogFactory.getLog(ServiceController.class);
	private List<String> execDirs;

	public ServiceController(List<String> execDirs) {
		this.execDirs = execDirs;
	}

	public String restartService() {
		IRestartStrategy restartStrategy = null;

		if (OsUtil.isWindows()) {
			restartStrategy = new WindowsRestartStrategy();
		} else {
			restartStrategy = new UnixRestartStrategy();
		}

		for (String execDir : execDirs) {
			restartStrategy.restart(execDir);
		}
		return SUCCESS;
	}

}
