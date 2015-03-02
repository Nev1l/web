package by.epam.controllers;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.MusicArtist;
import by.epam.beans.MusicGroup;
import by.epam.consts.ConstsJSP;
import by.epam.implem.WorkImpement;

@Controller
public class ArtistController {

	@Autowired
	private WorkImpement work;
	private static final Logger logger = LoggerFactory
			.getLogger(ArtistController.class);

	@RequestMapping(value = "/artist", method = RequestMethod.GET)
	public String group(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "groupName", required = false) String groupName,
			@RequestParam(value = "artistName", required = false) String artistName) {
		MusicGroup group = work.getGroup(groupName);
		req.setAttribute(ConstsJSP.GROUP, group);
		req.setAttribute(ConstsJSP.ARTIST,  group.getMusicArtistList().get(artistName));
		return ConstsJSP.artistPage;
	}

}
