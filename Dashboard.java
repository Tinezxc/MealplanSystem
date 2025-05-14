package com.mycompany.dashboard;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public Dashboard(String email) {
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

        // Main background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800); 
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Title
        JLabel dashboardTitle = new JLabel("DASHBOARD");
        dashboardTitle.setBackground(Color.WHITE);
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 28));
        dashboardTitle.setBounds(150, 70, 300, 50);
        mainPanel.add(dashboardTitle);

        //Search Field and Buttons
        JTextField searchField = new JTextField();
        searchField.setBounds(630, 70, 400, 40);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(1040, 70, 100, 40);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBackground(Color.WHITE);
        userButton.setBounds(1150, 70, 100, 40);
        mainPanel.add(userButton);

        // Categories
        JLabel categoriesLabel = new JLabel("CATEGORIES");
        categoriesLabel.setFont(new Font("Arial", Font.BOLD, 22));
        categoriesLabel.setBounds(150, 150, 250, 40);
        mainPanel.add(categoriesLabel);

        int xPos = 150;
        for (int i = 0; i < 4; i++) {
            JPanel categoryBox = new JPanel();
            categoryBox.setBounds(xPos, 200, 180, 120);
            categoryBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mainPanel.add(categoryBox);
            xPos += 220;
        }

        //for notif
        JLabel notificationLabel = new JLabel("NOTIFICATION");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 22));
        notificationLabel.setBounds(150, 360, 250, 40);
        mainPanel.add(notificationLabel);

        int yPos = 410;
        for (int i = 0; i < 2; i++) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setBounds(150, yPos, 1100, 120);
            notificationPanel.setBackground(Color.WHITE);
            notificationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            notificationPanel.setLayout(new BorderLayout());

            JLabel notificationTitle = new JLabel("Notification");
            notificationTitle.setFont(new Font("Arial", Font.BOLD, 16));
            JTextArea notificationText = new JTextArea(i == 0 ? "It's time for your lunch baby!" : "notif.");
            notificationText.setFont(new Font("Arial", Font.PLAIN, 14));
            notificationText.setLineWrap(true);
            notificationText.setWrapStyleWord(true);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            JButton readButton = new JButton("READ");
            readButton.setBackground(Color.WHITE);
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setBackground(Color.WHITE);
            buttonPanel.add(readButton);
            buttonPanel.add(deleteButton);

            notificationPanel.add(notificationTitle, BorderLayout.NORTH);
            notificationPanel.add(notificationText, BorderLayout.CENTER);
            notificationPanel.add(buttonPanel, BorderLayout.SOUTH);

            mainPanel.add(notificationPanel);
            yPos += 140;
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard("demo@example.com");
    }
}
