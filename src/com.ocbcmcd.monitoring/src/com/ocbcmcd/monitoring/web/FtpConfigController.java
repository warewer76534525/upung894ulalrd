package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ocbcmcd.monitoring.command.FtpConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;
import com.ocbcmcd.monitoring.service.impl.ServiceController;
import com.ocbcmcd.monitoring.validator.FtpConfigValidator;

@Controller
@RequestMapping("/ftpConfig")
public class FtpConfigController {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigurerService ftpConfigurerService;

	@Autowired
	private FtpConfigValidator validator;
	
	@Autowired 
	private ServiceController serviceController;
	
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@RequestParam(required = false) FtpConfigCommand command) {
		if (command == null) {
			command = ftpConfigurerService.getFtpConfig();
		}
		
		return new ModelAndView("ftp_config", "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("command") FtpConfigCommand command, BindingResult result, SessionStatus status) {
		validator.validate(command, result);
		
		if (result.hasErrors()) {
			log.info("there is error");
			log.info(result.getAllErrors());
			return "ftp_config";
		} else {
			ftpConfigurerService.saveConfig(command);
			serviceController.restartService();
			return "redirect:ftpConfig/?message=1";
		}
	}
	
}
