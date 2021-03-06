package com.murphy.dft.qa.app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.murphy.downtime.util.DowntimeServicesUtil;

/**
 * @author INC00718
 *
 */
public class QAAppDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAAppDownloadServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		if(fileType != null) {
			fileType = fileType.toUpperCase();
//			downloadFile(fileType, response);
			DowntimeServicesUtil.unSetupSOCKS();
			
			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
				Class.forName("com.sap.db.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
//				Connection con = DriverManager.getConnection("jdbc:sap://localhost:30115/WORKBOX", "WORKBOX", "Incture1234567891013");
				Statement stmt = con.createStatement();
				Map<String, String> versionMap = new HashMap<String, String>();
				ResultSet versionResult = stmt.executeQuery("SELECT FILE_TYPE AS FILE_TYPE, MAX(FILE_VERSION) AS VERSION FROM APP_FILE_QA GROUP BY(FILE_TYPE)");
				while(versionResult.next()) {
					versionMap.put(versionResult.getString("FILE_TYPE"), versionResult.getString("VERSION"));
				}
				System.err.println("versionMap : "+versionMap);
				String query = "SELECT ID, FILE_NAME, FILE_TYPE, FILE FROM APP_FILE_QA WHERE FILE_TYPE LIKE '%"+fileType+"%' AND FILE_VERSION LIKE '%"+versionMap.get(fileType)+"%'";
				System.err.println("query : "+query);
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					System.err.println("FILE_NAME : "+rs.getString("FILE_NAME"));
					String fileName = rs.getString("FILE_NAME");
					String fileTy = rs.getString("FILE_TYPE");
					System.err.println("FILE_TYPE : "+rs.getString("FILE_TYPE"));
					File file = null;
					InputStream is = (InputStream) (rs.getBlob("FILE").getBinaryStream());
					if(fileTy.equalsIgnoreCase("APK")){
						file = File.createTempFile("export-", ".apk", new File("."));
					} else if(fileTy.equalsIgnoreCase("IPA")){
						file = File.createTempFile("export-", ".ipa", new File("."));
					} else if (fileTy.equalsIgnoreCase("PLIST")) {
						file = File.createTempFile("export-", ".plist", new File("."));
					}
					OutputStream out = new FileOutputStream(file);
					byte[] buff = new byte[8192];
					int len = 0;
					while ((len = is.read(buff)) != -1) {
					    out.write(buff, 0, len);
					}
					out.close();
					if(fileTy.equalsIgnoreCase("APK")){
						response.setContentType("application/apk");
					} else if(fileTy.equalsIgnoreCase("IPA")){
						response.setContentType("application/ipa");
					} else if(fileTy.equalsIgnoreCase("PLIST")) {
//						response.setContentType("application/plist");
						response.setContentType("text/xml");
					}
					if(fileType.equalsIgnoreCase("PLIST")) {
						
						System.err.println("Into Else Loop");
//						String result = IOUtils.toString(is, StandardCharsets.UTF_8);
						int ch;
						StringBuilder sb = new StringBuilder();
						while((ch = is.read()) != -1)
						    sb.append((char)ch);
						
						System.err.println("Result Text : "+sb.toString());
						response.getWriter().write(sb.toString());
						
					} else {
						if(fileTy != null) {
							response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "_" +versionMap.get(fileType) +"."+ fileTy.toLowerCase() + "\"");
						}
//						response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "_" +versionMap.get(fileType) +"."+ fileTy.toLowerCase() + "\"");
						OutputStream os = response.getOutputStream();
						FileInputStream fis = new FileInputStream(file);
						BufferedInputStream bufferInStream = new BufferedInputStream(fis);
						int cnt;
						while ((cnt = bufferInStream.read()) != -1) {
						    os.write(cnt);
						}
						bufferInStream.close();
				        response.flushBuffer();
					}
			        System.err.println("File downloaded successfully");
				}
				
			} catch(Exception e) { 
				response.getWriter().write("Error : "+e.getMessage());
			}
		}
	}
}
