package com.murphy.dft.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author INC00718
 *
 */
public class AppUtils {
	
	// isEmpty  --  start
	
	public static boolean isEmpty(Object[] objs) {
		if (objs == null || objs.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Collection<?> o) {
		if (o == null || o.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(StringBuffer sb) {
		if (sb == null || sb.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(StringBuilder sb) {
		if (sb == null || sb.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Element nd) {
		if (nd == null) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(NamedNodeMap nd) {
		if (nd == null || nd.getLength() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Node nd) {
		if (nd == null ) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(NodeList nd) {
		if (nd == null || nd.getLength() == 0) {
			return true;
		}
		return false;
	}
	
	// isEmpty  --  end

	public static Map<String, String> getVersionMap() {
		Map<String, String> versionMap = new HashMap<String, String>();
		ResultSet versionResult = null;
		try {
			versionResult = DBConnector.getInstance().query(
					"SELECT FILE_TYPE AS FILE_TYPE, MAX(FILE_VERSION) AS VERSION FROM APP_FILE GROUP BY(FILE_TYPE)");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLException : " + e.getMessage());
		}
		try {
			while (versionResult.next()) {
				versionMap.put(versionResult.getString("FILE_TYPE"), versionResult.getString("VERSION"));
			}
		} catch (SQLException e) {
			System.err.println("SQLException : " + e.getMessage());
		}
		return versionMap;
	}
	
	public static String getFileUrl(String fileType) {
		ResultSet resultSet = null;
		String fileUrl = "";
		if(!AppUtils.isEmpty(fileType)) {
			try {
				resultSet = DBConnector.getInstance().query("SELECT FILE_URL AS FILE_URL, MAX(FILE_VERSION) AS VERSION FROM APP_FILE WHERE FILE_TYPE = '"+fileType.toUpperCase()+"' GROUP BY(FILE_URL)");
				while(resultSet.next()) {
					fileUrl = resultSet.getString("FILE_URL");
				}
			} catch (Exception e) {
				System.err.println("Exception : "+e.getMessage());
			}
		}
		return fileUrl;
	}
	
	public static String getFileUrl2(String fileType) {
		ResultSet resultSet = null;
		String fileUrl = "";
		if(!AppUtils.isEmpty(fileType)) {
			try {
				resultSet = DBConnector2.getInstance().query("Select 'my dummy string' as DUMMY FROM DUAL");
				while(resultSet.next()) {
					fileUrl = resultSet.getString("DUMMY");
				}
			} catch (Exception e) {
				System.err.println("Exception : "+e.getMessage());
			}
		}
		return fileUrl;
	}
	
	public static FileDto getFileDetail(FileDto requestDto) throws SQLException {
		requestDto.setFileType(requestDto.getFileType().toUpperCase());
		FileDto fileDto = null;
		ResultSet resultSet = null;
		try {
			fileDto = new FileDto();
			resultSet = DBConnector.getInstance().query("SELECT ID, FILE_NAME, FILE_VERSION, FILE_TYPE, FILE FROM APP_FILE WHERE FILE_TYPE LIKE '%"+requestDto.getFileType()+"%' AND FILE_VERSION LIKE '%"+AppUtils.getVersionMap().get(requestDto.getFileType())+"%'");
			while(resultSet.next()) {
				fileDto.setFileName(resultSet.getString("FILE_NAME"));
				fileDto.setFileType(resultSet.getString("FILE_TYPE"));
				fileDto.setFileVersion(resultSet.getString("FILE_VERSION"));
				fileDto.setFile(resultSet.getBlob("FILE").getBinaryStream());
			}
		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
			e.printStackTrace();
		} finally {
			resultSet.close();
		}
		return fileDto;
	}
	
	public static FileDto getGenericFileDetail(FileDto requestDto) throws SQLException {
		requestDto.setFileType(requestDto.getFileType().toUpperCase());
		FileDto fileDto = null;
		ResultSet resultSet = null;
		try {
			fileDto = new FileDto();
			String query = "SELECT ID, FILE_NAME, FILE_TYPE, FILE FROM APP_FILE WHERE 1=1";
			if(!AppUtils.isEmpty(requestDto.getFileType())) {
				query = query + "AND FILE_TYPE LIKE '%"+requestDto.getFileType()+"%'";
			} if(!AppUtils.isEmpty(requestDto.getFileName())) {
				query = query + "AND FILE_NAME LIKE '%"+requestDto.getFileName()+"%'";
			} if(!AppUtils.isEmpty(requestDto.getFileVersion())) {
				query = query + "AND FILE_VERSION LIKE '%"+requestDto.getFileVersion()+"%'";
			}
			resultSet = DBConnector.getInstance().query(query);
			while(resultSet.next()) {
				fileDto.setFileName(resultSet.getString("FILE_NAME"));
				fileDto.setFileType(resultSet.getString("FILE_TYPE"));
				fileDto.setFileVersion(resultSet.getString("FILE_VERSION"));
				fileDto.setFile(resultSet.getBlob("FILE").getBinaryStream());
			}
		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
			e.printStackTrace();
		} finally {
			resultSet.close();
		}
		return fileDto;
	}
	
	public static int insertFileDetail(FileDto fileDto) {
		fileDto.setFileType(fileDto.getFileType().toUpperCase());
		try {
			PreparedStatement pstmt =  DBConnector.getInstance().insert("INSERT INTO APP_FILE(ID, FILE_NAME, FILE_VERSION, FILE_TYPE, FILE) VALUES(?, ?, ?, ?, ?)");
			pstmt.setString(1, fileDto.getId());
			pstmt.setString(2, fileDto.getFileName());
			pstmt.setString(3, fileDto.getFileVersion());
			pstmt.setString(4, fileDto.getFileType());
			pstmt.setBlob(5, fileDto.getFile());
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
	
	public static HttpServletResponse downloadFile(HttpServletResponse response, FileDto responseFileDto) throws IOException {
		
		File file = null;
		InputStream inputStream = null;
		String fileType = responseFileDto.getFileType();
		if(!AppUtils.isEmpty(responseFileDto.getFile())) {
			inputStream = responseFileDto.getFile();
		}
		if(!AppUtils.isEmpty(fileType)) {
			file = File.createTempFile("export-", "."+fileType.toLowerCase(), new File("."));
		}
		OutputStream out = new FileOutputStream(file);
		byte[] buff = new byte[8192];
		int len = 0;
		while ((len = inputStream.read(buff)) != -1) {
		    out.write(buff, 0, len);
		}
		out.close();
		if(fileType.equalsIgnoreCase(AppConstants.FILE_TYPE_PLIST)) {
			response.setContentType("text/xml");
		} else {
			response.setContentType("application/"+fileType+"");
		}
		if(fileType.equalsIgnoreCase(AppConstants.FILE_TYPE_PLIST)) {
			
			int ch;
			StringBuilder sb = new StringBuilder();
			while((ch = inputStream.read()) != -1)
			    sb.append((char)ch);
			response.getWriter().write(sb.toString());
		} else {
			Map<String, String> versionMap = AppUtils.getVersionMap();
			if(!AppUtils.isEmpty(fileType) && !AppUtils.isEmpty(responseFileDto.getFileName()) && !AppUtils.isEmpty(!AppUtils.isEmpty(versionMap))) {
				String fileVersion = versionMap.get(fileType);
				response.setHeader("Content-Disposition", "attachment;filename=\"" + responseFileDto.getFileName() + "_" + fileVersion +"."+ fileType.toLowerCase() + "\"");
			}
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
		
		return response;
	}
	
	public static void appendForm(HttpServletResponse response) throws IOException{
		response.getWriter().println("<h3>App Upload Area</h3><br><br><p><form action=\"\" method=\"post\"enctype=\"multipart/form-data\">"
				+ "Upload File : <input type=\"file\" name=\"file\" style=\"margin-left: 4em;\"/><br><br>"
				+ "File Name : <input type=\"text\" name=\"fileName\" style=\"margin-left: 4.5em;\"><br><br>"
				+ "File Version : <input type=\"text\" name=\"fileVersion\" style=\"margin-left: 3.8em;\"><br><br>"
				+ "File Type : <select name = apps style=\"margin-left: 5em; padding-right: 5em;\"><option value=\"apk\">APK</option><option value=\"ipa\">IPA</option><option value=\"plist\">PLIST</option></select><br><br><br>"
				+ "Upload Type: <select name = upload style=\"margin-left: 5em; padding-right: 5em;\"><option value=\"Update\">UPDATE</option><option value=\"create\">CREATE</option></select><br><br><br>"
				+ "Download URL: <input type = \"text\" name=\"downloadUrl\" style=\"margin-left: 2.2em;\"><br><br>"
				+ "<input type=\"submit\" value=\"Upload File\" /></form></p>");
	}
	
	public static void appendGenericForm(HttpServletResponse response) throws IOException{
		response.getWriter().println("<h3>App Upload Area</h3><br><br><p><form action=\"\" method=\"post\"enctype=\"multipart/form-data\">"
				+ "Upload File : <input type=\"file\" name=\"file\" style=\"margin-left: 4em;\"/><br><br>"
				+ "File Name : <input type=\"text\" name=\"fileName\" style=\"margin-left: 4.5em;\"><br><br>"
				+ "File Type : <input type=\"text\" name=\"fileType\" style=\"margin-left: 4.5em;\"><br><br>"
				+ "<input type=\"submit\" value=\"Upload File\" /></form></p>");
	}
	
	public static void addVersionTable(HttpServletResponse response) {
		Map<String, String> versionMap;
		try{
			versionMap = AppUtils.getVersionMap();
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
	
	public static String getServerUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.indexOf(contextPath));
		url += contextPath;
		return url;
	}
	
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		/*String filePath = "\\src\\main\\resources\\Textfile.zip";
		File file = new File(System.getProperty("user.dir")+filePath);
		FileInputStream fi = new FileInputStream(file);
		FileDto fileDto = new FileDto();
		fileDto.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		fileDto.setFileName("TestFile");
		fileDto.setFileType("zip");
		fileDto.setFileVersion("1.0");
		fileDto.setFile(fi);
		System.err.println(AppUtils.insertFileDetail(fileDto));*/
		
//		FileDto fileDto = new FileDto();
//		fileDto.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		fileDto.setFileName("TestFile");
//		fileDto.setFileType("zip");
//		fileDto.setFileVersion("1.0");
//		fileDto.setFile(fi);
		
//		System.out.println(AppUtils.getFileDetail(fileDto));

//		String url = "http://localhost:8307/AppDownload/tupload";
//		String contextPath = "/AppDownload";
//		
//		System.out.println(url.substring(0, url.indexOf(contextPath)));
//		System.out.println(url.split(contextPath)[0]);
		
	}
}
