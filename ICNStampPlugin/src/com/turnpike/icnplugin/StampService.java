package com.turnpike.icnplugin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.filenet.api.constants.*;
import com.filenet.api.core.Factory;

public class StampService extends PluginService {
    @Override
    public String getId() { return "StampService"; }
    @Override
    public void execute(PluginServiceCallbacks callbacks, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getRemoteUser();
        String repositoryId = request.getParameter("repositoryId");
        String docIdsJson = request.getParameter("docIds");
        JSONArray docIds = JSONArray.parse(docIdsJson);
        for (Object obj : docIds) {
            String docId = (String) obj;
            try {
                com.filenet.api.core.ObjectStore os = callbacks.getObjectStore(repositoryId);
                com.filenet.api.core.Document doc = Factory.Document.fetchInstance(os, docId, null);
                doc.getProperties().putValue("StampedBy", userId);
                doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
                doc.save(RefreshMode.REFRESH);
            } catch (Exception e) { e.printStackTrace(); }
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject result = new JSONObject();
        result.put("status", "success");
        result.put("message", "Documents stamped by: " + userId);
        out.print(result.toString());
    }
}