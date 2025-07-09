package com.ibm.filenet.edu;

import java.util.Iterator;

import javax.security.auth.Subject;

import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.constants.AccessLevel;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

public class CEconnetion {
	private Subject subject = null;
	private ObjectStore os = null;

	public Connection getConnection() throws Exception {
		String url = "http://localhost:9080/wsi/FNCEWS40MTOM";

		Connection conn = Factory.Connection.getConnection(url);
		if (conn != null) {
			subject = UserContext.createSubject(conn, "admin", "Password123", "FileNetP8WSI");
			UserContext.get().pushSubject(subject);
			System.out.println("Go the connection");

		} else {
			System.out.println("Error in connecting to FileNet");
		}
		return conn;

	}

	public Domain getConnection(Connection conn) {
		// TODO Auto-generated method stub
		String domainame = "HIT";
		Domain domain = Factory.Domain.fetchInstance(conn, domainame, null);
		System.out.println("Name of the domain: " + domain.get_Name());
		return domain;
	}

	public ObjectStore getOs(Domain dom) {
		ObjectStoreSet osSet = dom.get_ObjectStores();
		ObjectStore os = null;
		Iterator it = osSet.iterator();
		Iterator<ObjectStore> osIter = it;

		while (osIter.hasNext()) {
			os = osIter.next();

			if ((os.getAccessAllowed().intValue() & AccessLevel.USE_OBJECT_STORE.getValue()) > 0) {
				System.out.println(os.get_Name());
				if (os.get_Name().equalsIgnoreCase("MCA")) {
					break;

				}
			}
		}

		return os;
	}
}