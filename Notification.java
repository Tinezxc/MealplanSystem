
package com.mycompany.notification;

import javax.swing.*;
import java.awt.*;

public class Notification {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Notification Center");
        frame.setSize(800, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Left navigation panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 80, 500);
        leftPanel.setLayout(new GridLayout(5, 1));
        frame.add(leftPanel);

        String[] navigatorTitles = {"DASHBOARD", "MEAL PLAN", "PROGRESS", "SCHEDULE", "NOTIFICATION"};
        for (String title : navigatorTitles) {
            JButton navigatorButton = new JButton(title);
            navigatorButton.setBackground(Color.GREEN);
            navigatorButton.setBorderPainted(false);
            navigatorButton.setFocusPainted(false);
            navigatorButton.setFont(new Font("Arial", Font.PLAIN, 7));
            leftPanel.add(navigatorButton);
        }

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 720, 500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Back button
        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(15, 10, 43, 25);
        mainPanel.add(backButton);

        // Title label
        JLabel notificationTitle = new JLabel("NOTIFICATIONS");
        notificationTitle.setFont(new Font("Arial", Font.BOLD, 16));
        notificationTitle.setBounds(70, 10, 150, 30);
        mainPanel.add(notificationTitle);

        // Search field
        JTextField searchField = new JTextField(20);
        searchField.setBounds(327, 5, 200, 30);
        mainPanel.add(searchField);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(530, 5, 80, 30);
        mainPanel.add(searchButton);

        // User icon button
        JButton userButton = new JButton("👤");
        userButton.setBackground(Color.WHITE);
        userButton.setBounds(615, 5, 80, 30);
        mainPanel.add(userButton);

        // Notification display area
        JTextArea notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        notificationArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notificationArea.setText(
            "- Meal Plan updated for next week\n" +
            "- Progress Report is ready to view\n" +
            "- System maintenance on April 18\n" +
            "- Notification bell sound test\n"
        );

        JScrollPane scrollPane = new JScrollPane(notificationArea);
        scrollPane.setBounds(20, 100, 660, 300);
        mainPanel.add(scrollPane);

        // Control buttons
        JButton clearButton = new JButton("Clear All");
        clearButton.setBounds(20, 420, 100, 30);
        mainPanel.add(clearButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(130, 420, 100, 30);
        mainPanel.add(refreshButton);

        frame.setVisible(true);
    }
}
