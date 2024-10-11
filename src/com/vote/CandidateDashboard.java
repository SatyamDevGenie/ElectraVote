package com.vote;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CandidateDashboard extends Frame {

    // Database connection object (you should initialize it properly)
    private Connection con;

    // Constructor for Candidate Dashboard
    public CandidateDashboard(String candidateName, Connection connection) {
        this.con = connection; // Set the database connection
        setTitle("Candidate Dashboard - " + candidateName);
        setSize(400, 350); // Set the window size
        setLayout(new GridLayout(5, 1)); // GridLayout with 5 rows and 1 column

        // Create buttons for the dashboard
        Button viewProfileButton = new Button("View Profile");
        Button updateProfileButton = new Button("Update Profile");
        Button viewResultsButton = new Button("View Election Results");
        Button totalVoteCountButton = new Button("Total Vote Count"); // New button for vote count
        Button logoutButton = new Button("Logout");

        // Add buttons to the Frame
        add(viewProfileButton);
        add(updateProfileButton);
        add(viewResultsButton);
        add(totalVoteCountButton); // Add the new button for total vote count
        add(logoutButton);

        // Add action listeners to the buttons
        viewProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage("Viewing profile for " + candidateName);
            }
        });

        updateProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage("Updating profile for " + candidateName);
            }
        });

        viewResultsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage("Viewing election results for " + candidateName);
            }
        });

        // Action listener for the total vote count button
        totalVoteCountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the method to fetch total votes from the database
                int totalVotes = getTotalVotes(candidateName);
                if (totalVotes != -1) {
                    showMessage("Total votes for " + candidateName + ": " + totalVotes);
                } else {
                    showMessage("Error fetching vote count for " + candidateName);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMessage("Logging out " + candidateName);
                dispose(); // Close the dashboard
            }
        });

        // Handle the window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window
            }
        });

        setVisible(true); // Make the Frame visible
    }

    // Method to fetch total votes from the database
    private int getTotalVotes(String candidateName) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalVotes = -1; // Initialize with -1 to indicate an error if not set

        try {
            // SQL query to count total votes for the candidate
            String sql = "SELECT COUNT(*) AS totalVotes FROM Votes WHERE candidateName = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, candidateName);
            rs = pstmt.executeQuery();

            // Get the total votes from the result set
            if (rs.next()) {
                totalVotes = rs.getInt("totalVotes");
            }

        } catch (SQLException ex) {
            System.out.println("Error fetching total votes: " + ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }

        return totalVotes; // Return the total vote count (or -1 if an error occurred)
    }

    // Method to display a message dialog
    private void showMessage(String message) {
        Dialog d = new Dialog(this, "Info", true);
        d.setLayout(new FlowLayout());
        Label l = new Label(message);
        Button b = new Button("OK");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });
        d.add(l);
        d.add(b);
        d.setSize(250, 100);
        d.setVisible(true);
    }

    // Main method to launch the dashboard (for testing purposes)
    public static void main(String[] args) {
        // Simulate passing a database connection (you need to replace this with your actual DB connection)
        Connection con = null; // Replace with actual database connection
        new CandidateDashboard("John Doe", con); // Example candidate name and DB connection
    }
}
