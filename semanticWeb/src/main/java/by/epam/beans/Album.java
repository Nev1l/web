package by.epam.beans;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Album {
	private String name;
	private int year;
	private String performer;
	private List<Song> songList;
	private Set<Genre> genreList = new LinkedHashSet<Genre>();

	public Album() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
		for (Song song : songList) {
			this.genreList.addAll(song.getGenreList());
		}
	}

	public Set<Genre> getGenreList() {
		return genreList;
	}

}
