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
			// Load the SQL Server JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Connection string for MSSQL
			String url = "jdbc:sqlserver://localhost:1433;databaseName=ElectraVote;user=sa;password=123;encrypt=true;trustServerCertificate=true;";

			// Establish the connection
			con = DriverManager.getConnection(url);
			System.out.println("Connection established successfully.");

		} catch (ClassNotFoundException e) {
			System.out.println("SQL Server JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error connecting to the database.");
			e.printStackTrace();
		}
	}

	// insert data in table
	// method name ( values )

	public static void insertData(String candidateID, String name, int age, String gender, String password) {

		try {
			// Assuming the table has columns: id (auto-incremented), voterName, voterAge,
			// voterAddress
			String sql = "INSERT INTO Candidates (candidateID, name, age, gender, password) VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);

			// Set values for the placeholders
			pstmt.setString(1, candidateID);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, password);

			// Execute the insert command
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Data inserted successfully!");
			}

		} catch (SQLException ex) {
			System.out.println("Error inserting data: " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println("Error closing statement: " + e);
			}
		}
	}

	// Login Method
	public static boolean loginCandidate(String Username, String Password) {
		try {
			pstmt = con.prepareStatement("Select password from Candidates where candidateID=?");
			pstmt.setString(1, Username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(Password)) {
					return true;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return false;
	}
	

}
