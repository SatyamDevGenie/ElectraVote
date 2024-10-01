package com.vote;
import java.awt.*;
import java.awt.event.*;


public class frontPage extends Frame implements ActionListener {
	    TextField usernameField, passwordField;
	    Button loginButton;

	   void VotingAppLogin() {
	        // Frame properties
	        setTitle("Voting App Login");
	        setSize(300, 150);
	        setLayout(new FlowLayout());

	        // Username Label and TextField
	        Label usernameLabel = new Label("Username:");
	        usernameField = new TextField(20);

	        // Password Label and TextField
	        Label passwordLabel = new Label("Password:");
	        passwordField = new TextField(20);
	        passwordField.setEchoChar('*');

	        // Login Button
	        loginButton = new Button("Login");
	        loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					homePage homeObj = new homePage();
//					homeObj.VotingAppLogin();
					
				}
			});
	        
	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent we) {
	                // Close the window and exit the program
	                System.exit(0);
	            }
	        });
	        

	        // Adding components to the frame
	        add(usernameLabel);
	        add(usernameField);
	        add(passwordLabel);
	        add(passwordField);
	        add(loginButton);
	        
	        setVisible(true);
	        
	    }

	    public void actionPerformed(ActionEvent e) {
	        // Handle login logic
	    }

	    
	

}
