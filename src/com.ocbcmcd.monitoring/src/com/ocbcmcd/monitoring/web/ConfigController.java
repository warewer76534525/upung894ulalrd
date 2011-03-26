package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.ocbcmcd.config.service.EncryptedConfigurationService;
import com.ocbcmcd.monitoring.command.ConfigCommand;
import com.ocbcmcd.monitoring.validator.ConfigValidator;

@Controller
@RequestMapping("/config")
public class ConfigController {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigValidator validator;
	
	@Autowired()
	@Qualifier("monitoringConfigurationService")
	private EncryptedConfigurationService monitoringConfService;
	
	@Autowired()
	@Qualifier("confirmWatcherConfigurationService")
	private EncryptedConfigurationService confirmWatcherConfService;
	
	@Autowired()
	@Qualifier("ftpFileConfigurationService")
	private EncryptedConfigurationService ftpFileConfService;
	
	@Autowired()
	@Qualifier("mailSenderConfigurationService")
	private EncryptedConfigurationService mailSenderConfService;

	@RequestMapping(method = GET)
	public ModelMap setupForm(@RequestParam(required = false) ConfigCommand command) {
		command = new ConfigCommand();
		return new ModelMap("command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("command") ConfigCommand command, BindingResult result, SessionStatus status) {
		validator.validate(command, result);
		
		if (result.hasErrors())
			return "config";
		else {
			monitoringConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			confirmWatcherConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			ftpFileConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			mailSenderConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			
			return "redirect:config/?message=1";
		}
	}
	
}
