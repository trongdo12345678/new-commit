package database;

import java.sql.*;

public class ConnectToDB {
	private static Connection con = null;
	private static final String url = "jdbc:sqlserver://";
	private static final String serverName = "LAPTOP-35QMNA7J";
	private static final String portNumber = "1433";
	private static final String databaseName = "c2208i1Swing";
	private static final String user = "sa";
	private static final String password = "Hieu1309";
	
	//trả về chuổi kết nối
	
	public static String getConnectUrl() {
		return url + serverName + ":" + portNumber + "; databaseName=" + 
					databaseName +"; user=" + user + "; password=" + password;
	}
	public static Connection getConn() {
		try {
			con = DriverManager.getConnection(getConnectUrl());
		} catch (Exception e) {
			con= null;
			e.printStackTrace();
			
		}
		return con;
	}
	public static Connection getConnect() throws Exception {
		con = DriverManager.getConnection(getConnectUrl());
		return con;
	}
}
