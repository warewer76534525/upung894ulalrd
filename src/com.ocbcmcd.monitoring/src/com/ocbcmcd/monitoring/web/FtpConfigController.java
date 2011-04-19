package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.ocbcmcd.monitoring.command.FtpConfigCommand;
import com.ocbcmcd.monitoring.command.OldConfigCommand;

@Controller
@RequestMapping("/ftpConfig")
public class FtpConfigController {
	protected Log log = LogFactory.getLog(getClass());
	
	
	@RequestMapping(method = GET)
	public ModelMap setupForm(@RequestParam(required = false) FtpConfigCommand command) {
		command = new FtpConfigCommand();
		return new ModelMap("command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("command") OldConfigCommand command, BindingResult result, SessionStatus status) {
		/*validator.validate(command, result);
		
		if (result.hasErrors())
			return "config";
		else {
			monitoringConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			confirmWatcherConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			ftpFileConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			mailSenderConfService.setAndEncryptProperty(command.getConfigName(), command.getConfigValue());
			
			return "redirect:config/?message=1";
		}*/
		return null;
	}
	
}
