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

import com.ocbcmcd.monitoring.command.DbConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;
import com.ocbcmcd.monitoring.validator.DbConfigValidator;

@Controller
@RequestMapping("/dbConfig")
public class DbConfigController {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigurerService dbConfigurerService;

	@Autowired
	private DbConfigValidator validator;
	
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@RequestParam(required = false) DbConfigCommand command) {
		if (command == null) {
			command = dbConfigurerService.getDbConfig();
		}
		
		return new ModelAndView("db_config", "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("command") DbConfigCommand command, BindingResult result, SessionStatus status) {
		validator.validate(command, result);
		
		if (result.hasErrors()) {
			log.info(result.getAllErrors());
			return "db_config";
		} else {
			dbConfigurerService.saveConfig(command);
			return "redirect:dbConfig/?message=1";
		}
	}
	
}
