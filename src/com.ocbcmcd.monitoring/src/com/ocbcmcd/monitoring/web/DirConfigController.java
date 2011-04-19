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

import com.ocbcmcd.monitoring.command.DirConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;
import com.ocbcmcd.monitoring.validator.DirConfigValidator;

@Controller
@RequestMapping("/dirConfig")
public class DirConfigController {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ConfigurerService dirConfigurerService;

	@Autowired
	private DirConfigValidator validator;
	
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@RequestParam(required = false) DirConfigCommand command) {
		if (command == null) {
			command = dirConfigurerService.getDirConfig();
		}
		
		return new ModelAndView("dir_config", "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("command") DirConfigCommand command, BindingResult result, SessionStatus status) {
		validator.validate(command, result);
		
		if (result.hasErrors()) {
			log.info(result.getAllErrors());
			return "dir_config";
		} else {
			dirConfigurerService.saveConfig(command);
			return "redirect:dirConfig/?message=1";
		}
	}
	
}
