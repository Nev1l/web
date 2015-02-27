package by.epam.dao;

import java.util.HashMap;
import java.util.Map;

import org.openrdf.model.Namespace;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public abstract class WorkDAO implements AlbumDAO, GenreDAO, MusicArtistDAO,
		MusicGroupDAO, SongDAO {
	public Map<String, String> getPrefixMap(RepositoryConnection conn) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			RepositoryResult<Namespace> result = conn.getNamespaces();
			while (result.hasNext()) {
				Namespace ns = result.next();
				map.put(ns.getPrefix(), ns.getName());
			}
		} catch (RepositoryException e) {
			throw new RuntimeException(e);
		}
		return map;
	}

	public String getPrefixes(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + ":" + entry.getValue());
		}
		return sb.toString();
	}
}
