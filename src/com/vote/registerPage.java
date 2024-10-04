package com.vote;

import java.awt.*;
import java.awt.event.*;
import java.util.UUID;
import java.lang.*;

public class registerPage extends Frame implements ActionListener {
    TextField nameField, ageField, passwordField;
    Choice genderChoice;
    Button registerButton, backButton;

    void VotingAppRegister() {
        // Frame properties
        setTitle("Voting App Registration");
        setSize(400, 500);
        setLayout(new GridBagLayout()); // Use GridBagLayout to center components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; // Center the components in the grid

        // Set background color for the Frame
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Heading Label
        Label headingLabel = new Label("User Registration", Label.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        headingLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(headingLabel, gbc);

        // Name Label and TextField
        Label nameLabel = new Label("Name:");
        nameLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset to single column
        add(nameLabel, gbc);

        nameField = new TextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameField, gbc);

        // Gender Label and Choice
        Label genderLabel = new Label("Gender:");
        genderLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(genderLabel, gbc);

        genderChoice = new Choice();
        genderChoice.add("Male");
        genderChoice.add("Female");
        genderChoice.add("Other");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(genderChoice, gbc);

        // Age Label and TextField
        Label ageLabel = new Label("Age:");
        ageLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ageLabel, gbc);

        ageField = new TextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(ageField, gbc);

        // Password Label and TextField
        Label passwordLabel = new Label("Password:");
        passwordLabel.setForeground(new Color(40, 40, 40)); // Dark font color
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*'); // Mask password input
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(passwordField, gbc);

        // Register Button
        registerButton = new Button("Register");
        registerButton.setBackground(new Color(70, 130, 180)); // Steel Blue background
        registerButton.setForeground(Color.WHITE); // White text for the button
        registerButton.setPreferredSize(new Dimension(130, 30)); // Button size
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.fill = GridBagConstraints.NONE;     // Do not resize the button
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(registerButton, gbc);

        // Back Button
        backButton = new Button("Back");
        backButton.setBackground(new Color(70, 130, 180)); // Steel Blue background
        backButton.setForeground(Color.WHITE); // White text for the button
        backButton.setPreferredSize(new Dimension(130, 30)); // Button size
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(backButton, gbc);

        // Action listeners for buttons
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
                String name = nameField.getText();
                String gender = genderChoice.getSelectedItem();
                int age = Integer.parseInt(ageField.getText());
                String password = passwordField.getText();
                int length = 10;                
                String candidateID =  generateUUIDWithLength(10).toUpperCase();
                System.out.println("UUID String: " + candidateID);
                
                DatabaseOperation.loadJDBCDriverAndCreateConnection();
                DatabaseOperation.insertData(candidateID, name, age, gender, password);

                // Registration logic goes here (e.g., validation, database insertion)
                System.out.println("User Registered: " + name + ", Gender: " + gender + ", Age: " + age);
                
                dispose();
                
                Dialog dialog = new Dialog(registerPage.this, "Candidate ID", true);
                dialog.setSize(400, 200); // Adjust the size for better visibility
                dialog.setLayout(new BorderLayout()); // Use BorderLayout for better control over placement

                // Create a panel for the label and center it with padding at the top
                Panel messagePanel = new Panel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(20, 0, 0, 0); // Add 20px padding to the top

                Label lblMessage = new Label("Candidate User ID is: " + candidateID);
                lblMessage.setFont(new Font("Arial", Font.PLAIN, 14)); // Use a custom font for better appearance
                messagePanel.add(lblMessage, gbc);

                // Add some padding around the panel
                messagePanel.setPreferredSize(new Dimension(350, 100)); 

                // Add a panel for the button and center it
                Panel buttonPanel = new Panel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                Button btnOK = new Button("OK");
                btnOK.setPreferredSize(new Dimension(80, 30)); // Adjust button size for better visibility
                btnOK.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false); // Hide the dialog on OK button click

                        // Opens Login Page
                        LoginPage obj = new LoginPage();
                        obj.VotingAppLogin();
                    }
                });
                buttonPanel.add(btnOK);

                // Add panels to the dialog
                dialog.add(messagePanel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.SOUTH);

                // Set dialog to be modal and centered on the screen
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);


                
                
                
                
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to go back to the login page
                LoginPage loginPage = new LoginPage();
                loginPage.VotingAppLogin();
                setVisible(false); // Hide the current registration page
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
    public static String generateUUIDWithLength(int length) {
        // Generate a UUID and remove hyphens
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // Return the substring of the desired length
        return uuid.length() > length ? uuid.substring(0, length) : uuid;
    }

    public static void main(String[] args) {
        registerPage registrationPage = new  registerPage();
        registrationPage.VotingAppRegister();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions here if needed
    }
}
