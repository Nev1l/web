package by.epam.implem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import by.epam.beans.Genre;
import by.epam.beans.MusicGroup;
import by.epam.connection.Connection;
import by.epam.dao.WorkDAO;

@Repository
public class WorkImpement implements WorkDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(WorkImpement.class);
	private static RepositoryConnection con;
	private static String prefixes;

	public WorkImpement() {
		super();
		con = Connection.getConnection();
	}

	public void getSong() {
		try {
			String queryString = prefixes
					+ "SELECT ?song WHERE {?song <http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#hasGenre> ?g}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) { // iterate over the result
					BindingSet bindingSet = result.next();
					Value valueOfX = bindingSet.getValue("song");
					// Value valueOfY = bindingSet.getValue("y");
					System.out.println(valueOfX);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}
	}

	public List<MusicGroup> getGroups() {
		prefixes = getPrefixes(getPrefixMap(Connection.getConnection()));
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

	public List<Genre> getGenres() {
		prefixes = getPrefixes(getPrefixMap(Connection.getConnection()));
		List<Genre> list = new ArrayList<Genre>();
		/*try {
			String queryString = prefixes
					+ "SELECT ?name WHERE {  ?genre rdfs:subClassOf :Genre. ?igenre rdf:type ?genre. ?igenre :name ?name. ?igenre :image ?image}";
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
					list.add(musicGroup);
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			logger.error(e.getMessage());
		}*/
		return list;
	}

	private static Map<String, String> getPrefixMap(RepositoryConnection con) {
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

	private static String getPrefixes(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append("PREFIX " + entry.getKey() + ": <" + entry.getValue()
					+ ">\n");
		}
		logger.info(sb.toString());
		return sb.toString();
	}

}
