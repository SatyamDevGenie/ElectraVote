package com.vote;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class homePage extends Frame implements ActionListener {
    Label lblTitle, lblCandidateList;
    CheckboxGroup candidateGroup;
    Button submitButton;

    public homePage() {
        // Frame properties
        setTitle("Voting Dashboard");
        setSize(600, 630);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set background color
        setBackground(new Color(240, 240, 240)); // Light grey background

        // Add a title label with styling
        lblTitle = new Label("ElectraVote Platform");
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 30));
        lblTitle.setForeground(new Color(50, 50, 50));
        lblTitle.setAlignment(Label.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // Candidate List Label with enhanced styling
        lblCandidateList = new Label("Please Select Your Candidate :-");
        lblCandidateList.setFont(new Font("Helvetica", Font.BOLD, 15));
        lblCandidateList.setForeground(new Color(35, 35, 35));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblCandidateList, gbc);

        // Create a Panel for Checkboxes with GridLayout for organized layout
        Panel checkboxPanel = new Panel();
        checkboxPanel.setLayout(new GridLayout(0, 1, 10, 10)); // 1 column with 10 pixels spacing

        // Create a CheckboxGroup for single selection
        candidateGroup = new CheckboxGroup();

        try {
            DatabaseOperation.loadJDBCDriverAndCreateConnection();
            ResultSet rs = DatabaseOperation.getCandidateNames();
            while (rs.next()) {
                String candidateName = rs.getString("name");

                // Create and style candidate checkboxes with a card-like appearance
                Checkbox candidateCheckbox = new Checkbox(candidateName, candidateGroup, false);
                styleCandidateCheckbox(candidateCheckbox);
                checkboxPanel.add(candidateCheckbox);
            }
        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
        }

        // Add checkbox panel to the main frame
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(checkboxPanel, gbc);

        // Submit Button with custom styling
        submitButton = new Button("Submit Vote");
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        submitButton.setBackground(new Color(0, 123, 255)); // Blue background
        submitButton.setForeground(Color.WHITE); // White text
        submitButton.setPreferredSize(new Dimension(160, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(this);
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

    // Method to style candidate checkbox with a card-like appearance
    private void styleCandidateCheckbox(Checkbox checkbox) {
        checkbox.setFont(new Font("Helvetica", Font.PLAIN, 14));
        checkbox.setForeground(new Color(60, 60, 60));
        checkbox.setBackground(new Color(255, 255, 255)); // White background for checkbox
        checkbox.setPreferredSize(new Dimension(300, 50)); // Set a preferred size for each checkbox
        checkbox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        checkbox.addItemListener(e -> {
            // Change background color on hover
            if (e.getStateChange() == ItemEvent.SELECTED) {
                checkbox.setBackground(new Color(200, 220, 255)); // Light blue on selection
            } else {
                checkbox.setBackground(new Color(255, 255, 255)); // White background when not selected
            }
        });
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
