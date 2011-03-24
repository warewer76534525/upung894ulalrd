package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.ocbcmcd.monitoring.command.RegistrationCommand;

@Controller
@RequestMapping("/log")
public class LogController {
	
	@RequestMapping(method = GET)
	public ModelMap setupForm(@ModelAttribute("command") RegistrationCommand command, BindingResult result, SessionStatus status) {
		command = new RegistrationCommand();
		return new ModelMap("command", command);
	}
	
}
