package by.epam.sesame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.aduna.iteration.Iterations;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Model;
import org.openrdf.model.Namespace;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.repository.manager.RemoteRepositoryManager;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.Rio;

import by.epam.beans.MusicGroup;
import by.epam.implem.WorkImpement;

//http://www.dataversity.net/empire-rdf-sparql-meet-jpa/
public class TestSesame {

	// http://rdf4j.org/sesame/2.7/docs/users.docbook?view#section-repository-api
	public static void main(String[] args) {
		test();
	}

	public static void test() {
		String sesameServer = "http://localhost/openrdf-sesame";
		String repositoryID = "db";
		RepositoryConnection conn = null;
		try {

			Repository rep = new HTTPRepository(sesameServer, repositoryID);
			rep.initialize();
			String namespace = "http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#";
			ValueFactory f = rep.getValueFactory();
			conn = rep.getConnection();
			String prefix = "http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#";
			List<MusicGroup> list = new ArrayList<MusicGroup>();
			try {// ; ?name <"+prefix+"name>
				String queryString = "SELECT ?name ?image WHERE {?name a <"
						+ prefix + "MusicGroup> ?image rdfs:name}";
				TupleQuery tupleQuery = conn.prepareTupleQuery(
						QueryLanguage.SPARQL, queryString);
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
		} catch (RepositoryException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (RepositoryException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Map<String, String> getPrefixMap(RepositoryConnection conn) {
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

	public static String getPrefixes(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + ":" + entry.getValue() + '\n');
		}
		return sb.toString();
	}
}
