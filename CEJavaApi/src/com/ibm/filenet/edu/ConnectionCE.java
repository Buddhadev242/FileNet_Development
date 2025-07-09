package com.ibm.filenet.edu;

import javax.security.auth.Subject;

import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.util.UserContext;


	public class ConnectionCE {
	
		private Subject subject = null;
		private ObjectStore os = null;
	
		public ObjectStore getConnection() throws Exception{
			String url = "http://localhost:9080/wsi/FNCEWS40MTOM";
	
			Connection conn = Factory.Connection.getConnection(url);
			if (conn != null) {
				subject = UserContext.createSubject(conn, "admin", "Password123", "FileNetP8WSI");
				UserContext.get().pushSubject(subject);
				
				Domain domain = Factory.Domain.fetchInstance(conn, "HIT", null);
				System.out.println(domain.get_Name());
				os = Factory.ObjectStore.fetchInstance(domain, "MCA", null);
				System.out.println("CE Connection fetched : " + os.get_DisplayName());
	
			} else {
				System.out.println("Error in connecting to FileNet");
			}
			return os;
	
		}
		
//		getDomain(Connection Conn)
//		
//		getOS(Domain domain)
//	
	}