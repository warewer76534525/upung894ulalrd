package com.ocbcmcd.monitoring.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.exception.UserNotFoundException;
import com.ocbcmcd.monitoring.query.IUserQuery;
import com.ocbcmcd.monitoring.service.IRegistrationService;

@Controller
 
public class UserController {
	protected Log log = LogFactory.getLog(getClass());
	
	private static final int DISABLE_MESSAGE_ID = 5;
	private static final int ENABLE_MESSAGE_ID = 4;
	
	@Autowired
	private IUserQuery userQuery;
	
	@Autowired
	private IRegistrationService registrationService;
	
	@Value("${paging.size}")
	private String _pageSize;
	
	@RequestMapping("/userList")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView list(@RequestParam(required = false) String p, Model model) {
		int page = 0;
		List searchResults = userQuery.getUsers();
		PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
		
		try {
			page = Integer.parseInt(p);
		} catch (NumberFormatException e) {

		}
		
		pagedListHolder.setPage(page);
		int pageSize = Integer.parseInt(_pageSize);
		pagedListHolder.setPageSize(pageSize);

		return new ModelAndView("user", "pagedListHolder", pagedListHolder);
	}
	
	@RequestMapping("/userDetail/{id}")
	public ModelAndView detail(@PathVariable("id") int id, Model model) {
		User user = registrationService.getUser(id);
		if (user != null) {
			return new ModelAndView("user_detail", "user", user);
		} else {
			return new ModelAndView("404");
		}
	}
	
	@RequestMapping("/userView/{userName}")
	public ModelAndView view(@PathVariable("userName") String userName, Model model) {
		User user = registrationService.getUser(userName);
		if (user != null) {
			return new ModelAndView("redirect:/userEdit/"+ user.getId());
		} else {
			return new ModelAndView("404");
		}
	}
	
	@RequestMapping("/userDisable/{id}")
	public ModelAndView disable(@PathVariable("id") int id, Model model) {
		String url;
		try {
			registrationService.disable(id);
			url = "redirect:/userDetail/" + id + "?message=" + DISABLE_MESSAGE_ID;
			
			return new ModelAndView(url);
		} catch (UserNotFoundException e) {
			return new ModelAndView("404");
		}
	}
	
	@RequestMapping("/userEnable/{id}")
	public ModelAndView enable(@PathVariable("id") int id, Model model) {
		String url;
		try {
			registrationService.enable(id);
			url = "redirect:/userDetail/" + id + "/?message=" + ENABLE_MESSAGE_ID;
			
			return new ModelAndView(url);
		} catch (UserNotFoundException e) {
			return new ModelAndView("404");
		}
	}

}
