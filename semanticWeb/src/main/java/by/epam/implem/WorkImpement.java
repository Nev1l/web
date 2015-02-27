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
import org.springframework.stereotype.Repository;

import by.epam.beans.MusicGroup;
import by.epam.connection.Connection;
import by.epam.dao.WorkDAO;

@Repository
public class WorkImpement implements WorkDAO {
	private RepositoryConnection con;
	private Map<String,String> prefixes;
	
	public WorkImpement() {
		super();
		this.con = Connection.getConnection();
		this.prefixes = getPrefixMap();
	}

	public void getSong() {
		try {
			String queryString = "SELECT ?song WHERE {?song <http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#hasGenre> ?g}";
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
			// handle exception
		}
	}

	public List<MusicGroup> getGroups() {
		String prefix = "http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#";
		List<MusicGroup> list = new ArrayList<MusicGroup>();
		try {// ; ?name <"+prefix+"name>
			String queryString = "SELECT ?name ?image WHERE {?name a <"
					+ prefix + "MusicGroup> ?image rdfs:name}";
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
			// handle exception
		}
		return list;
	}

	public Map<String, String> getPrefixMap() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			RepositoryResult<Namespace> result = con.getNamespaces();
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
