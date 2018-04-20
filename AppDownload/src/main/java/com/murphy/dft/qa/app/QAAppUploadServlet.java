package com.murphy.dft.qa.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * @author INC00718
 *
 */
public class QAAppUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAAppUploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		addVersionTable(response);
		appendForm(response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		Part filePart = request.getPart("file");
//		InputStream fileIs = request.getPart("file").getInputStream();
//		String fileName = request.getParameter("fileName");
//		String fileVersion = request.getParameter("fileVersion");
//		String fileType = request.getParameter("apps");
//		String downloadURL = request.getParameter("downloadUrl");
		
		String fileName = "";
		String fileVersion = "";
		String fileType = "";
		String downloadUrl = "";
		String inFileName = "";
		
		InputStream fileContent = null;
		
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					switch(fieldName) {
						case ("fileName") : 
							fileName = fieldValue; break;
						case ("fileVersion") : 
							fileVersion = fieldValue; break;
						case ("apps") : 
							fileType = fieldValue; break;
						case ("downloadUrl") :
							downloadUrl = fieldValue; break;
					}
				} else {
//					String fieldName = item.getFieldName();
					inFileName = FilenameUtils.getBaseName(item.getName());
					fileName = inFileName;
					fileContent = item.getInputStream();
					if((item.getInputStream() == null) && (item.getSize() <= 0)) {
						fileContent = null;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		if(fileType.equalsIgnoreCase("apk")) {
//			int length;
			if((inFileName != null) && inFileName != ""){
//				length = inFileName.length();
//				fileName = inFileName.substring(0, length-4);
				fileName = inFileName;
			}
		} else if(fileType.equalsIgnoreCase("ipa")) {
			if((inFileName != null) && inFileName != ""){
//				length = inFileName.length();
//				fileName = inFileName.substring(0, length-4);
				fileName = inFileName;
			}
		} else if(fileType.equalsIgnoreCase("plist")) {
			if((inFileName != null) && inFileName != ""){
//				length = inFileName.length();
//				fileName = inFileName.substring(0, length-4);
				fileName = inFileName;
			}
		}
		
		if(fileType.equalsIgnoreCase("apk")){
			downloadUrl = "https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/qa/download?fileType=APK";
		} else if(fileType.equalsIgnoreCase("ipa")) {
//			downloadUrl = "https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/download?fileType=IPA";
		} else if(fileType.equalsIgnoreCase("plist")) {
			downloadUrl = "https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/qa/download?fileType=PLIST";
		}
		
		try {
			Class.forName("com.sap.db.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO APP_FILE_QA(ID, FILE_NAME, FILE_VERSION, FILE_TYPE, FILE, FILE_URL) VALUES(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
			pstmt.setString(2, fileName);
			pstmt.setString(3,fileVersion);
			pstmt.setString(4, fileType.toUpperCase());
			pstmt.setBlob(5, fileContent);
			pstmt.setString(6, downloadUrl);
			System.out.println(pstmt.executeUpdate());
			System.out.println("Success inserting");
			
			response.getWriter().write("Inserting Application Successful! FileName : " +fileName +" File Type : "+fileType +" File Version : "+fileVersion);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addVersionTable(HttpServletResponse response) {
		try{
			Class.forName("com.sap.db.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
			
			Statement stmt = con.createStatement();
			Map<String, String> versionMap = new HashMap<String, String>();
			ResultSet versionResult = stmt.executeQuery("SELECT FILE_TYPE AS FILE_TYPE, MAX(FILE_VERSION) AS VERSION FROM APP_FILE_QA GROUP BY(FILE_TYPE)");
			
			while(versionResult.next()) {
				versionMap.put(versionResult.getString("FILE_TYPE"), versionResult.getString("VERSION"));
			}
			
			response.getWriter().println("<div><br><br><table border = \"1\">");
			response.getWriter().println("<tr><th colspan = \"3\">Application</th>");
			response.getWriter().println("<th colspan = \"3\">Version</th></tr>");
			for(Map.Entry<String, String> entry : versionMap.entrySet()) {
				response.getWriter().println("<tr><td colspan = \"3\">"+entry.getKey()+"</td>");
				response.getWriter().println("<td colspan = \"3\">"+entry.getValue()+"</td></tr></div>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void appendForm(HttpServletResponse response) throws IOException{
		response.getWriter().println("<h3>App Upload Area</h3><br><br><p><form action=\"\" method=\"post\"enctype=\"multipart/form-data\">"
				+ "Upload File : <input type=\"file\" name=\"file\" style=\"margin-left: 4em;\"/><br><br>"
				+ "File Name : <input type=\"text\" name=\"fileName\" style=\"margin-left: 4.5em;\"><br><br>"
				+ "File Version : <input type=\"text\" name=\"fileVersion\" style=\"margin-left: 3.8em;\"><br><br>"
				+ "File Type : <select name = apps style=\"margin-left: 5em; padding-right: 5em;\"><option value=\"apk\">APK</option><option value=\"ipa\">IPA</option><option value=\"plist\">PLIST</option></select><br><br><br>"
				+ "Download URL: <input type = \"text\" name=\"downloadUrl\" style=\"margin-left: 2.2em;\"><br><br>"
				+ "<input type=\"submit\" value=\"Upload File\" /></form></p>");
	}

}
