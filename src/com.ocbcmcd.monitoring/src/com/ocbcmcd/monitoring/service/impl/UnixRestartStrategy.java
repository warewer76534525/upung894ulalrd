package com.ocbcmcd.monitoring.service.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;

import com.ocbcmcd.monitoring.service.IRestartStrategy;

public class UnixRestartStrategy implements IRestartStrategy {
	protected Log log = LogFactory.getLog(WindowsRestartStrategy.class);
	
	String restartLog = null;

	@Override
	public String restart(String execDir) {
		Commandline cl = null;
		cl = getUnixCommandLine(execDir);
		

		StreamConsumer consumer = new StreamConsumer() {
		    public void consumeLine( String line ) {
		    	restartLog += line + "\n";
		    }
		};

		StreamConsumer stderr = new StreamConsumer() {
		    public void consumeLine( String line ) {
		    	restartLog += line + "\n";
		    }
		};

		int exitCode;
		
		try {
		    exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
		    log.info(exitCode);
		} catch ( CommandLineException ex ) {
		   ex.printStackTrace();
		}
		return restartLog;
	}
	
	private Commandline getUnixCommandLine(String execDir) {
		Commandline cl = new Commandline();
		
		cl.setExecutable("bash");
		cl.createArg().setValue("app");
		cl.createArg().setValue("restart");
		cl.setWorkingDirectory(new File(execDir));
		
		return cl;
	}

}
