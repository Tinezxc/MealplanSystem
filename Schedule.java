

package com.mycompany.schedule;
import javax.swing.*;
import java.awt.*;

public class Schedule {
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday", "Sunday"};
    private static final String[] MEALS = {"Breakfast", "Snack 1", "Lunch", "Snack 2", "Dinner"};
    private static final String[] WEEKS = {"Week 1", "Week 2", "Week 3", "Week 4"};

 public static void main(String[] args) {
        JFrame frame = new JFrame("Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 530);
        frame.setLayout(null);

        // Side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.GREEN);
        sidePanel.setBounds(0, 0, 90, 500);
        sidePanel.setLayout(new GridLayout(5, 1));
        frame.add(sidePanel);

        String[] navigatorTitles = {"DASHBOARD", "MEAL PLAN", "PROGRESS", "SCHEDULE", "NOTIFICATION"};
        for (String title : navigatorTitles) {
            JButton navigatorButton = new JButton(title);
            navigatorButton.setBackground(Color.GREEN);
            navigatorButton.setBorderPainted(false);
            navigatorButton.setFocusPainted(false);
            navigatorButton.setFont(new Font("Arial", Font.PLAIN, 7));
            sidePanel.add(navigatorButton);
        }

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 720, 500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Top-right bar
        JPanel topRightPanel = new JPanel(null);
        topRightPanel.setBackground(Color.WHITE);
        topRightPanel.setBounds(0, 0, 720, 40);
        mainPanel.add(topRightPanel);

        JButton backButton = new JButton("<");
        backButton.setBounds(15, 5, 43, 30);
        backButton.setBackground(Color.WHITE);
        topRightPanel.add(backButton);

        JLabel scheduleTitle = new JLabel("SCHEDULE");
        scheduleTitle.setFont(new Font("Arial", Font.BOLD, 16));
        scheduleTitle.setBounds(70, 10, 150, 30);
        topRightPanel.add(scheduleTitle);

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

        // Meal panel with schedule grid
        JPanel mealPanel = new JPanel(new BorderLayout());
        mealPanel.setBounds(20, 60, 680, 400);
        mainPanel.add(mealPanel);

        // Header: Week selector and meal labels
        JPanel headerPanel = new JPanel(new GridLayout(1, MEALS.length + 1, 10, 10));
        JComboBox<String> weekComboBox = new JComboBox<>(WEEKS);
        weekComboBox.setFont(new Font("Arial", Font.PLAIN, 10));
        headerPanel.add(weekComboBox);

        for (String meal : MEALS) {
            JLabel label = new JLabel(meal, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 10));
            headerPanel.add(label);
        }

        // Grid for days and meal buttons
        JPanel gridPanel = new JPanel(new GridLayout(DAYS.length, MEALS.length + 1, 10, 10));
        for (String day : DAYS) {
            JLabel dayLabel = new JLabel(day.toUpperCase());
            dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
            gridPanel.add(dayLabel);

            for (int j = 0; j < MEALS.length; j++) {
                JButton mealButton = new JButton("+");
                mealButton.setFont(new Font("Arial", Font.PLAIN, 10));
                mealButton.setFocusPainted(false);
                mealButton.setBackground(Color.WHITE);
                mealButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(mealButton);
            }
        }

        mealPanel.add(headerPanel, BorderLayout.NORTH);
        mealPanel.add(gridPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
