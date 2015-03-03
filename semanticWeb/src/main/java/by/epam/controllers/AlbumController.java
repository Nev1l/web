package by.epam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.beans.MusicGroup;
import by.epam.consts.ConstsJSP;
import by.epam.implem.WorkImpement;

@Controller
public class AlbumController {

	@Autowired
	private WorkImpement work;

	@RequestMapping(value = "/album", method = RequestMethod.GET)
	public String group(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value = "groupName", required = false) String groupName,
			@RequestParam(value = "albumName", required = false) String albumName) {
		MusicGroup group = work.getGroup(groupName);
		req.setAttribute(ConstsJSP.GENRES, work.getAllGenres());
		req.setAttribute(ConstsJSP.GROUP, group);
		req.setAttribute(ConstsJSP.ALBUM, group.getMusicAlbums().get(albumName));
		return ConstsJSP.albumPage;
	}
}
