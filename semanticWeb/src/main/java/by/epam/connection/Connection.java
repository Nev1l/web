package by.epam.connection;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connection {
	private static final Logger logger = LoggerFactory
			.getLogger(Connection.class);

	final static String port = "80";
	final static String sesameServer = "http://localhost:" + port
			+ "/openrdf-sesame";
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
		} catch (RepositoryException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized RepositoryConnection getConnection() {
		/*rep = new HTTPRepository(sesameServer, repositoryID);
		try {
			// RemoteRepositoryManager manager = new
			// RemoteRepositoryManager(sesameServer);
			// manager.initialize();
			// rep = manager.getRepository(repositoryID);
			// rep = manager.getRepository(repositoryID);
			rep.initialize();
			conn = rep.getConnection();
		} catch (RepositoryException e) {
			logger.error("Error on creating repository connection", e);
		}*/
		// =========[no singleton]==================
		return conn;
	}

	public static void closeConnection(RepositoryConnection rc) {
		if (rc != null) {
			rc = null;
		}
	}
}
