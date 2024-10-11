package com.vote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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

	public static boolean registerVoters(String voterID, String name, int age, String gender, String password, int aadharCard) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isInserted = false;  // To track if data is inserted

        try {
            // Query to check if the aadharCard already exists in the Voters table
            String checkQuery = "SELECT aadharCard FROM Voters WHERE aadharCard = ?";
            pstmt = con.prepareStatement(checkQuery);
            pstmt.setInt(1, aadharCard);
            rs = pstmt.executeQuery();

            // If a record is found, the aadharCard already exists, so don't insert
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Aadhar card already exists. No new voter will be inserted.", "Duplicate Aadhar", JOptionPane.WARNING_MESSAGE);
                return false;  // Return false since no data was inserted
            } else {
                // Proceed with the insertion since the aadharCard does not exist
                String insertQuery = "INSERT INTO Voters (voterID, name, age, gender, password, aadharCard) VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertQuery);

                // Set values for the placeholders
                pstmt.setString(1, voterID);
                pstmt.setString(2, name);
                pstmt.setInt(3, age);
                pstmt.setString(4, gender);
                pstmt.setString(5, password);
                pstmt.setInt(6, aadharCard);

                // Execute the insert command
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    isInserted = true;  // Mark as true since data was inserted
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error during database operation: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isInserted;  // Return the result of insertion
    }


	// Registration for Candidates
	public static boolean registerCandidates(String candidateID, String name, int age, String gender, String password, int aadharCard) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isInserted = false;  // To track if data is inserted

        try {
            // Query to check if the aadharCard already exists in the Voters table
            String checkQuery = "SELECT aadharCard FROM Candidates WHERE aadharCard = ?";
            pstmt = con.prepareStatement(checkQuery);
            pstmt.setInt(1, aadharCard);
            rs = pstmt.executeQuery();

            // If a record is found, the aadharCard already exists, so don't insert
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Aadhar card already exists. No new voter will be inserted.", "Duplicate Aadhar", JOptionPane.WARNING_MESSAGE);
                return false;  // Return false since no data was inserted
            } else {
                // Proceed with the insertion since the aadharCard does not exist
                String insertQuery = "INSERT INTO Candidates (candidateID, name, age, gender, password, aadharCard) VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertQuery);

                // Set values for the placeholders
                pstmt.setString(1, candidateID);
                pstmt.setString(2, name);
                pstmt.setInt(3, age);
                pstmt.setString(4, gender);
                pstmt.setString(5, password);
                pstmt.setInt(6, aadharCard);

                // Execute the insert command
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    isInserted = true;  // Mark as true since data was inserted
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error during database operation: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isInserted;  // Return the result of insertion
    }

	// Login Method
	public static String loginCandidate(String Username, String Password) {
		 try {
		        // Query to check the password for the given candidateID (Username)
		        String sql = "SELECT name, password FROM Candidates WHERE candidateID = ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, Username);
		        rs = pstmt.executeQuery();

		        // Check if the result set has the candidate's details
		        if (rs.next()) {
		            // Validate the password
		            if (rs.getString("password").equals(Password)) {
		                String candidateName = rs.getString("name"); // Retrieve the candidate name
		                return candidateName; // Return the candidateName if login is successful
		            } else {
		                System.out.println("Invalid password.");
		            }
		        } else {
		            System.out.println("Candidate not found.");
		        }
		    } catch (SQLException ex) {
		        System.out.println("Error during login: " + ex);
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		            System.out.println("Error closing resources: " + e);
		        }
		    }
		    return null; // Return null if login failed

//		try {
//			pstmt = con.prepareStatement("Select password from Voters where voterID=?");
//			pstmt.setString(1, Username);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				if (rs.getString("password").equals(Password)) {
//					return true;
//				}
//			}
//
//		} catch (SQLException ex) {
//			System.out.println(ex);
//		}
//		return false;

	}

	public static ResultSet getCandidateNames() {
		try {
			pstmt = con.prepareStatement("Select name from Candidates");
			rs = pstmt.executeQuery();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return rs;
	}
}
