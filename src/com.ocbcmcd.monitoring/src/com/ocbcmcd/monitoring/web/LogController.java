package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ocbcmcd.monitoring.query.ILogEventQuery;

@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	ILogEventQuery logQuery;
	
	@RequestMapping(method = GET)
	public ModelMap list(){
		return new ModelMap(logQuery.getLogs());
	}
}
