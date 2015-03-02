package by.epam.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.MusicGroup;
import by.epam.consts.ConstsJSP;
import by.epam.implem.WorkImpement;

@Controller
public class GroupController {
	@Autowired
	private WorkImpement work;
	private static final Logger logger = LoggerFactory
			.getLogger(GroupController.class);

	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String group(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "name", required = false) String name) {
		MusicGroup group = work.getGroup(name);
		logger.info("group name:"+name+" obj="+group);
		req.setAttribute(ConstsJSP.GROUP, group);
		return ConstsJSP.groupPage;
	}
}
