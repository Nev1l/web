package by.epam.beans;

import java.util.List;
import java.util.Map;

public class MusicGroup {
	private String name;
	private String image;
	private String description;
	private Map<String, MusicArtist> musicArtistList;
	private List<Genre> genreList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public MusicGroup() {
		super();
	}

	public Map<String, MusicArtist> getMusicArtistList() {
		return musicArtistList;
	}

	public void setMusicArtistList(Map<String, MusicArtist> musicArtistList) {
		this.musicArtistList = musicArtistList;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[MusicGroup:" + name + ", url=" + image + "]" + musicArtistList;
	}

}
