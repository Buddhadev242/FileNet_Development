package com.ibm.filenet.edu;

import com.filenet.api.core.*;
import com.filenet.api.util.UserContext;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.*;
import com.filenet.api.engine.*;
import com.filenet.api.property.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.Subject;

public class TestUploadDocument {

	public void createDire(ObjectStore os) {
		String[] al = { "A", "B", "C"};
		String[] num = { "1", "2", "3"};
		Folder parentFolder = Factory.Folder.fetchInstance(os, "/", null);

		Folder testFolder = Factory.Folder.createInstance(os, "Folder", null);
		testFolder.set_Parent(parentFolder);
		testFolder.set_FolderName("Broke");
		testFolder.save(RefreshMode.REFRESH);
		System.out.println("Fatch Folder name: " + testFolder.get_FolderName());

		Folder testFolder2 = Factory.Folder.createInstance(os, "Folder", null);
		testFolder2.set_Parent(testFolder);
		testFolder2.set_FolderName("Contai");
		testFolder2.save(RefreshMode.REFRESH);
		System.out.println("Fatch Folder name: " + testFolder2.get_FolderName());

		for (String S1 : al) {
			Folder firstFolder = Factory.Folder.createInstance(os, "Folder", null);
			firstFolder.set_Parent(testFolder2);
			firstFolder.set_FolderName(S1);
			firstFolder.save(RefreshMode.REFRESH);
			System.out.println("Fatch Folder name: " + firstFolder.get_FolderName());

			for (String S2 : num) {
				Folder secondFolder = Factory.Folder.createInstance(os, "Folder", null);
				secondFolder.set_Parent(firstFolder);
				secondFolder.set_FolderName(S2);
				secondFolder.save(RefreshMode.REFRESH);
				System.out.println("Fatch Folder name: " + secondFolder.get_FolderName());

				for (int i = 1; i <= 3; i++) {
					String docName = S1 + "_" + S2 + "_" + i + ".jpg";
					try {
						File file = new File("C:\\Users\\Administrator\\eclipse-workspace\\CEJavaApi\\Sample\\db1.JPG");
						InputStream isStream = null;
						isStream = new FileInputStream(file);
						Document doc = Factory.Document.createInstance(os, "Aadhar_Class");
						ContentTransfer ct = Factory.ContentTransfer.createInstance();
						ct.setCaptureSource(isStream);
						ct.set_ContentType("image/jpeg");
						ct.set_RetrievalName(docName);
						ContentElementList cl = Factory.ContentElement.createList();
						cl.add(ct);
						doc.set_ContentElements(cl);
						doc.getProperties().putValue("FirstName", "Priya");
						doc.getProperties().putValue("LastName", "Kuila");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date dob = sdf.parse("1999-11-11 11:00:00");
						doc.getProperties().putValue("DOB", dob);
						doc.getProperties().putValue("Gender", "Female");
						doc.getProperties().putValue("Address", "WB");
						doc.set_MimeType("image/jpeg");
						doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
						doc.save(RefreshMode.REFRESH);
						ReferentialContainmentRelationship rcr = secondFolder.file(doc, AutoUniqueName.AUTO_UNIQUE,
								"Sample_Upload_jpg", DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
						rcr.save(RefreshMode.REFRESH);

						System.out.println("Document uploaded successfully: " + doc.get_Id());

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

}
