package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.Genre;
import by.epam.consts.ConstsJSP;
import by.epam.implem.WorkImpement;

@Controller
public class GenreController {
	@Autowired
	private WorkImpement work;
	private static final Logger logger = LoggerFactory
			.getLogger(GenreController.class);

	@RequestMapping(value = "/genre", method = RequestMethod.GET)
	public String group(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "genreName", required = false) String genreName) {
		Genre genre = new Genre();
		genre.setName(genreName);
		req.setAttribute(ConstsJSP.GENRE, genre);
		req.setAttribute(ConstsJSP.GENRES, work.getAllGenres());
		req.setAttribute(ConstsJSP.GROUPS, work.getGroupsByGenre(genreName));
		logger.info("info:"+work.getGroupsByGenre(genreName));
		return ConstsJSP.genrePage;
	}

}
