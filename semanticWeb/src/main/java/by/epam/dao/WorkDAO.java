package by.epam.dao;

import java.util.Map;

public interface WorkDAO extends AlbumDAO, GenreDAO, MusicArtistDAO,
		MusicGroupDAO, SongDAO {
	Map<String, String> getPrefixMap();

	String getPrefixes(Map<String, String> map);
}
