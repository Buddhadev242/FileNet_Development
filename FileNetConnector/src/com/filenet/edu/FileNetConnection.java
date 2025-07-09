package com.filenet.edu;

import com.filenet.api.core.*;
import com.filenet.api.util.UserContext;
import javax.security.auth.Subject;

public class FileNetConnection {

    public static void main(String[] args) {
        try {
            // 1. FileNet server URL
            String uri = "http://your-server:port/wsi/FNCEWS40MTOM/";
            Connection conn = Factory.Connection.getConnection(uri);

            // 2. Authentication
            Subject subject = UserContext.createSubject(conn, "your-username", "your-password", null);
            UserContext.get().pushSubject(subject);

            // 3. Fetch domain and object store
            Domain domain = Factory.Domain.fetchInstance(conn, null, null);
            ObjectStore os = Factory.ObjectStore.fetchInstance(domain, "YourObjectStore", null);

            System.out.println("Connected to Object Store: " + os.get_Name());

            // Pop subject when done
            UserContext.get().popSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
