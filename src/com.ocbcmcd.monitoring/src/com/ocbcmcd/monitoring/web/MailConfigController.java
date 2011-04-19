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

import com.ocbcmcd.monitoring.command.MailConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;
import com.ocbcmcd.monitoring.validator.MailConfigValidator;

@Controller
@RequestMapping("/mailConfig")
public class MailConfigController {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigurerService mailConfigurerService;

	@Autowired
	private MailConfigValidator validator;
	
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@RequestParam(required = false) MailConfigCommand command) {
		if (command == null) {
			command = mailConfigurerService.getMailConfig();
		}
		
		return new ModelAndView("mail_config", "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("command") MailConfigCommand command, BindingResult result, SessionStatus status) {
		validator.validate(command, result);
		
		if (result.hasErrors()) {
			log.info("there is error");
			log.info(result.getAllErrors());
			return "mail_config";
		} else {
			mailConfigurerService.saveConfig(command);
			log.info("executed succesfully");
			return "redirect:mailConfig/?message=1";
		}
	}
	
}
