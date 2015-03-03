package by.epam.beans;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Album {
	private String name;
	private String year;
	private String image;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Album [name=" + name + ", year=" + year + "]";
	}

}
