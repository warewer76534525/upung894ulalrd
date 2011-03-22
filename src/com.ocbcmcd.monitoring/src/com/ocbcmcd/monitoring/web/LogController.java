package com.ocbcmcd.monitoring.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ocbcmcd.monitoring.query.ILogEventQuery;

@Controller
@RequestMapping("/log")
public class LogController {
	@Autowired
	private ILogEventQuery logQuery;
	protected Log log = LogFactory.getLog(getClass());

	@Value("${paging.size}")
	private String _pageSize;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = GET)
	public ModelMap list(@RequestParam(required = false, value="p") String p, @RequestParam(required = false, value="startDate") String startDate, @RequestParam(required = false, value="endDate") String endDate, Model model) {
		int page = 0;
		List searchResults = logQuery.getLogs();
		PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
		
		try {
			page = Integer.parseInt(p);
		} catch (NumberFormatException e) {

		}
		
		pagedListHolder.setPage(page);
		int pageSize = Integer.parseInt(_pageSize);
		pagedListHolder.setPageSize(pageSize);

		return new ModelMap("pagedListHolder", pagedListHolder);
	}
}
