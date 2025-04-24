package com.mycompany.dashboard;


import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public Dashboard(String email, String username) {

        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 90, 565);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(40));
        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(110, 50));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.BLACK);
            navButton.setBackground(Color.GREEN);
            navButton.setBorderPainted(false);
            navButton.setFont(new Font("Arial", Font.PLAIN, 7));
            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(40));
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 705, 563);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(25, 40, 50, 30);
        mainPanel.add(backButton);

        JLabel dashboardTitle = new JLabel("DASHBOARD");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 16));
        dashboardTitle.setBounds(80, 10, 150, 90);
        mainPanel.add(dashboardTitle);

        JTextField searchField = new JTextField();
        searchField.setBounds(250, 41, 250, 30);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(510, 41, 80, 30);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBackground(Color.WHITE);
        userButton.setBounds(600, 41, 80, 30);
        mainPanel.add(userButton);

        JLabel categoriesLabel = new JLabel("CATEGORIES");
        categoriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        categoriesLabel.setBounds(80, 120, 150, 30);
        mainPanel.add(categoriesLabel);

        int xPos = 80;
        for (int i = 0; i < 4; i++) {
            JPanel categoryBox = new JPanel();
            categoryBox.setBounds(xPos, 150, 100, 80);
            categoryBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel categoryLabel = new JLabel();
            categoryBox.add(categoryLabel);

            mainPanel.add(categoryBox);
            xPos += 120;
        }

        JLabel notificationLabel = new JLabel("NOTIFICATION");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        notificationLabel.setBounds(80, 250, 190, 30);
        mainPanel.add(notificationLabel);

        int yPos = 280;
        for (int i = 0; i < 2; i++) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setBackground(Color.WHITE); 
            notificationPanel.setBounds(80, yPos, 550, 80);
            notificationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            notificationPanel.setLayout(new BorderLayout());

            JLabel notificationTitle = new JLabel("Notification");
            JTextArea notificationText = new JTextArea("Notification content...");
            notificationText.setBackground(Color.WHITE);
            

            JPanel buttonPanel = new JPanel();
            JButton readButton = new JButton("READ");
            readButton.setBackground(Color.WHITE);
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setBackground(Color.WHITE);
            buttonPanel.setBackground(Color.WHITE); 
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

    public static void main(String[] args) {
        new Dashboard("demo@example.com", "demoUser");
    }
}
