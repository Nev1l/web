package by.epam.implem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Namespace;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import by.epam.beans.Album;
import by.epam.beans.Genre;
import by.epam.beans.MusicArtist;
import by.epam.beans.MusicGroup;
import by.epam.beans.Song;
import by.epam.connection.Connection;
import by.epam.dao.WorkDAO;

@Repository
public class WorkImpement implements WorkDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(WorkImpement.class);
	private RepositoryConnection con;
	private String prefixes;

	public WorkImpement() {
		super();
		con = Connection.getConnection();
	}

	public Map<String, MusicGroup> getGroupsByGenre(String genreName) {
		prefixes = getPrefixes();
		Map<String, MusicGroup> groupList = new HashMap<String, MusicGroup>();
		try {
			String queryString = prefixes
					+ "SELECT ?gName ?gDescription ?gImage ?aName ?aYear ?aImage ?name ?text WHERE {?s :hasGenre ?genre. ?genre :name \""
					+ genreName
					+ "\".  ?s :name ?name.  ?s rdfs:comment ?text.  ?s :isSongOfAlbum ?a.  ?a :name ?aName.  ?a :year ?aYear. ?a :image ?aImage.  ?a :hasPerformer ?g.  ?g :name ?gName.  ?g rdfs:comment ?gDescription. ?g :image ?gImage}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value gName = bindingSet.getValue("gName");
					Value gDescription = bindingSet.getValue("gDescription");
					Value gImage = bindingSet.getValue("gImage");
					Value aName = bindingSet.getValue("aName");
					Value aImage = bindingSet.getValue("aImage");
					Value aYear = bindingSet.getValue("aYear");
					Value name = bindingSet.getValue("name");
					Value text = bindingSet.getValue("text");
					// Value download = bindingSet.getValue("download");
					MusicGroup group = new MusicGroup();
					group.setName(gName.stringValue());
					group.setImage(gImage.stringValue());
					group.setDescription(gDescription.stringValue());
					Album album = new Album();
					album.setImage(aImage.stringValue());
					album.setName(aName.stringValue());
					album.setYear(aYear.stringValue());
					Song song = new Song();
					song.setName(name.stringValue());
					song.setText(text.stringValue());
					// song.setDownloadURL(download.stringValue());
					//song.setGenreList(getGenres(song.getName()));
					if (!groupList.containsKey(group.getName())) {
						groupList.put(group.getName(), group);
					}
					MusicGroup temp = groupList.get(group.getName());
					if (!temp.getMusicAlbums().containsKey(album.getName())) {
						temp.getMusicAlbums().put(album.getName(), album);
					}
					temp.getMusicAlbums().get(album.getName()).songAdd(song);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return groupList;
	}

	public Genre getGenreInfo(String genreName) {
		//DOESN'T WORK! NOT USE
		prefixes = getPrefixes();
		Genre genre = new Genre();
		try {
			//. ?g rdfs:comment ?description
			String queryString = prefixes
					+ "SELECT ?description WHERE {?g :name \"" + genreName
					+ "\"?g rdfs:comment ?description}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				if (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value description = bindingSet.getValue("description");
					genre.setName(genreName);
					genre.setDescription(description.stringValue());
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return genre;
	}

	public List<Song> getSongs(String albumName) {
		prefixes = getPrefixes();
		List<Song> albumSongs = new ArrayList<Song>();
		try {
			String queryString = prefixes
					+ "SELECT ?name ?text  WHERE {?song :isSongOfAlbum ?album. ?album :name \""
					+ albumName
					+ "\". ?song :name ?name. ?song rdfs:comment ?text}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					Value text = bindingSet.getValue("text");
					// Value download = bindingSet.getValue("download");
					Song song = new Song();
					song.setName(name.stringValue());
					song.setText(text.stringValue());
					// song.setDownloadURL(download.stringValue());
					song.setGenreList(getGenres(song.getName()));
					albumSongs.add(song);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return albumSongs;
	}

	public MusicGroup getGroup(String groupName) {
		prefixes = getPrefixes();
		MusicGroup musicGroup = null;
		try {
			String queryString = prefixes
					+ "SELECT ?name ?image ?description WHERE {?group rdf:type :MusicGroup. ?group :name ?name. filter regex(?name,'"
					+ groupName
					+ "',\"i\"). ?group :image ?image. ?group rdfs:comment ?description}";
			logger.debug(queryString);
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				if (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					Value image = bindingSet.getValue("image");
					Value description = bindingSet.getValue("description");
					musicGroup = new MusicGroup();
					musicGroup.setName(name.stringValue());
					musicGroup.setImage(image.stringValue());
					musicGroup.setDescription(description.stringValue());
					musicGroup
							.setMusicArtistList(getArtists(name.stringValue()));
					musicGroup.setMusicAlbums(getAlbums(musicGroup.getName()));
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return musicGroup;
	}

	// ========[End prefixes changes]=====
	public List<MusicGroup> getGroups() {
		prefixes = getPrefixes();
		List<MusicGroup> list = new ArrayList<MusicGroup>();
		try {
			String queryString = prefixes
					+ "SELECT ?name ?image ?description WHERE {?group rdf:type :MusicGroup. ?group :name ?name. ?group :image ?image}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					Value image = bindingSet.getValue("image");
					MusicGroup musicGroup = new MusicGroup();
					musicGroup.setName(name.stringValue());
					musicGroup.setImage(image.stringValue());
					musicGroup.setMusicAlbums(getAlbums(musicGroup.getName()));
					list.add(musicGroup);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public Set<Genre> getAllGenres() {
		prefixes = getPrefixes();
		Set<Genre> list = new LinkedHashSet<Genre>();
		try {
			// . ?genre rdfs:comment ?description
			String queryString = prefixes
					+ "SELECT distinct ?name WHERE{?type rdfs:subClassOf* :Genre.?obj rdf:type owl:NamedIndividual.?obj rdf:type ?type.?obj :name ?name}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					// Value description = bindingSet.getValue("description");
					Genre genre = new Genre();
					genre.setName(name.stringValue());
					// genre.setDescription(description.stringValue());
					list.add(genre);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public Set<Genre> getGenres(String songName) {
		prefixes = getPrefixes();
		Set<Genre> list = new LinkedHashSet<Genre>();
		try {
			String queryString = prefixes
					+ "SELECT ?name WHERE {?song :hasGenre ?genre. ?song :name \""
					+ songName + "\". ?genre :name ?name}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					// Value image = bindingSet.getValue("image");
					Genre genre = new Genre();
					genre.setName(name.stringValue());
					list.add(genre);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public Map<String, MusicArtist> getArtists(String groupName) {
		prefixes = getPrefixes();
		Map<String, MusicArtist> list = new HashMap<String, MusicArtist>();
		try {
			String queryString = prefixes
					+ "SELECT ?name ?image ?description WHERE {?artist rdf:type :MusicArtist. ?artist :name ?name. ?artist :image ?image. ?artist rdfs:comment ?description. ?artist :hasMusicGroup ?group. ?group :name \""
					+ groupName + "\"}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					Value image = bindingSet.getValue("image");
					Value description = bindingSet.getValue("description");
					MusicArtist musicArtist = new MusicArtist();
					musicArtist.setName(name.stringValue());
					musicArtist.setImage(image.stringValue());
					musicArtist.setDescription(description.stringValue());
					list.put(musicArtist.getName(), musicArtist);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public Map<String, Album> getAlbums(String groupName) {
		prefixes = getPrefixes();
		Map<String, Album> list = new HashMap<String, Album>();
		try {
			String queryString = prefixes
					+ "SELECT ?name ?image ?year WHERE {?album :hasPerformer ?group. ?group :name \""
					+ groupName
					+ "\". ?album :name ?name. ?album :image ?image. ?album :year ?year}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					Value name = bindingSet.getValue("name");
					Value image = bindingSet.getValue("image");
					Value description = bindingSet.getValue("year");
					Album musicAlbum = new Album();
					musicAlbum.setName(name.stringValue());
					musicAlbum.setImage(image.stringValue());
					musicAlbum.setYear(description.stringValue());
					musicAlbum.setSongList(getSongs(musicAlbum.getName()));
					list.put(name.stringValue(), musicAlbum);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	private String getPrefixes() {
		return getPrefixes(getPrefixMap(Connection.getConnection()));
	}

	private Map<String, String> getPrefixMap(RepositoryConnection con) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			RepositoryResult<Namespace> result = con.getNamespaces();
			while (result.hasNext()) {
				Namespace ns = result.next();
				map.put(ns.getPrefix(), ns.getName());
			}
		} catch (RepositoryException e) {
			logger.error(e.getMessage());
		}
		return map;
	}

	private String getPrefixes(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append("PREFIX " + entry.getKey() + ": <" + entry.getValue()
					+ ">\n");
		}
		logger.info(sb.toString());
		return sb.toString();
	}

}
