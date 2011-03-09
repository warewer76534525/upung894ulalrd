package com.ocbcmcd.mailsender.listener;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.annotation.Transformer;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class FileToMailMessageTransformer {
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.to}")
	private String mailTo; 
	
	@Value("${mail.from}")
	private String mailFrom;
	
	@Value("${daily.report.dir}")
	private String dailyReportDir;
	
	@Transformer
	public MailMessage transformFileToString(File file) throws Exception {
		
		File destFile = new File(dailyReportDir, file.getName());
		FileUtils.moveFile(file, destFile);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(mailFrom);
		helper.setTo(mailTo);
		helper.setText(FileUtils.readFileToString(destFile));
		
		helper.addAttachment(file.getName(), new FileSystemResource(destFile));
		
		return new MimeMailMessage(message);
	}
}
