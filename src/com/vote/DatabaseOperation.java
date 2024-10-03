package com.vote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperation {
	
	static PreparedStatement pstmt;
	static Connection con;
	static Statement stmt;
	static ResultSet rs;

	public static void loadJDBCDriverAndCreateConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=ElectraVote;user=sa;password=123;encrypt=true;trustServerCertificate=true");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// insert data in table 
	//method name ( values )

}
