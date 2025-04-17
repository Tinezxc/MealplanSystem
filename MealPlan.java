package com.mycompany.mealplan;


import javax.swing.*;
import java.awt.*;

public class MealPlan {
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] MEALS = {"Breakfast", "Snack 1", "Lunch", "Snack 2", "Dinner"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MealPlan::new);
    }

    public MealPlan() {
        JFrame frame = new JFrame("Meal Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setLayout(new BorderLayout());

        // Sidebar Panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.GREEN);
        sidePanel.setPreferredSize(new Dimension(150, frame.getHeight()));

        String[] navItems = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(140, 40));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(Color.GREEN);
            navButton.setBorderPainted(false);
            sidePanel.add(Box.createVerticalStrut(20));
            sidePanel.add(navButton);
        }

        // Meal Plan Panel
        JPanel mealPanel = new JPanel();
        mealPanel.setLayout(new BorderLayout());
        mealPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        // Meal Header Row
        JPanel headerPanel = new JPanel(new GridLayout(1, MEALS.length + 1, 10, 10));
        headerPanel.add(new JLabel("Day"));
        
        
        for (String meal : MEALS) {
            JLabel label = new JLabel(meal, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 10));
            headerPanel.add(label);
        }

        // Meal Grid
        JPanel gridPanel = new JPanel(new GridLayout(DAYS.length, MEALS.length + 1, 10, 10));

        for (String day : DAYS) {
            JLabel dayLabel = new JLabel(day.toUpperCase());
            dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
            gridPanel.add(dayLabel);

            for (int j = 0; j < MEALS.length; j++) {
                JButton mealButton = new JButton("+");
                mealButton.setFont(new Font("Arial", Font.PLAIN, 10));
                mealButton.setFocusPainted(false);
                mealButton.setBackground(Color.LIGHT_GRAY);
                mealButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(mealButton);
            }
        }

        mealPanel.add(headerPanel, BorderLayout.NORTH);
        mealPanel.add(gridPanel, BorderLayout.CENTER);

        // Add Panels to Frame
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(mealPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}