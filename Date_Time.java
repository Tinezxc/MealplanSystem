
package com.mycompany.date_time;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Date_Time {
    public Date_Time(String email, String username) {
        JFrame frame = new JFrame("Date and Time");
        frame.setSize(800, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Left-side Green Panel with Navigation Buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 90, 500);
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

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 720, 500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Top Right Panel
        JPanel topRightPanel = new JPanel(null);
        topRightPanel.setBackground(Color.WHITE);
        topRightPanel.setBounds(0, 0, 720, 40);
        mainPanel.add(topRightPanel);

        JButton backButton = new JButton("<");
        backButton.setBounds(15, 5, 43, 30);
        backButton.setBackground(Color.WHITE);
        topRightPanel.add(backButton);

        JLabel DateTimeTitle = new JLabel("DATE AND TIME");
        DateTimeTitle.setFont(new Font("Arial", Font.BOLD, 16));
        DateTimeTitle.setBounds(70, 10, 150, 30);
        topRightPanel.add(DateTimeTitle);

        JTextField searchField = new JTextField();
        searchField.setBounds(327, 5, 200, 30);
        topRightPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(530, 5, 80, 30);
        searchButton.setBackground(Color.WHITE);
        topRightPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBounds(615, 5, 80, 30);
        userButton.setBackground(Color.WHITE);
        topRightPanel.add(userButton);

        // Date Picker
        int centerX = (650 - 200) / 2;

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(centerX - 80, 100, 100, 25);
        mainPanel.add(dateLabel);

        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setBounds(centerX, 100, 200, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        mainPanel.add(dateSpinner);

        // Time Picker
        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setBounds(centerX - 80, 150, 100, 25);
        mainPanel.add(timeLabel);

        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        JSpinner timeSpinner = new JSpinner(timeModel);
        timeSpinner.setBounds(centerX, 150, 200, 30);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        mainPanel.add(timeSpinner);

        // Save and Edit Buttons
        int buttonY = 210;
        int buttonWidth = 80;
        int spacing = 20;
        int totalWidth = (buttonWidth * 2) + spacing;
        int startX = (650 - totalWidth) / 2;

        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(startX, buttonY, buttonWidth, 30);
        mainPanel.add(saveButton);

        JButton editButton = new JButton("EDIT");
        editButton.setBounds(startX + buttonWidth + spacing, buttonY, buttonWidth, 30);
        mainPanel.add(editButton);

        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Date_Time("demo@example.com", "demoUser");
    }
}