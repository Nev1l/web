package by.epam.beans;

import java.util.LinkedHashSet;
import java.util.Set;

public class Song {
	private String name;
	private String text;
	private String downloadURL;
	private Set<Genre> genreList = new LinkedHashSet<Genre>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public Set<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(Set<Genre> genreList) {
		this.genreList = genreList;
	}

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

}
