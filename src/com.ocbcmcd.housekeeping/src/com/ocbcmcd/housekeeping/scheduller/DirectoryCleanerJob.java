package com.ocbcmcd.housekeeping.scheduller;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ocbcmcd.housekeeping.service.IDirectoryCleaner;

public class DirectoryCleanerJob extends QuartzJobBean {
	
	protected Log log = LogFactory.getLog(getClass());
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		Map dataMap = context.getJobDetail().getJobDataMap();
		IDirectoryCleaner directoryCleaner = (IDirectoryCleaner) dataMap.get("directoryCleaner");
		
		try {
			directoryCleaner.clean();
			log.info("Clean directory has been executed successfully");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

}
