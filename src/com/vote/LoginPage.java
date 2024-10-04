package com.vote;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends Frame {
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
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// Clear placeholder when focus is gained
				if (usernameField.getText().equals("Enter your Candidate ID here...")) {
					usernameField.setText("");
					usernameField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Reset placeholder if the field is empty when focus is lost
				if (usernameField.getText().isEmpty()) {
					usernameField.setText("Enter your Candidate ID here...");
					usernameField.setForeground(Color.GRAY);
				}
			}
		});
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
		gbc.fill = GridBagConstraints.NONE; // do not resize the button
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
		gbc.fill = GridBagConstraints.NONE; // do not resize the button
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(registerButton, gbc);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Login logic

				DatabaseOperation.loadJDBCDriverAndCreateConnection();
				if (DatabaseOperation.loginCandidate(usernameField.getText(), passwordField.getText())) {
					homePage homeObj = new homePage();
					dispose();
				} else {

					Dialog dialog = new Dialog(LoginPage.this, "Candidate ID", true);
					dialog.setSize(400, 200); // Adjust the size for better visibility
					dialog.setLayout(new BorderLayout()); // Use BorderLayout for better control over placement

					// Create a panel for the label and center it with padding at the top
					Panel messagePanel = new Panel(new GridBagLayout());
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.insets = new Insets(20, 0, 0, 0); // Add 20px padding to the top

					Label lblMessage = new Label("Your Username and Password is incorrect.");
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
//							LoginPage obj = new LoginPage();
//							obj.VotingAppLogin();
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

			}
		});

		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				registerPage regObj = new registerPage();
				regObj.VotingAppRegister();
				
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

}