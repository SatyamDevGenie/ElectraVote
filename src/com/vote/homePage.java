package com.vote;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class homePage extends Frame implements ActionListener {
    TextField usernameField, passwordField;
    Button loginButton;
    
    void VotingAppLogin() {
        // Frame properties
        setTitle("Home Page");
        setSize(300, 150);
        setLayout(new FlowLayout());
        
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                // Close the window and exit the program
                System.exit(0);
            }
        });
        setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
