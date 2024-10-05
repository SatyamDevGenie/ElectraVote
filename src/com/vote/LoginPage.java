package com.vote;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends Frame {
    TextField usernameField, passwordField;
    Button loginButton, registerButton;

    void VotingAppLogin() {
        // Frame properties
        setTitle("User Login");
        setSize(600, 630);
        setLayout(new GridBagLayout());
        
     // Center the frame on the screen
        setLocationRelativeTo(null);  // This centers the frame
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Gradient Background Color
        setBackground(new Color(236, 240, 241)); // Light gray background for the frame

        // Panel for form elements (Username and Password fields)
        Panel formPanel = new Panel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(600, 500));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBounds(30, 40, 280, 200);
        
        // Add subtle shadow effect using a raised bevel border
        formPanel.setForeground(Color.BLACK);
        formPanel.setPreferredSize(new Dimension(320, 200));

        // Heading Label
        Label headingLabel = new Label("Voter's Login", Label.CENTER);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 25)); // Modern font
        headingLabel.setForeground(new Color(52, 73, 94)); // Darker font color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(headingLabel, gbc);

        // Add padding to form elements
        gbc.insets = new Insets(15, 15, 15, 15);

     // Username Label and TextField
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameLabel.setForeground(new Color(44, 62, 80)); // Dark font color
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        gbc.gridwidth = 1; // Reset to 1 column wide
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        formPanel.add(usernameLabel, gbc);

        usernameField = new TextField(20); // Set 20 columns for width
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setForeground(new Color(127, 140, 141)); // Gray font for placeholder
        usernameField.setText("Enter your ID here...");
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Enter your ID here")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Enter your ID here...");
                    usernameField.setForeground(new Color(127, 140, 141)); // Gray placeholder text
                }
            }
        });
        gbc.gridx = 1; // Second column, next to the label
        gbc.gridy = 0; // First row, same as label
        formPanel.add(usernameField, gbc);

        // Password Label and TextField
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(44, 62, 80)); // Dark font color
        gbc.gridx = 0; // First column
        gbc.gridy = 1; // Second row
        formPanel.add(passwordLabel, gbc);

        passwordField = new TextField(20); // Set 20 columns for width
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; // Second column, next to the label
        gbc.gridy = 1; // Second row, same as label
        formPanel.add(passwordField, gbc);

        // Add formPanel to the main frame
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(formPanel, gbc);

        // Login Button
        loginButton = new Button("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Use a modern bold font
        loginButton.setBackground(new Color(46, 204, 113)); // Green
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(150, 40)); // Make buttons wider
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFocusable(false); // Disable focus on buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across 2 columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally
        gbc.fill= GridBagConstraints.NONE;
        add(loginButton, gbc);
        
     // Register Button
        registerButton = new Button("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(new Color(52, 152, 219)); // Light Blue
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(150, 40)); // Make buttons wider
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setFocusable(false); // Disable focus on buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across 2 columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally
        gbc.fill= GridBagConstraints.NONE;
        add(registerButton, gbc);

        // Add Action Listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperation.loadJDBCDriverAndCreateConnection();
                if (DatabaseOperation.loginCandidate(usernameField.getText(), passwordField.getText())) {
                    homePage homeObj = new homePage();
                    dispose();
                } else {
                    showErrorDialog("Your Username and Password is incorrect.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                registerPage regObj = new registerPage();
                regObj.VotingAppRegister();
            }
        });

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Helper method to show an error dialog
    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog(LoginPage.this, "Error", true);
        dialog.setSize(350, 150);
        dialog.setLayout(new BorderLayout());

        Panel messagePanel = new Panel(new GridBagLayout());
        Label lblMessage = new Label(message);
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messagePanel.add(lblMessage);
        dialog.add(messagePanel, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button btnOK = new Button("OK");
        btnOK.setPreferredSize(new Dimension(80, 30));
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        buttonPanel.add(btnOK);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}






