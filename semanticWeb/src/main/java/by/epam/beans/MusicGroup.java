package by.epam.beans;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MusicGroup {
	private String name;
	private String image;
	private String description;
	private Map<String, MusicArtist> musicArtistList;
	private Map<String, Album> musicAlbums;
	private Set<Genre> genreList = new LinkedHashSet<Genre>();

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

	public Set<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(Set<Genre> genreList) {
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

	public Map<String, Album> getMusicAlbums() {
		return musicAlbums;
	}

	public void setMusicAlbums(Map<String, Album> musicAlbums) {
		this.musicAlbums = musicAlbums;
		for (Map.Entry<String, Album> entry : musicAlbums.entrySet()) {
			this.genreList.addAll(entry.getValue().getGenreList());
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[MusicGroup:" + name + ", url=" + image + "]" + musicArtistList;
	}

}
