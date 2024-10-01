package com.vote;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class homePage extends Frame implements ActionListener {
    Label lblCandidateList;
    CheckboxGroup candidateGroup; // Group for checkboxes
    Checkbox choice1, choice2, choice3;
    Button submitButton;

    public homePage() {
        // Frame properties
        setTitle("Voting Application");
        setSize(400, 250);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set background color
        setBackground(new Color(240, 240, 240));

        // Candidate List Label
        lblCandidateList = new Label("Candidate List:");
        lblCandidateList.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
        lblCandidateList.setForeground(new Color(50, 50, 50)); // Set text color
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.gridwidth = 2; // Span 2 columns
        add(lblCandidateList, gbc);

        // Create a CheckboxGroup for single selection
        candidateGroup = new CheckboxGroup();

        // Candidate Checkboxes with styling
        choice1 = new Checkbox("Candidate 1", candidateGroup, false);
        choice1.setFont(new Font("Arial", Font.PLAIN, 14));
        choice1.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 1; // Reset to 1 column
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        add(choice1, gbc);

        choice2 = new Checkbox("Candidate 2", candidateGroup, false);
        choice2.setFont(new Font("Arial", Font.PLAIN, 14));
        choice2.setForeground(new Color(70, 70, 70));
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        add(choice2, gbc);

        choice3 = new Checkbox("Candidate 3", candidateGroup, false);
        choice3.setFont(new Font("Arial", Font.PLAIN, 14));
        choice3.setForeground(new Color(70, 70, 70));
        gbc.gridx = 0; // Column 0
        gbc.gridy = 3; // Row 3
        add(choice3, gbc);

        // Submit Button with custom styling
        submitButton = new Button("Submit Your Vote");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(50, 150, 50)); // Green background
        submitButton.setForeground(Color.WHITE); // White text
        gbc.gridx = 0; // Column 0
        gbc.gridy = 4; // Row 4
        gbc.gridwidth = 2; // Span 2 columns
        add(submitButton, gbc);

        // Action listener for button
        submitButton.addActionListener(this);

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
