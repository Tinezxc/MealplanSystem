package com.mycompany.date_time;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Date_Time {
    public Date_Time(String email){
        JFrame frame = new JFrame("PROGRESS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);

        // Left-side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(150));

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

        // Main white panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(120, 0, 1430, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JLabel titleLabel = new JLabel("SCHEDULE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(20, 40, 300, 40);
        mainPanel.add(titleLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(870, 40, 300, 30);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(1175, 40, 90, 30);
        searchButton.setBackground(Color.WHITE);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBounds(1270, 40, 60, 30);
        userButton.setBackground(Color.WHITE);
        mainPanel.add(userButton);

        //date picker


        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(550, 220, 200, 40);
        mainPanel.add(dateLabel);

        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setBounds(660, 200, 200, 50);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        mainPanel.add(dateSpinner);
        
        //Add Change Listener to JSpinner
       dateSpinner.addChangeListener(e -> {
       Date selectedDate = (Date) dateSpinner.getValue();
       java.util.Calendar calendar = java.util.Calendar.getInstance();
       calendar.setTime(selectedDate);
       });
       
        //Time Picker
        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setBounds(550, 300, 200, 40);
        mainPanel.add(timeLabel);

        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        JSpinner timeSpinner = new JSpinner(timeModel);
        timeSpinner.setBounds(660, 280, 200, 50);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        mainPanel.add(timeSpinner);

        // Save and Edit Buttons
        JButton saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 15));
        saveButton.setBackground(Color.WHITE);
        saveButton.setBounds(600, 400, 80, 20);
        mainPanel.add(saveButton);

        JButton editButton = new JButton("EDIT");
        editButton.setFont(new Font("Arial", Font.PLAIN, 15));
        editButton.setBackground(Color.WHITE);
        editButton.setBounds(730, 400, 80, 20);
        mainPanel.add(editButton);

        frame.setVisible(true);
    }
    
        public static void main(String[] args) {
        new Date_Time("demo@example.com");
    }
}
