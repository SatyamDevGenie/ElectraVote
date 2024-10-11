package com.vote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CandidateDashboard extends JFrame {

    // Database connection object
    private Connection con;

    // Constructor for Candidate Dashboard
    public CandidateDashboard(String candidateName, Connection connection) {
        this.con = connection;
        setTitle("Candidate Dashboard - " + candidateName);
        setSize(600, 400); // Set window size
        setLayout(new BorderLayout(10, 10)); // Set layout with padding

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Check All Records Here");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(e -> logout(candidateName));
        menu.add(logoutMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Create a panel for the main dashboard content with padding
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around all sides

        JLabel welcomeLabel = new JLabel("Welcome, " + candidateName, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create a panel for buttons with BoxLayout (vertical alignment)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding inside button panel

        // Create buttons and style them
        JButton viewProfileButton = createStyledButton("View Profile", new Color(66, 134, 244), Color.WHITE);
        JButton updateProfileButton = createStyledButton("Update Profile", new Color(244, 144, 12), Color.WHITE);
        JButton viewResultsButton = createStyledButton("View Election Results", new Color(72, 201, 176), Color.WHITE);
        JButton totalVoteCountButton = createStyledButton("Total Vote Count", new Color(234, 84, 85), Color.WHITE);
        JButton logoutButton = createStyledButton("Logout", new Color(108, 92, 231), Color.WHITE);

        // Set margins and sizes for buttons
        Dimension buttonSize = new Dimension(200, 40);
        viewProfileButton.setMaximumSize(buttonSize);
        updateProfileButton.setMaximumSize(buttonSize);
        viewResultsButton.setMaximumSize(buttonSize);
        totalVoteCountButton.setMaximumSize(buttonSize);
        logoutButton.setMaximumSize(buttonSize);

        // Add buttons to panel with spacing
        buttonPanel.add(Box.createVerticalStrut(15)); // Adds space before the first button
        buttonPanel.add(viewProfileButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Adds space between buttons
        buttonPanel.add(updateProfileButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(viewResultsButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(totalVoteCountButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(logoutButton);
        buttonPanel.add(Box.createVerticalStrut(15)); // Adds space after the last button

        // Add the button panel to the left side
        add(buttonPanel, BorderLayout.WEST);

        // Add content panel in the center
        add(contentPanel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        viewProfileButton.addActionListener(e -> showMessage("Viewing profile for " + candidateName));
        updateProfileButton.addActionListener(e -> showMessage("Updating profile for " + candidateName));
        viewResultsButton.addActionListener(e -> showMessage("Viewing election results for " + candidateName));
        totalVoteCountButton.addActionListener(e -> {
            int totalVotes = getTotalVotes(candidateName);
            if (totalVotes != -1) {
                showMessage("Total votes for " + candidateName + ": " + totalVotes);
            } else {
                showMessage("Error fetching vote count for " + candidateName);
            }
        });
        logoutButton.addActionListener(e -> logout(candidateName));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame when the window is closed
        setVisible(true); // Make the frame visible
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false); // Removes focus border
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding inside button
        return button;
    }

    // Method to fetch total votes from the database
    private int getTotalVotes(String candidateName) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalVotes = -1;

        try {
            String sql = "SELECT COUNT(*) AS totalVotes FROM Votes WHERE candidateName = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, candidateName);
            rs = pstmt.executeQuery();

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

        return totalVotes;
    }

    // Method to display a message dialog
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to handle logout action
    private void logout(String candidateName) {
        showMessage("Logging out " + candidateName);
        dispose(); // Close the dashboard
    }

    // Main method for testing
    public static void main(String[] args) {
        // Simulate passing a database connection (replace with your actual DB connection)
        Connection con = null; // Replace with actual DB connection
        new CandidateDashboard("John Doe", con); // Example candidate name and DB connection
    }
}















//package com.vote;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class CandidateDashboard extends Frame {
//
//    // Database connection object (you should initialize it properly)
//    private Connection con;
//
//    // Constructor for Candidate Dashboard
//    public CandidateDashboard(String candidateName, Connection connection) {
//        this.con = connection; // Set the database connection
//        setTitle("Candidate Dashboard - " + candidateName);
//        setSize(400, 350); // Set the window size
//        setLayout(new GridLayout(5, 1)); // GridLayout with 5 rows and 1 column
//
//        // Create buttons for the dashboard
//        Button viewProfileButton = new Button("View Profile");
//        Button updateProfileButton = new Button("Update Profile");
//        Button viewResultsButton = new Button("View Election Results");
//        Button totalVoteCountButton = new Button("Total Vote Count"); // New button for vote count
//        Button logoutButton = new Button("Logout");
//
//        // Add buttons to the Frame
//        add(viewProfileButton);
//        add(updateProfileButton);
//        add(viewResultsButton);
//        add(totalVoteCountButton); // Add the new button for total vote count
//        add(logoutButton);
//
//        // Add action listeners to the buttons
//        viewProfileButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                showMessage("Viewing profile for " + candidateName);
//            }
//        });
//
//        updateProfileButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                showMessage("Updating profile for " + candidateName);
//            }
//        });
//
//        viewResultsButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                showMessage("Viewing election results for " + candidateName);
//            }
//        });
//
//        // Action listener for the total vote count button
//        totalVoteCountButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Call the method to fetch total votes from the database
//                int totalVotes = getTotalVotes(candidateName);
//                if (totalVotes != -1) {
//                    showMessage("Total votes for " + candidateName + ": " + totalVotes);
//                } else {
//                    showMessage("Error fetching vote count for " + candidateName);
//                }
//            }
//        });
//
//        logoutButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                showMessage("Logging out " + candidateName);
//                dispose(); // Close the dashboard
//            }
//        });
//
//        // Handle the window close event
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent we) {
//                dispose(); // Close the window
//            }
//        });
//
//        setVisible(true); // Make the Frame visible
//    }
//
//    // Method to fetch total votes from the database
//    private int getTotalVotes(String candidateName) {
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        int totalVotes = -1; // Initialize with -1 to indicate an error if not set
//
//        try {
//            // SQL query to count total votes for the candidate
//            String sql = "SELECT COUNT(*) AS totalVotes FROM Votes WHERE candidateName = ?";
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, candidateName);
//            rs = pstmt.executeQuery();
//
//            // Get the total votes from the result set
//            if (rs.next()) {
//                totalVotes = rs.getInt("totalVotes");
//            }
//
//        } catch (SQLException ex) {
//            System.out.println("Error fetching total votes: " + ex);
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (pstmt != null) pstmt.close();
//            } catch (SQLException e) {
//                System.out.println("Error closing resources: " + e);
//            }
//        }
//
//        return totalVotes; // Return the total vote count (or -1 if an error occurred)
//    }
//
//    // Method to display a message dialog
//    private void showMessage(String message) {
//        Dialog d = new Dialog(this, "Info", true);
//        d.setLayout(new FlowLayout());
//        Label l = new Label(message);
//        Button b = new Button("OK");
//        b.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                d.setVisible(false);
//            }
//        });
//        d.add(l);
//        d.add(b);
//        d.setSize(250, 100);
//        d.setVisible(true);
//    }
//
//    // Main method to launch the dashboard (for testing purposes)
//    public static void main(String[] args) {
//        // Simulate passing a database connection (you need to replace this with your actual DB connection)
//        Connection con = null; // Replace with actual database connection
//        new CandidateDashboard("John Doe", con); // Example candidate name and DB connection
//    }
//}
