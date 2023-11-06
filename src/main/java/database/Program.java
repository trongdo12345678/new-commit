package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Program {

	public static void main(String[] args) {
		Connection con = null;
		try {
//			con = DriverManager.getConnection(
//					ConnectToDB.getConnectUrl()
//					);
			con = ConnectToDB.getConn();
			System.out.println("thanh cong");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
