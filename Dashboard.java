package com.mycompany.dashboard;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public static void main(String[] args) {

        // Main frame setup
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(null);

        // Left-side color panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 80, 500);
        leftPanel.setLayout(new GridLayout(5, 3));
        frame.add(leftPanel);
        
        String[] navigatorTitles = {"DASHBOARD", "MEAL PLAN", "PROGRESS", "SCHEDULE", "NOTIFICATION"};
        for (String title : navigatorTitles) {
            JButton navigatorButton = new JButton(title);
            navigatorButton.setBackground(Color.GREEN);
            navigatorButton.setBorderPainted(false); // Remove border for a cleaner look
            navigatorButton.setFocusPainted(false); // Remove focus border
            navigatorButton.setFont(new Font("Arial", Font.PLAIN, 7)); // Set font to Arial size 10
            leftPanel.add(navigatorButton);
        }

        // Main background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 720, 500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Back button
        JButton backButton = new JButton("<");
        backButton.setBounds(10, 10, 43, 25);
        mainPanel.add(backButton);

        // Dashboard Title
        JLabel dashboardTitle = new JLabel("DASHBOARD");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardTitle.setBounds(70, 10, 150, 30);
        mainPanel.add(dashboardTitle);

        // Search Bar & User Button
        JTextField searchField = new JTextField();
        searchField.setBounds(250, 10, 250, 30);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(510, 10, 80, 30);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("USER");
        userButton.setBounds(600, 10, 80, 30);
        mainPanel.add(userButton);

        // Categories Section
        JLabel categoriesLabel = new JLabel("CATEGORIES");
        categoriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        categoriesLabel.setBounds(70, 60, 150, 30);
        mainPanel.add(categoriesLabel);


        int xPos = 70;
        for (int i = 0; i < 4; i++) {
            JPanel categoryBox = new JPanel();
                 categoryBox.setBounds(xPos, 100, 100, 80);
                 categoryBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    if (i == 0) {
        // First box: white background, green "+"
        categoryBox.setBackground(Color.WHITE);
        JLabel plusSign = new JLabel("+", SwingConstants.CENTER);
        plusSign.setForeground(Color.GREEN);
        categoryBox.setLayout(new BorderLayout()); // Center the "+"
        categoryBox.add(plusSign, BorderLayout.CENTER);
    } else {
        // Other boxes: green background
        categoryBox.setBackground(Color.GREEN);
    }

    mainPanel.add(categoryBox);
    xPos += 120;
}

        // Notification Section
        JLabel notificationLabel = new JLabel("NOTIFICATION");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        notificationLabel.setBounds(70, 200, 150, 30);
        mainPanel.add(notificationLabel);

        // Notifications
        int yPos = 240;
        for (int i = 0; i < 2; i++) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setBounds(70, yPos, 550, 80);
            notificationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            notificationPanel.setLayout(new BorderLayout());

            JLabel notificationTitle = new JLabel("Notification");
            JTextArea notificationText = new JTextArea(i == 0 ? "It's time for your lunch baby!" : "notif.");

            JPanel buttonPanel = new JPanel();
            JButton readButton = new JButton("READ");
            JButton deleteButton = new JButton("DELETE");
            buttonPanel.add(readButton);
            buttonPanel.add(deleteButton);

            notificationPanel.add(notificationTitle, BorderLayout.NORTH);
            notificationPanel.add(notificationText, BorderLayout.CENTER);
            notificationPanel.add(buttonPanel, BorderLayout.SOUTH);

            mainPanel.add(notificationPanel);
            yPos += 100;
        }

        frame.setVisible(true);
    }
}