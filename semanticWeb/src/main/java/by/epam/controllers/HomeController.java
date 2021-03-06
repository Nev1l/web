package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.epam.consts.ConstsJSP;
import by.epam.implem.WorkImpement;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private WorkImpement work;
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute(ConstsJSP.GENRES, work.getAllGenres());
		req.setAttribute(ConstsJSP.GROUPS, work.getGroups());
		return "home";
	}

}
