package by.epam.implem;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;

import by.epam.beans.MusicGroup;
import by.epam.connection.Connection;
import by.epam.dao.WorkDAO;

public class WorkImpement extends WorkDAO {
	private RepositoryConnection con;

	public WorkImpement() {
		super();
		this.con = Connection.getConnection();
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
			String queryString = "SELECT ?musicGroup ?image WHERE {?musicGroup a <"
					+ prefix + "MusicGroup>}";
			TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL,
					queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					System.out.println(bindingSet.getValue("musicGroup"));
					/*
					 * Value name = bindingSet.getValue("name"); Value image =
					 * bindingSet.getValue("image"); MusicGroup musicGroup = new
					 * MusicGroup(); musicGroup.setName(name.stringValue());
					 * musicGroup.setImage(image.stringValue());
					 * list.add(musicGroup); System.out.println(name+" "+image);
					 */
				}
			} finally {
				result.close();
			}
		} catch (OpenRDFException e) {
			// handle exception
		}
		return null;
	}
}
