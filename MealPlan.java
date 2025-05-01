package com.mycompany.mealplan;

import javax.swing.*;
import java.awt.*;

public class MealPlan {
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] MEALS = {"Breakfast", "Snack 1", "Lunch", "Snack 2", "Dinner"};
    private static final String[] WEEKS = {"Week 1", "Week 2", "Week 3", "Week 4"};

    public MealPlan(String email) {
        JFrame frame = new JFrame("Meal Plan");
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

        // Top Panel
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(150, 0, 1400, 100);
        topPanel.setBackground(null); // Transparent
        frame.add(topPanel);

        JButton backButton = new JButton("<");
        backButton.setBounds(30, 25, 60, 30);
        backButton.setBackground(Color.WHITE);
        topPanel.add(backButton);

        JLabel titleLabel = new JLabel("MEAL PLAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(110, 20, 300, 40);
        topPanel.add(titleLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(860, 25, 300, 30);
        topPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(1170, 25, 90, 30);
        searchButton.setBackground(Color.WHITE);
        topPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBounds(1270, 25, 60, 30);
        userButton.setBackground(Color.WHITE);
        topPanel.add(userButton);

        // Action Buttons Panel below search bar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBounds(860, 60, 470, 35);
        buttonPanel.setOpaque(false);
        topPanel.add(buttonPanel);

        JButton editPlanButton = new JButton("EDIT PLAN");
        JButton saveButton = new JButton("SAVE");
        JButton editButton = new JButton("EDIT");
        JButton deleteButton = new JButton("DELETE");

        for (JButton btn : new JButton[]{editPlanButton, saveButton, editButton, deleteButton}) {
            btn.setPreferredSize(new Dimension(105, 30));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);
            buttonPanel.add(btn);
        }

        // Meal Grid Section
        JPanel mealPanel = new JPanel(new BorderLayout());
        mealPanel.setBounds(200, 100, 1290, 640);
        frame.add(mealPanel);

        // Header Panel
        JPanel headerPanel = new JPanel(new GridLayout(1, MEALS.length + 1, 20, 20));
        JComboBox<String> weekComboBox = new JComboBox<>(WEEKS);
        weekComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(weekComboBox);

        for (String meal : MEALS) {
            JLabel label = new JLabel(meal, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            headerPanel.add(label);
        }

        // Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(DAYS.length, MEALS.length + 1, 20, 20));
        for (String day : DAYS) {
            JLabel dayLabel = new JLabel(day.toUpperCase(), SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
            gridPanel.add(dayLabel);

            for (int j = 0; j < MEALS.length; j++) {
                JButton mealButton = new JButton("+");
                mealButton.setFont(new Font("Arial", Font.PLAIN, 14));
                mealButton.setFocusPainted(false);
                mealButton.setBackground(Color.WHITE);
                mealButton.setPreferredSize(new Dimension(80, 40));
                gridPanel.add(mealButton);
            }
        }

        mealPanel.add(headerPanel, BorderLayout.NORTH);
        mealPanel.add(gridPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MealPlan("demo@example.com");
    }
}
