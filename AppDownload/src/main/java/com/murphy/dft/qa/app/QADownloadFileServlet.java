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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author INC00718
 *
 */
public class QADownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QADownloadFileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		String fileF = request.getParameter("file");
		if((fileType != null) && (fileF != null)) {
			fileType = fileType.toUpperCase();
			
			try {
				Class.forName("com.sap.db.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:sap://vadbhh004fu.phx.od.sap.biz:30015/WORKBOX", "WORKBOX", "Incture1234567891013");
				
				Statement stmt = con.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT ID, FILE_NAME, FILE_TYPE, FILE FROM APP_FILE_QA WHERE FILE_TYPE LIKE '%"+fileType+"%' AND FILE_NAME LIKE '%"+fileF+"%'");
				
				while(resultSet.next()) {

					System.err.println("FILE_NAME : "+resultSet.getString("FILE_NAME"));
					String fileName = resultSet.getString("FILE_NAME");
					String fileTy = resultSet.getString("FILE_TYPE");
					System.err.println("FILE_TYPE : "+resultSet.getString("FILE_TYPE"));
					File file = null;
					InputStream is = (InputStream) (resultSet.getBlob("FILE").getBinaryStream());
					file = File.createTempFile("export-", "."+fileType.toLowerCase(), new File("."));
//					if(fileTy.equalsIgnoreCase("APK")){
//						file = File.createTempFile("export-", ".apk", new File("."));
//					} else if(fileTy.equalsIgnoreCase("IPA")){
//						file = File.createTempFile("export-", ".ipa", new File("."));
//					} else if (fileTy.equalsIgnoreCase("PLIST")) {
//						file = File.createTempFile("export-", ".plist", new File("."));
//					}
					OutputStream out = new FileOutputStream(file);
					byte[] buff = new byte[8192];
					int len = 0;
					while ((len = is.read(buff)) != -1) {
					    out.write(buff, 0, len);
					}
					out.close();
					response.setContentType("application/"+fileType.toLowerCase()+"");
//					if(fileTy.equalsIgnoreCase("APK")){
//						response.setContentType("application/apk");
//					} else if(fileTy.equalsIgnoreCase("IPA")){
//						response.setContentType("application/ipa");
//					} else if(fileTy.equalsIgnoreCase("PLIST")) {
//						response.setContentType("application/plist");
//					}
					response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName +"."+ fileTy + "\"");
					OutputStream os = response.getOutputStream();
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bufferInStream = new BufferedInputStream(fis);
					int cnt;
					while ((cnt = bufferInStream.read()) != -1) {
					    os.write(cnt);
					}
					bufferInStream.close();
			        response.flushBuffer();
			        System.err.println("File downloaded successfully");
				
				}
			} catch (Exception e) {
				response.getWriter().write("Exception : "+e.getMessage());
			}
		}
	}

}
