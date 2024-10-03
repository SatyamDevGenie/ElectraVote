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
	
	public static void insertData(String name, int age, int voterID, String gender, String password ) {
		
		 try {
	            // Assuming the table has columns: id (auto-incremented), voterName, voterAge, voterAddress
	            String sql = "INSERT INTO Voters (name, age, gender, password, voterID) VALUES (?, ?, ?, ?, >)";
	            pstmt = con.prepareStatement(sql);

	            // Set values for the placeholders
	            pstmt.setString(1, name);
	            pstmt.setInt(2, age);
	            pstmt.setString(3, password);
	            pstmt.setString(4, gender);
	            pstmt.setInt(5, voterID);

	            // Execute the insert command
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Data inserted successfully!");
	            }

	        } catch (SQLException ex) {
	            System.out.println("Error inserting data: " + ex);
	        } finally {
	            try {
	                if (pstmt != null) pstmt.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing statement: " + e);
	            }
	        }
	    }
		
	}





//package com.vote;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DatabaseOperation {
//	
//	static PreparedStatement pstmt;
//	static Connection con;
//	static Statement stmt;
//	static ResultSet rs;
//
//	public static void loadJDBCDriverAndCreateConnection() {
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			con = DriverManager.getConnection(
//					"jdbc:sqlserver://localhost:1433;databaseName=ElectraVote;user=sa;password=123;encrypt=true;trustServerCertificate=true");
//		} catch (ClassNotFoundException ex) {
//			System.out.println(ex);
//		} catch (SQLException ex) {
//			System.out.println(ex);
//		}
//	}
//	
//	// insert data in table 
//	//method name ( values )
//
//}
