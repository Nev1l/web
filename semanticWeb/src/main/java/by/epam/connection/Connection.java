package by.epam.connection;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class Connection {
	final static String sesameServer = "http://localhost/openrdf-sesame";
	final static String repositoryID = "db";
	private static RepositoryConnection conn;
	private static Repository rep;

	private Connection() {
		super();

	}

	static {
		rep = new HTTPRepository(sesameServer, repositoryID);
		try {
			rep.initialize();
			conn = rep.getConnection();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized RepositoryConnection getConnection() {
		return conn;
	}

	public static void closeConnection(RepositoryConnection rc) {
		if (rc != null) {
			rc = null;
		}
	}
}
