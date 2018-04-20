package com.murphy.dft.util;
//package com.murphy.dft.app;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author INC00718
// *
// */
//@WebServlet("/VersionServlet")
//public class VersionServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    public VersionServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.90:1521:xe", "DBUSER", "DBUSER");
//			
//			Statement stmt = con.createStatement();
//			Map<String, String> versionMap = new HashMap<String, String>();
//			ResultSet versionResult = stmt.executeQuery("SELECT FILE_TYPE AS FILE_TYPE, MAX(FILE_VERSION) AS VERSION FROM APP_FILE GROUP BY(FILE_TYPE)");
//			
//			while(versionResult.next()) {
//				versionMap.put(versionResult.getString("FILE_TYPE"), versionResult.getString("VERSION"));
//			}
//			
//			PrintWriter pw = response.getWriter();
//			
//			String tableStyle = "style=\"border-collapse: collapse;\"";
//			String rowStyle = "style=\"border: 1px solid black; padding: 10px; text-align: left;\"";
//			
//			pw.println("<br><br><table "+tableStyle+">");
//			pw.println("<tr><th "+rowStyle+">Application</th>");
//			pw.println("<th "+rowStyle+">Version</th></tr>");
//			for(Map.Entry<String, String> entry : versionMap.entrySet()) {
//				pw.println("<tr><td "+rowStyle+">"+entry.getKey()+"</td>");
//				pw.println("<td "+rowStyle+">"+entry.getValue()+"</td></tr>");
//			}
//			
//		} catch (Exception e) {
//			
//		}
//	}
//}
