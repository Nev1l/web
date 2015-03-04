package by.epam.beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Album {
	private String name;
	private String year;
	private String image;
	private List<Song> songList = new ArrayList<Song>();
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

	public void songAdd(Song song){
		if(!songList.contains(song)){
			songList.add(song);
			genreList.addAll(song.getGenreList());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
