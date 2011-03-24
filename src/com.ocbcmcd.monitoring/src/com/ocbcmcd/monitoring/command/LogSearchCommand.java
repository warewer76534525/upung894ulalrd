package com.ocbcmcd.monitoring.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogSearchCommand {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	private String file;
	private String from;
	private String to;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getStartDate() {
		if (to == null)
			return null;
		
		try {
			return dateFormat.parse(from);
		} catch (ParseException e) {
			return null;
		}
	}

	public Date getEndDate() {
		if (to == null)
			return null;
		
		try {
			return dateFormat.parse(to);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "LogSearchCommand [file=" + file + ", from=" + from + ", to="
				+ to + "]";
	}

	
}
