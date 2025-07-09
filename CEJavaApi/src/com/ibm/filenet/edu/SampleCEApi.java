package com.ibm.filenet.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.filenet.api.collection.ContentElementList;
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
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;

public class SampleCEApi {

	public static void main(String[] args) throws Exception {

		CEconnetion ceConn = new CEconnetion();
		Connection conn = ceConn.getConnection();
		Domain dom = ceConn.getConnection(conn);
		ObjectStore os = ceConn.getOs(dom);
		System.out.println("OS Name: " + os.get_DisplayName());
		TestUploadDocument tud = new TestUploadDocument();
		tud.createDire(os);
		/*
		 * Folder parentFolder = Factory.Folder.fetchInstance(os,
		 * "/Buddha/Kuila/Contai", null); System.out.println("Fatch Folder name: " +
		 * parentFolder.get_FolderName());
		 */
//		Document doc = Factory.Document.fetchInstance(os, "{80D3E397-0000-CE14-92BC-CC7EC255DA5E}", null);
		/*
		 * doc.getProperties().putValue("DocumentTitle", "SampleDocument.jpg");
		 * doc.save(RefreshMode.NO_REFRESH); ReferentialContainmentRelationship rcr =
		 * parentFolder.file(doc, AutoUniqueName.AUTO_UNIQUE, "Sample_Upload_jpg",
		 * DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
		 * rcr.save(RefreshMode.REFRESH);
		 */
		/*
		 * doc.delete(); doc.save(RefreshMode.NO_REFRESH);
		 */
		/*
		 * Folder testFolder = Factory.Folder.createInstance(os, "Folder", null);
		 * testFolder.set_Parent(parentFolder);
		 * testFolder.set_FolderName("Buddha");
		 * testFolder.save(RefreshMode.REFRESH);
		 * System.out.println("Fatch Folder name: " + testFolder.get_FolderName());
		 * 
		 * Folder testFolder2 = Factory.Folder.createInstance(os, "Folder", null);
		 * testFolder2.set_Parent(testFolder); 
		 * testFolder2.set_FolderName("Kuila");
		 * testFolder2.save(RefreshMode.REFRESH);
		 */

		/*
		 * Folder parentFolder = Factory.Folder.fetchInstance(os, "/Testedu", null);
		 * String testFolder = parentFolder.get_FolderName(); parentFolder.delete();
		 * parentFolder.save(RefreshMode.REFRESH); System.out.println(testFolder +
		 * " deleted"); System.out.println("Fatch Folder name: " +
		 * testFolder2.get_FolderName());
		 */

		/*
		 * File file = new File(
		 * "C:\\Users\\Administrator\\eclipse-workspace\\CEJavaApi\\Sample\\db1.JPG");
		 * System.out.println("File exists: " + file.exists());
		 * System.out.println("File readable: " + file.canRead());
		 * System.out.println("File size: " + file.length());
		 * 
		 * try (InputStream isStream = new FileInputStream(file)) { Document doc =
		 * Factory.Document.createInstance(os, "Aadhar_Class"); ContentTransfer ct =
		 * Factory.ContentTransfer.createInstance(); ct.setCaptureSource(isStream);
		 * ct.set_ContentType("image/jpeg"); ct.set_RetrievalName(file.getName());
		 * 
		 * ContentElementList cl = Factory.ContentElement.createList(); cl.add(ct);
		 * doc.set_ContentElements(cl);
		 * 
		 * doc.getProperties().putValue("FirstName", "Priya");
		 * doc.getProperties().putValue("LastName", "Kuila"); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date dob =
		 * sdf.parse("1999-11-11 11:00:00"); doc.getProperties().putValue("DOB", dob);
		 * doc.getProperties().putValue("Gender", "Female");
		 * doc.getProperties().putValue("Address", "WB");
		 * 
		 * doc.set_MimeType("image/jpeg");
		 * 
		 * doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
		 * doc.save(RefreshMode.REFRESH);
		 * 
		 * System.out.println("Document uploaded successfully: " + doc.get_Id());
		 */
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

}
