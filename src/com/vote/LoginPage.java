package com.vote;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends Frame implements ActionListener {
    TextField usernameField, passwordField;
    Button loginButton, registerButton;

    void VotingAppLogin() {
        // Frame properties
        setTitle("Voting App Login");
        setSize(400, 400);
        setLayout(new GridBagLayout()); // Use GridBagLayout to center components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; // Center the components in the grid

        // Set background color for the Frame
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Heading Label
        Label headingLabel = new Label("Voter's Login", Label.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        headingLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(headingLabel, gbc);

        // Username Label and TextField
        Label usernameLabel = new Label("Username:");
        usernameLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset to single column
        add(usernameLabel, gbc);

        usernameField = new TextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        // Password Label and TextField
        Label passwordLabel = new Label("Password:");
        passwordLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Login Button
        loginButton = new Button("Login");
        loginButton.setBackground(new Color(70, 130, 180)); // Steel Blue background
        loginButton.setForeground(Color.WHITE); // White text for the button
        loginButton.setPreferredSize(new Dimension(130, 30)); // Width = 150, Height = 50
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // anchor at center
        gbc.fill = GridBagConstraints.NONE;     // do not resize the button
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);
        
        // Register Button
        registerButton = new Button("Register");
        registerButton.setBackground(new Color(70, 130, 180)); // Steel Blue background
        registerButton.setForeground(Color.WHITE); // White text for the button
        registerButton.setPreferredSize(new Dimension(130, 30)); // Width = 150, Height = 50
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // anchor at center
        gbc.fill = GridBagConstraints.NONE;     // do not resize the button
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Login logic
                homePage homeObj = new homePage();
                
                // method created in database operation class
                //{
                // values passed to the method
                //}
                // call the method and pass the values
                // usernameField.getText();
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
