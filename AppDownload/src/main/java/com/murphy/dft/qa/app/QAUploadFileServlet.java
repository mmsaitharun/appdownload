package com.murphy.dft.qa.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author INC00718
 *
 */
public class QAUploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAUploadFileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		appendForm(response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		String fileName = "";
//		String fileVersion = "";
		String fileType = "";
//		String downloadUrl = "";
//		String inFileName = "";
		
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
//						case ("fileVersion") : 
//							fileVersion = fieldValue; break;
						case ("fileType") : 
							fileType = fieldValue; break;
//						case ("downloadUrl") :
//							downloadUrl = fieldValue; break;
					}
				} else {
//					String fieldName = item.getFieldName();
//					inFileName = FilenameUtils.getBaseName(item.getName());
//					fileName = inFileName;
					fileContent = item.getInputStream();
					if((item.getInputStream() == null) && (item.getSize() <= 0)) {
						fileContent = null;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		/*if(fileType.equalsIgnoreCase("apk")) {
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
		}*/
		
		/*if(fileType.equalsIgnoreCase("apk")){
			downloadUrl = "https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/download?fileType=APK";
		} else if(fileType.equalsIgnoreCase("ipa")) {
			downloadUrl = "https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/download?fileType=IPA";
		} else if(fileType.equalsIgnoreCase("plist")) {
			downloadUrl = "https://appdownloaddee8964f1.us2.hana.ondemand.com/AppDownload/download?fileType=PLIST";
		}*/
		
		try {
			Class.forName("com.sap.db.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO APP_FILE_QA(ID, FILE_NAME, FILE_TYPE, FILE) VALUES(?, ?, ?, ?)");
			pstmt.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
			pstmt.setString(2, fileName);
//			pstmt.setString(3,fileVersion);
			pstmt.setString(3, fileType.toUpperCase());
			pstmt.setBlob(4, fileContent);
//			pstmt.setString(6, downloadUrl);
			System.out.println(pstmt.executeUpdate());
			System.out.println("Success inserting");
			
			response.getWriter().write("Inserting File Successful! FileName : " +fileName +" File Type : "+fileType);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void appendForm(HttpServletResponse response) throws IOException{
		response.getWriter().println("<h3>App Upload Area</h3><br><br><p><form action=\"\" method=\"post\"enctype=\"multipart/form-data\">"
				+ "Upload File : <input type=\"file\" name=\"file\" style=\"margin-left: 4em;\"/><br><br>"
				+ "File Name : <input type=\"text\" name=\"fileName\" style=\"margin-left: 4.5em;\"><br><br>"
//				+ "File Version : <input type=\"text\" name=\"fileVersion\" style=\"margin-left: 3.8em;\"><br><br>"
//				+ "File Type : <select name = apps style=\"margin-left: 5em; padding-right: 5em;\"><option value=\"apk\">APK</option><option value=\"ipa\">IPA</option><option value=\"plist\">PLIST</option></select><br><br><br>"
				+ "File Type : <input type=\"text\" name=\"fileType\" style=\"margin-left: 4.5em;\"><br><br>"
//				+ "Download URL: <input type = \"text\" name=\"downloadUrl\" style=\"margin-left: 2.2em;\"><br><br>"
				+ "<input type=\"submit\" value=\"Upload File\" /></form></p>");
	}

}
