package com.vote;

import java.awt.*;
import java.awt.event.*;
import java.util.UUID;

public class registerPage extends Frame {
	TextField nameField, ageField, passwordField, aadharField;
	Choice genderChoice;
	Button registerButton, backButton;
	Checkbox candidateCheckbox, voterCheckbox;

	void VotingAppRegister() {
		// Frame properties
		setTitle("User Registration");
		setSize(600, 630); // Increased height to accommodate new elements
		setLayout(new GridBagLayout()); // Use GridBagLayout for better control

		// Center the frame on the screen
		setLocationRelativeTo(null); // This centers the frame

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15); // Increased padding for professional look
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// Set background color for the Frame
		setBackground(new Color(245, 245, 245)); // Subtle light gray background

		// Heading Label
		Label headingLabel = new Label("Create your Account", Label.CENTER);
		headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 25)); // Modern, professional font
		headingLabel.setForeground(new Color(52, 73, 94)); // Darker font color for better contrast
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(headingLabel, gbc);


		// Terms and conditions checkbox and Newsletter subscription checkbox in a Panel
		Panel checkboxPanel = new Panel(new GridBagLayout());
		GridBagConstraints checkGbc = new GridBagConstraints();
		checkGbc.insets = new Insets(0, 15, 0, 15); // Padding between checkboxes
		checkGbc.anchor = GridBagConstraints.CENTER;

		CheckboxGroup registerGrp = new CheckboxGroup();
		// Candidate Checkbox
		candidateCheckbox = new Checkbox("Candidate", registerGrp, false);
		candidateCheckbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		checkGbc.gridx = 0;
		checkGbc.gridy = 0;
		checkboxPanel.add(candidateCheckbox, checkGbc);

		// Voter Checkbox
		voterCheckbox = new Checkbox("Voter", registerGrp, false);
		voterCheckbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		checkGbc.gridx = 1;
		checkGbc.gridy = 0;
		checkboxPanel.add(voterCheckbox, checkGbc);

		// Add checkbox panel to main layout
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2; // Span across two columns for centering
		add(checkboxPanel, gbc);

		// Name Label and TextField
		Label nameLabel = new Label("Full Name:");
		nameLabel.setForeground(new Color(52, 73, 94));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		add(nameLabel, gbc);

		nameField = new TextField(20);
		nameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(nameField, gbc);

		// Gender Label and Choice
		Label genderLabel = new Label("Gender:");
		genderLabel.setForeground(new Color(52, 73, 94));
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(genderLabel, gbc);

		genderChoice = new Choice();
		genderChoice.add("Male");
		genderChoice.add("Female");
		genderChoice.add("Other");
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(genderChoice, gbc);

		// Age Label and TextField
		Label ageLabel = new Label("Age:");
		ageLabel.setForeground(new Color(52, 73, 94));
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(ageLabel, gbc);

		ageField = new TextField(20);
		ageField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(ageField, gbc);

		// Password Label and TextField
		Label passwordLabel = new Label("Password:");
		passwordLabel.setForeground(new Color(52, 73, 94));
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(passwordLabel, gbc);

		passwordField = new TextField(20);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		passwordField.setEchoChar('*'); // Mask password input
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(passwordField, gbc);

		// Aadhar Label and TextField
		Label aadharLabel = new Label("Aadhar Card No:");
		aadharLabel.setForeground(new Color(52, 73, 94));
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(aadharLabel, gbc);

		aadharField = new TextField(20);
		aadharField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		gbc.gridx = 1;
		gbc.gridy = 7;
		add(aadharField, gbc);

		// Register Button
		registerButton = new Button("Register");
		registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Modern font for buttons
		registerButton.setBackground(new Color(39, 174, 96)); // Green background for Register button
		registerButton.setForeground(Color.WHITE); // White text for contrast
		registerButton.setPreferredSize(new Dimension(150, 40)); // Consistent button size
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; // Center the button
		gbc.fill = GridBagConstraints.NONE;
		add(registerButton, gbc);

		// Back Button
		backButton = new Button("Back");
		backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backButton.setBackground(new Color(52, 152, 219)); // Light blue background for Back button
		backButton.setForeground(Color.WHITE);
		backButton.setPreferredSize(new Dimension(150, 40));
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(backButton, gbc);

		// Action listeners for buttons
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Checkbox selectedCandidate = registerGrp.getSelectedCheckbox().getLabel();
				if (registerGrp.getSelectedCheckbox().getLabel().equals("Voter")) {
					String name = nameField.getText();
					String gender = genderChoice.getSelectedItem();
					int age = Integer.parseInt(ageField.getText());
					String password = passwordField.getText();
					String voterID = generateUUIDWithLength(10).toUpperCase();
					int aadharNo = Integer.parseInt(aadharField.getText());

					DatabaseOperation.loadJDBCDriverAndCreateConnection();
					DatabaseOperation.registerVoters(voterID, name, age, gender, password, aadharNo);

					// Display dialog with Candidate ID
					showVoterIDDialog(voterID);
				} else if (registerGrp.getSelectedCheckbox().getLabel().equals("Candidate")) {
					String name = nameField.getText();
					String gender = genderChoice.getSelectedItem();
					int age = Integer.parseInt(ageField.getText());
					String password = passwordField.getText();
					String candidateID = generateUUIDWithLength(10).toUpperCase();
					int aadharNo = Integer.parseInt(aadharField.getText());

					DatabaseOperation.loadJDBCDriverAndCreateConnection();
					DatabaseOperation.registerCandidates(candidateID, name, age, gender, password, aadharNo);

					// Display dialog with Candidate ID
					showCandidateIDDialog(candidateID);
				} else {
					showErrorDialog("Please Select checkbox...");
				}
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Logic to go back to the login page
				LoginPage loginPage = new LoginPage();
				loginPage.VotingAppLogin();
				setVisible(false);
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

	private void showCandidateIDDialog(String candidateID) {
		Dialog dialog = new Dialog(this, "Voter ID", true);
		dialog.setSize(400, 200);
		dialog.setLayout(new BorderLayout());

		Label messageLabel = new Label("Your Candidate ID is: " + candidateID, Label.CENTER);
		messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		Button okButton = new Button("OK");
		okButton.setPreferredSize(new Dimension(80, 30));
		okButton.addActionListener(e -> dialog.setVisible(false));

		Panel buttonPanel = new Panel();
		buttonPanel.add(okButton);

		dialog.add(messageLabel, BorderLayout.CENTER);
		dialog.add(buttonPanel, BorderLayout.SOUTH);

		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	// Helper method to show dialog with Voter ID
	private void showVoterIDDialog(String voterID) {
		Dialog dialog = new Dialog(this, "Voter ID", true);
		dialog.setSize(400, 200);
		dialog.setLayout(new BorderLayout());

		Label messageLabel = new Label("Your Voter ID is: " + voterID, Label.CENTER);
		messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		Button okButton = new Button("OK");
		okButton.setPreferredSize(new Dimension(80, 30));
		okButton.addActionListener(e -> dialog.setVisible(false));

		Panel buttonPanel = new Panel();
		buttonPanel.add(okButton);

		dialog.add(messageLabel, BorderLayout.CENTER);
		dialog.add(buttonPanel, BorderLayout.SOUTH);

		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	private void showErrorDialog(String message) {
		Dialog dialog = new Dialog(registerPage.this, "Error", true);
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

	public static String generateUUIDWithLength(int length) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid.length() > length ? uuid.substring(0, length) : uuid;
	}

	public static void main(String[] args) {
		new registerPage().VotingAppRegister();
	}
}
