package com.filenet.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.DocumentSet;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Connection;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.property.Properties;
import com.filenet.api.property.PropertyFilter;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;

public class SampleCEApi {

	public static void main(String[] args) throws Exception {

		CEconnetion ceConn = new CEconnetion();
		Connection conn = ceConn.getConnection();
		Domain dom = ceConn.getConnection(conn);
		ObjectStore os = ceConn.getOs(dom);
		System.out.println("OS Name: " + os.get_DisplayName());

		PropertyFilter pf = new PropertyFilter();
		pf.addIncludeProperty(0, null, Boolean.TRUE, "FirstName LastName DOB Gender Address Id", null);
		SearchScope ss = new SearchScope(os);
		String query = "Select D.FirstName, D.LastName, D.DOB, D.Gender, D.Address, D.Id from Aadhar_Class D where "
				+ "D.Address='WB'";
		SearchSQL sql = new SearchSQL(query);
		DocumentSet docset = (DocumentSet) ss.fetchObjects(sql, Integer.valueOf(10), pf, Boolean.valueOf(true));
		Iterator docIt = docset.iterator();
		int i=0;
		while (docIt.hasNext()) {
			Document doc = (Document) docIt.next();
			/*
			 * System.out.println(doc.getClassName());
			 * System.out.println(doc.getProperties().getStringValue("FirstName"));
			 * System.out.println(doc.getProperties().getStringValue("LastName")); Date dc =
			 * doc.getProperties().getDateTimeValue("DOB"); System.out.println(dc);
			 * System.out.println(doc.getProperties().getStringValue("Gender"));
			 * System.out.println(doc.getProperties().getStringValue("Address"));
			 */
			System.out.println("Doc Id: " + doc.getProperties().getIdValue("Id").toString());
			doc.delete();
			doc.save(RefreshMode.NO_REFRESH);
			i++;
		}
		System.out.println("Doc Count: " +i);
	}
}
