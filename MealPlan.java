package com.mycompany.mealplan;


import javax.swing.*;
import java.awt.*;

public class MealPlan {
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] MEALS = {"Breakfast", "Snack 1", "Lunch", "Snack 2", "Dinner"};
    private static final String[] WEEKS = {"Week 1", "Week 2", "Week 3", "Week 4"};

    public MealPlan(String email, String username) {
        JFrame frame = new JFrame("Meal Plan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setLayout(null);

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 90, 520);
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

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(80, 0, 720, 500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Top panel
        JPanel topRightPanel = new JPanel(null);
        topRightPanel.setBounds(0, 0, 700, 40);
        mainPanel.add(topRightPanel);

        JButton backButton = new JButton("<");
        backButton.setBounds(15, 5, 43, 30);
        backButton.setBackground(Color.WHITE);
        topRightPanel.add(backButton);

        JLabel scheduleTitle = new JLabel("MEAL PLAN");
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

        // Meal grid
        JPanel mealPanel = new JPanel(new BorderLayout());
        mealPanel.setBounds(20, 60, 680, 400);
        mainPanel.add(mealPanel);

        // Header (Week + Meals)
        JPanel headerPanel = new JPanel(new GridLayout(1, MEALS.length + 1, 10, 10));
        JComboBox<String> weekComboBox = new JComboBox<>(WEEKS);
        weekComboBox.setFont(new Font("Arial", Font.PLAIN, 10));
        headerPanel.add(weekComboBox);

        for (String meal : MEALS) {
            JLabel label = new JLabel(meal, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 10));
            headerPanel.add(label);
        }

        // Grid of days x meals
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

    public static void main(String[] args) {
        new MealPlan("demo@example.com", "demoUser");
    }
}
