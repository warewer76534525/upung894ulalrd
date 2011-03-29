package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ocbcmcd.monitoring.command.AdminUpdateUserCommand;
import com.ocbcmcd.monitoring.command.UserType;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.exception.UserNotFoundException;
import com.ocbcmcd.monitoring.service.IRegistrationService;
import com.ocbcmcd.monitoring.validator.AdminUserUpdateValidator;

@Controller 
@RequestMapping("/adminUserEdit/{id}")
public class AdminUserUpdateController {
	
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private AdminUserUpdateValidator validator;
	
	@Autowired
	private IRegistrationService registrationService;
	
	@ModelAttribute("userTypes")
	public List<UserType> populateUserTypes() {
		return UserType.getUserTypes();
	}
	
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable("id") int id, @RequestParam(required = false) AdminUpdateUserCommand command) {
		User user = registrationService.getUser(id);
		if (user == null) {
			return new ModelAndView("404");
		}
		
		command = new AdminUpdateUserCommand(user);
		if (user.isAdminUser()) {
			command.setUserType(UserType.ADMIN_TYPE);
		} else if (user.isRegulerUser()) {
			command.setUserType(UserType.REGULAR_TYPE);
		}
		
		return new ModelAndView("admin_user_edit", "command", command);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitForm(@PathVariable("id") int id, @ModelAttribute("command") AdminUpdateUserCommand command, BindingResult result, SessionStatus status) {
		validator.validate(command, result);
		if (result.hasErrors())
			return new ModelAndView("admin_user_edit");
		else {
			try {
				registrationService.update(command);
				return new ModelAndView("redirect:/adminUserEdit/" + id + "?message=1");
			} catch (UserNotFoundException e) {
				return new ModelAndView("404");
			}
		}
	}
}
