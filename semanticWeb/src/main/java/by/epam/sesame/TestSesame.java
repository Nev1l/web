package by.epam.sesame;

import java.util.HashMap;
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
		// RemoteRepositoryManager manager = null;
		// manager = new RemoteRepositoryManager(sesameServer);
		// manager.initialize();
		// manager.getRepository(repositoryID);
		RepositoryConnection conn = null;
		try {

			Repository rep = new HTTPRepository(sesameServer, repositoryID);
			rep.initialize();
			String namespace = "http://www.semanticweb.org/viktar_kapachou/ontologies/2015/1/untitled-ontology-7#";
			ValueFactory f = rep.getValueFactory();
			conn = rep.getConnection();
			URI song = f.createURI(namespace, "Song");
			// conn.add(john, RDF.TYPE, FOAF.PERSON); conn.add(john, RDFS.LABEL,
			// f.createLiteral("John", XMLSchema.STRING));

			RepositoryResult<Statement> statements = conn.getStatements(null,
					song, null, true);
			Model model = Iterations.addAll(statements, new LinkedHashModel());
			model.setNamespace("rdf", RDF.NAMESPACE);
			model.setNamespace("rdfs", RDFS.NAMESPACE);
			model.setNamespace("xsd", XMLSchema.NAMESPACE);
			model.setNamespace("owl", OWL.NAMESPACE);
			model.setNamespace("", namespace);
			System.out.println(getPrefixes(getPrefixMap(conn)));
			new WorkImpement().getGroups();
			// Rio.write(model, System.out, RDFFormat.TURTLE);
			// ----------http://stackoverflow.com/questions/21029584/how-to-print-the-retrieved-statements-in-sparql-after-executing-a-query
			// System.out.println("Success!");

		} catch (RepositoryException e) {
			e.printStackTrace();
		}/*
		 * catch (RDFHandlerException e1) { e1.printStackTrace(); }
		 */finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (RepositoryException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void test1() {
		String serverUrl = "http://localhost:8080/openrdf-sesame/db";
		RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
		try {
			manager.initialize();
			System.out.println("Success!");
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		String sesameServer = "http://localhost/openrdf-workbench/repositories";
		String repositoryID = "db";
		Repository myRepository = new HTTPRepository(sesameServer, repositoryID);
		try {
			myRepository.initialize();
			RepositoryConnection con = myRepository.getConnection();
			ValueFactory f = myRepository.getValueFactory();
			System.out.println("Success");
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public String getQueData() throws IllegalArgumentException { String
	 * message = null; try { HttpClient httpclient = new DefaultHttpClient();
	 * JSONParser parser = new JSONParser();
	 * 
	 * String url = "working - url"; HttpResponse response = null; response =
	 * httpclient.execute(new HttpGet(url));
	 * 
	 * JSONObject json_data = null; json_data = (JSONObject)
	 * parser.parse(EntityUtils.toString(response .getEntity())); JSONArray
	 * results = (JSONArray) json_data.get("result"); for (Object queid :
	 * results) { message = message.concat((String) ((JSONObject) queid)
	 * .get("id")); message = message.concat("\t"); message =
	 * message.concat((String) ((JSONObject) queid) .get("owner")); message =
	 * message.concat("\n"); } } catch (Exception e) { message = e.toString(); }
	 * return message; }
	 */

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
			sb.append(entry.getKey() + ":" + entry.getValue()+'\n');
		}
		return sb.toString();
	}
}
