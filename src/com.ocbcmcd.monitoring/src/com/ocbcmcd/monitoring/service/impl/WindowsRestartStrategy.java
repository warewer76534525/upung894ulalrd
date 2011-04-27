package com.ocbcmcd.monitoring.service.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;

import com.ocbcmcd.monitoring.service.IRestartStrategy;

public class WindowsRestartStrategy implements IRestartStrategy {
	protected Log log = LogFactory.getLog(WindowsRestartStrategy.class);
	
	String restartLog = null;
	

	@Override
	public String restart(String execDir) {
		
		Commandline startCl = null;
		Commandline stopCl = null;
		startCl = getWindowsStartCommandLine(execDir);
		stopCl = getWindowsStopCommandLine(execDir);
		
		StreamConsumer consumer = new StreamConsumer() {
		    public void consumeLine( String line ) {
		    	restartLog += line + "\n";
		    	log.info(line);
		    }
		};

		StreamConsumer stderr = new StreamConsumer() {
		    public void consumeLine( String line ) {
		    	restartLog += line + "\n";
		    }
		};

		int exitCode;
		
		try {
		    exitCode = CommandLineUtils.executeCommandLine(startCl, consumer, stderr);
		    exitCode = CommandLineUtils.executeCommandLine(stopCl, consumer, stderr);
		    log.info(exitCode);
		} catch ( CommandLineException ex ) {
		   ex.printStackTrace();
		}
		
		return restartLog;
	}
	
	private Commandline getWindowsStartCommandLine(String execDir) {
		Commandline cl = new Commandline();
		
		cl.setExecutable( "cmd.exe" );
		cl.createArg().setValue( "/c" );
		cl.setWorkingDirectory(new File(execDir));
		cl.createArg().setValue("StartService.bat");
		
		return cl;
	}
	
	private Commandline getWindowsStopCommandLine(String execDir) {
		Commandline cl = new Commandline();
		
		cl.setExecutable( "cmd.exe" );
		cl.createArg().setValue( "/c" );
		cl.setWorkingDirectory(new File(execDir));
		cl.createArg().setValue("StopService.bat");
		
		return cl;
	}
	
	
}
