package com.murphy.dft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.murphy.downtime.util.DowntimeServicesUtil;

/**
 * @author INC00718
 *
 */
public class DBConnector2 {

	private static DBConnector2 dbConnector;

	private DBConnector2() {

	}

	public static synchronized DBConnector2 getInstance() {
		if (dbConnector == null) {
			dbConnector = new DBConnector2();
		}
		return dbConnector;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		DowntimeServicesUtil.unSetupSOCKS();
		try {
//			Class.forName(AppConstants.JDBC_MYSQL_DRIVER);
//			con = DriverManager.getConnection(AppConstants.JDBC_MYSQL_URL+"?user="+AppConstants.JDBC_MYSQL_USER+"&password="+AppConstants.JDBC_MYSQL_USER+AppConstants.JDBC_URL_PARAMS);
			
//			Class.forName(AppConstants.JDBC_HANA_DRIVER);
//			con = DriverManager.getConnection(AppConstants.JDBC_HANA_URL, AppConstants.JDBC_HANA_USER, AppConstants.JDBC_HANA_USER);
			
			Class.forName(AppConstants.JDBC_ORACLE_DRIVER);
			con = DriverManager.getConnection(AppConstants.JDBC_ORACLE_URL, AppConstants.JDBC_ORACLE_USER, AppConstants.JDBC_ORACLE_PASS);
			
		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
		}
		return con;
	}

	public PreparedStatement insert(String insertQuery) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DBConnector2.getInstance().getConnection();
			preparedStatement = con.prepareStatement(insertQuery);
		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
		} finally {
//			con.close();
		}
		return preparedStatement;
	}

	public ResultSet query(String query) throws ClassNotFoundException, SQLException {
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = DBConnector2.getInstance().getConnection();
			resultSet = con.createStatement().executeQuery(query);
//			con.close();
			return resultSet;
		} catch (Exception e) {
			System.err.println("Exception : "+e.getMessage());
//			con.close();
			return resultSet;
		}
	}

}
