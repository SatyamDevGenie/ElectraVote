package com.vote;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends Frame implements ActionListener {
    TextField usernameField, passwordField;
    Button loginButton;

    void VotingAppLogin() {
        // Frame properties
        setTitle("Voting App Login");
        setSize(400, 250);
        setLayout(new GridBagLayout()); // Use GridBagLayout to center components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; // Center the components in the grid

        // Set background color for the Frame
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Username Label and TextField
        Label usernameLabel = new Label("Username:");
        usernameLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        add(usernameLabel, gbc);

        usernameField = new TextField(20);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        add(usernameField, gbc);

        // Password Label and TextField
        Label passwordLabel = new Label("Password:");
        passwordLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        gbc.gridx = 1; // Column 1
        gbc.gridy = 1; // Row 1
        add(passwordField, gbc);

        // Login Button
        loginButton = new Button("Login");
        loginButton.setBackground(new Color(70, 130, 180)); // Steel Blue background
        loginButton.setForeground(Color.WHITE); // White text for the button
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Login logic
                homePage homeObj = new homePage();
            }
        });

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                // Close the window and exit the program
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle login logic here
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.VotingAppLogin();
    }
}
