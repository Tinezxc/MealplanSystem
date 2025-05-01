package com.mycompany.notification;

import javax.swing.*;
import java.awt.*;

public class Notification {
    public Notification(String email) {
        //frame
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800); 
        frame.setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setBounds(0, 0, 150, 800); 
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);
        
        
        leftPanel.add(Box.createVerticalStrut(150));

        // Navigation Buttons
        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(120, 200));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.BLACK);
            navButton.setBackground(Color.GRAY);
            navButton.setBorderPainted(false);
            navButton.setFont(new Font("Arial", Font.PLAIN, 10));
            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(90));
        }

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(120, 0, 1430, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // backbutton
        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(50, 60, 70, 30);
        mainPanel.add(backButton);

        // Title
        JLabel dashboardTitle = new JLabel("DASHBOARD");
        dashboardTitle.setBackground(Color.WHITE);
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 28));
        dashboardTitle.setBounds(130, 50, 300, 50);
        mainPanel.add(dashboardTitle);

        //Search Field and Buttons
        JTextField searchField = new JTextField();
        searchField.setBounds(760, 50, 400, 30);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(1170, 50, 100, 30);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBackground(Color.WHITE);
        userButton.setBounds(1280, 50, 100, 30);
        mainPanel.add(userButton);

        // Notification Area
        JTextArea notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        notificationArea.setFont(new Font("Arial", Font.PLAIN, 18));
        notificationArea.setText(
                "- Meal Plan updated for next week\n" +
                "- Progress Report is ready to view\n" +
                "- System maintenance on April 18\n" +
                "- Notification bell sound test\n"
        );

        JScrollPane scrollPane = new JScrollPane(notificationArea);
        scrollPane.setBounds(50, 120, 1330, 500);
        mainPanel.add(scrollPane);

        // Clear and Refresh Buttons
        JButton clearButton = new JButton("Clear All");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(Color.WHITE);
        clearButton.setBounds(50, 650, 130, 30);
        mainPanel.add(clearButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setBackground(Color.WHITE);
        refreshButton.setBounds(220, 650, 130, 30);
        mainPanel.add(refreshButton);

        frame.setVisible(true);
    }
    
            public static void main(String[] args) {
        new Notification("demo@example.com");
    }
}
