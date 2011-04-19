package com.ocbcmcd.monitoring.command;

public class ConfigType {

	public static final String	FTP_HOST	="ftp.host";
	public static final String	FTP_USERNAME	="ftp.username";
	public static final String	FTP_PASSWORD	="ftp.password";
	public static final String	FTP_REMOTE_DIR	="ftp.remote.dir";
	public static final String  FTP_PORT = "ftp.port";
	public static final String	CHECKED_DIR	="checked.dir";
	public static final String	FTP_REPORT_DIR	="ftp.report.dir";
	public static final String	FTP_MAXRETRY	="ftp.maxretry";
	public static final String	FTP_INTERVAL	="ftp.interval";
	public static final String	WATCHER_MAXRETRY	="watcher.maxretry";
	public static final String	WATCHER_INTERVAL	="watcher.interval";
	

	public static final String	MAIL_HOST	="mail.host";
	public static final String	MAIL_PORT	="mail.port";
	public static final String	MAIL_USERNAME	="mail.username";
	public static final String	MAIL_PASSWORD	="mail.password";
	public static final String	MAIL_TRANSPORT_PROTOCOL	="mail.transport.protocol";
	public static final String	MAIL_SMTP_AUTH	="mail.smtp.auth";
	public static final String	MAIL_FROM	="mail.from";
	
	public static final String	MAIL_TO_EOD	= "mail.to.eod";
	public static final String	MAIL_EOD_SUBJECT	= "mail.eod.subject";
	public static final String	MAIL_TO_PROCESSED_FAILED	= "mail.to.processed.failed";
	public static final String	MAIL_PROCESSED_FAILED_SUBJECT	= "mail.processed.failed.subject";
	public static final String	MAIL_TO_SENT_FAILED	= "mail.to.sent.failed";
	public static final String	MAIL_SENT_FAILED_SUBJECT	= "mail.sent.failed.subject";
	
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_USERNAME = "jdbc.username";
	public static final String JDBC_PASSWORD = "jdbc.password";

	public static final String	INCOMING_DIR	="incoming.dir";
	public static final String	ENCRYPTED_DIR	="encrypted.dir";
	public static final String	PROCESSING_DIR	="processing.dir";
	public static final String	OUTGOING_DIR	="outgoing.dir";
	public static final String	DAILY_REPORT_DIR	="daily.report.dir";
	public static final String	FAILED_DIR	="failed.dir";
	public static final String	END_OF_MONTH_CRON	="endOfMonth.cron"; 
	public static final String	END_OF_MONTH_START	="endOfMonth.start";
	public static final String  END_OF_DAY_CRON = "endofday.cron";

}
