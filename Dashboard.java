package com.mycompany.dashboard;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // LEFT NAVIGATION PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(120, frame.getHeight()));

        String[] navigatorTitles = {"DASHBOARD", "MEAL PLAN", "PROGRESS", "SCHEDULE", "NOTIFICATION"};
        for (String title : navigatorTitles) {
            JButton button = new JButton(title);
            button.setBackground(Color.GREEN);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("Arial", Font.PLAIN, 10));
            leftPanel.add(button);
            leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        frame.add(leftPanel, BorderLayout.WEST);

        // MAIN CONTENT PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // TOP BAR
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(Color.WHITE);

        JButton backButton = new JButton("<");
        JLabel dashboardTitle = new JLabel("DASHBOARD");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton userButton = new JButton("USER");

        topBar.add(backButton);
        topBar.add(dashboardTitle);
        topBar.add(Box.createHorizontalStrut(20));
        topBar.add(searchField);
        topBar.add(searchButton);
        topBar.add(userButton);

        mainPanel.add(topBar, BorderLayout.NORTH);

        // CENTER PANEL FOR CONTENT
        JPanel centerContent = new JPanel();
        centerContent.setBackground(Color.WHITE);
        centerContent.setLayout(null); // you can replace with GridBagLayout for more responsive layout

        // CATEGORIES LABEL
        JLabel categoriesLabel = new JLabel("CATEGORIES");
        categoriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        categoriesLabel.setBounds(20, 10, 150, 30);
        centerContent.add(categoriesLabel);

        // CATEGORY BOXES
        int xPos = 20;
        for (int i = 0; i < 4; i++) {
            JPanel categoryBox = new JPanel();
            categoryBox.setBounds(xPos, 50, 100, 80);
            categoryBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (i == 0) {
                categoryBox.setBackground(Color.WHITE);
                JLabel plusSign = new JLabel("+", SwingConstants.CENTER);
                plusSign.setForeground(Color.GREEN);
                categoryBox.setLayout(new BorderLayout());
                categoryBox.add(plusSign, BorderLayout.CENTER);
            } else {
                categoryBox.setBackground(Color.GREEN);
            }
            centerContent.add(categoryBox);
            xPos += 120;
        }

        // NOTIFICATIONS LABEL
        JLabel notificationLabel = new JLabel("NOTIFICATION");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        notificationLabel.setBounds(20, 150, 150, 30);
        centerContent.add(notificationLabel);

        // NOTIFICATIONS
        int yPos = 190;
        for (int i = 0; i < 2; i++) {
            JPanel notifPanel = new JPanel();
            notifPanel.setLayout(new BorderLayout());
            notifPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            notifPanel.setBounds(20, yPos, 550, 80);

            JLabel notifTitle = new JLabel("Notification");
            JTextArea notifText = new JTextArea(i == 0 ? "It's time for your lunch Mommy!" : "notif.");
            notifText.setWrapStyleWord(true);
            notifText.setLineWrap(true);
            notifText.setEditable(false);

            JPanel notifButtons = new JPanel();
            notifButtons.add(new JButton("READ"));
            notifButtons.add(new JButton("DELETE"));

            notifPanel.add(notifTitle, BorderLayout.NORTH);
            notifPanel.add(notifText, BorderLayout.CENTER);
            notifPanel.add(notifButtons, BorderLayout.SOUTH);

            centerContent.add(notifPanel);
            yPos += 100;
        }

        mainPanel.add(centerContent, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
