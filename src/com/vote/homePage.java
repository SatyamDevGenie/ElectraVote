package com.vote;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class homePage extends Frame implements ActionListener {
    Label lblCandidateList;
    CheckboxGroup candidateGroup;
    Checkbox choice1, choice2, choice3;
    Button submitButton;

    public homePage() {
        // Frame properties
        setTitle("Voting Application");
        setSize(450, 450);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set background color
        setBackground(new Color(245, 245, 245));

        // Candidate List Label with enhanced styling
        lblCandidateList = new Label("Please Select Your Candidate:");
        lblCandidateList.setFont(new Font("Helvetica", Font.BOLD, 18)); // Modern font
        lblCandidateList.setForeground(new Color(35, 35, 35)); // Darker shade for text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblCandidateList, gbc);

        // Create a CheckboxGroup for single selection
        candidateGroup = new CheckboxGroup();

        // Candidate Checkboxes with enhanced styling
        choice1 = new Checkbox(" Candidate 1 - John Doe", candidateGroup, false);
        styleCheckbox(choice1, gbc, 1);
        
        choice2 = new Checkbox(" Candidate 2 - Jane Smith", candidateGroup, false);
        styleCheckbox(choice2, gbc, 2);
        
        choice3 = new Checkbox(" Candidate 3 - Alex Johnson", candidateGroup, false);
        styleCheckbox(choice3, gbc, 3);

        // Submit Button with custom styling and hover effect
        submitButton = new Button("Submit Vote");
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        submitButton.setBackground(new Color(0, 123, 255)); // Blue background
        submitButton.setForeground(Color.WHITE); // White text
        submitButton.setPreferredSize(new Dimension(160, 40)); // Button size
        submitButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(submitButton, gbc);

        // Center the frame on the screen
        centerFrame();

        // Window listener for closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Method to style checkbox with consistent format
    private void styleCheckbox(Checkbox checkbox, GridBagConstraints gbc, int row) {
        checkbox.setFont(new Font("Helvetica", Font.PLAIN, 14));
        checkbox.setForeground(new Color(60, 60, 60));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(checkbox, gbc);
    }

    // Method to center the frame
    private void centerFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click action
        if (e.getSource() == submitButton) {
            Checkbox selectedCandidate = candidateGroup.getSelectedCheckbox();
            if (selectedCandidate != null) {
                System.out.println("You voted for: " + selectedCandidate.getLabel());
            } else {
                System.out.println("No candidate selected.");
            }
        }
    }

    public static void main(String[] args) {
        new homePage(); // Instantiate the HomePage
    }
}
