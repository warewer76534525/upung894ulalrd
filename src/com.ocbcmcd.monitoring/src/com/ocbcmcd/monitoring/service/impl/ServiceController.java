package com.ocbcmcd.monitoring.service.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;

import com.ocbcmcd.monitoring.common.OsUtil;

public class ServiceController {
	public static final String SUCCESS = "SUCCESS";
	
	protected Log log = LogFactory.getLog(ServiceController.class);
	
	private String execDir;
	
	public ServiceController(String execDir) {
		this.execDir = execDir;
	}

	public String restartService() {
		Commandline cl = null;
		
		if (OsUtil.isWindows()) {
			cl = getWindowsCommandLine();
		} else {
			cl = getUnixCommandLine();
		}

		StreamConsumer consumer = new StreamConsumer() {
		    public void consumeLine( String line ) {
		        System.out.println(line);
		    }
		};

		StreamConsumer stderr = new StreamConsumer() {
		    public void consumeLine( String line ) {
		    	System.out.println(line);
		    }
		};

		int exitCode;
		
		try {
		    exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
		    System.out.println(exitCode);
		} catch ( CommandLineException ex ) {
		   ex.printStackTrace();
		}

		return SUCCESS;
	}

	private Commandline getWindowsCommandLine() {
		Commandline cl = new Commandline();
		
		cl.setExecutable( "cmd.exe" );
		cl.createArg().setValue( "/c" );
		cl.setWorkingDirectory(new File(execDir));
		cl.createArg().setValue("StartService.bat");
		
		return cl;
	}
	
	private Commandline getUnixCommandLine() {
		Commandline cl = new Commandline();
		
		cl.setExecutable("bash");
		cl.createArg().setValue("app");
		cl.createArg().setValue("restart");
		cl.setWorkingDirectory(new File(execDir));
		
		return cl;
	}
	
}
