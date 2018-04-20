package com.murphy.dft.qa.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.app.util.RestUtil;
import com.murphy.dft.util.AppUtils;
import com.murphy.downtime.util.DowntimeServicesUtil;

/**
 * @author INC00718
 *
 */
@Path("/qa/app")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class QADownloadRest {

	@GET
	@Path("/download")
	public String getDownloadData(@QueryParam("fileType") String fileType) {
		fileType = fileType.toUpperCase();
		/*DownloadResponse response = new DownloadResponse();
		if(fileType.equalsIgnoreCase("IPA")){
//			response.setUrl("https://i.diawi.com/GhYmJP");
			response.setUrl(this.getURL(fileType));
		} else if(fileType.equalsIgnoreCase("APK")){
//			response.setUrl("https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/download?fileType="+fileType);
			response.setUrl(this.getURL(fileType));
		} else if(fileType.equalsIgnoreCase("PLIST")) {
			response.setUrl(this.getURL(fileType));
		}
		response.setVersion(this.getDownloadVersion(fileType));
		return response;*/
		
		String response = "";
		if(!AppUtils.isEmpty(fileType)) {
			response = RestUtil.callRest("https://a65dee8964f1.us2.hana.ondemand.com/WORKBOX/GetFromAPP_FILE_QA.xsjs?fileType='"+fileType+"'");
		}
		return response;
	}
	
	@SuppressWarnings("unused")
	private String getDownloadVersion(String fileType) {
		DowntimeServicesUtil.unSetupSOCKS();
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
			Class.forName("com.sap.db.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
//			Connection con = DriverManager.getConnection("jdbc:sap://localhost:30115/WORKBOX", "WORKBOX", "Incture1234567891013");
			Statement stmt = con.createStatement();
			Map<String, String> versionMap = new HashMap<String, String>();
			ResultSet versionResult = stmt.executeQuery("SELECT FILE_TYPE AS FILE_TYPE, MAX(FILE_VERSION) AS VERSION FROM APP_FILE_QA GROUP BY(FILE_TYPE)");
			while(versionResult.next()) {
				versionMap.put(versionResult.getString("FILE_TYPE"), versionResult.getString("VERSION"));
			}
			return (versionMap.get(fileType));
		} catch(Exception e) { 
			System.err.println("Error : "+e.getMessage());
			return "";
		}
	}
	
	@SuppressWarnings("unused")
	private String getURL(String fileType) {
		String fileUrl = "";
		DowntimeServicesUtil.unSetupSOCKS();
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
			Class.forName("com.sap.db.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
//			Connection con = DriverManager.getConnection("jdbc:sap://localhost:30115/WORKBOX", "WORKBOX", "Incture1234567891013");
			Statement stmt = con.createStatement();
//			Map<String, String> versionMap = new HashMap<String, String>();
			ResultSet versionResult = stmt.executeQuery("SELECT FILE_URL AS FILE_URL, MAX(FILE_VERSION) AS VERSION FROM APP_FILE_QA WHERE FILE_TYPE = '"+fileType.toUpperCase()+"' GROUP BY(FILE_URL)");
			while(versionResult.next()) {
//				versionMap.put(versionResult.getString("FILE_URL"), versionResult.getString("VERSION"));
				fileUrl =  versionResult.getString("FILE_URL");
			}
//			return (versionMap.get(fileType));
		} catch(Exception e) { 
			System.err.println("Error : "+e.getMessage());
		}
		return fileUrl;
	}
}
