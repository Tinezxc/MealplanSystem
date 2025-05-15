package com.mycompany.food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Food {
    public Food(String email) {
        JFrame frame = new JFrame("FOODS");
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

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Top Panel (Search + User)
        JPanel topRightPanel = new JPanel(null);
        topRightPanel.setBackground(Color.WHITE);
        topRightPanel.setBounds(0, 0, 1400, 60);
        mainPanel.add(topRightPanel);

        JLabel scheduleTitle = new JLabel("FOODS");
        scheduleTitle.setFont(new Font("Arial", Font.BOLD, 28));
        scheduleTitle.setBounds(20, 25, 200, 30);
        topRightPanel.add(scheduleTitle);

        JTextField searchField = new JTextField();
        searchField.setBounds(830, 25, 300, 30);
        topRightPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(1140, 25, 100, 30);
        searchButton.setBackground(Color.WHITE);
        topRightPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBounds(1250, 25, 100, 30);
        userButton.setBackground(Color.WHITE);
        topRightPanel.add(userButton);

        // Action Buttons Panel
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(1000, 70, 380, 40);
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        actionPanel.setBackground(Color.WHITE);
        mainPanel.add(actionPanel);

        JButton saveButton = new JButton("Save");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        for (JButton btn : new JButton[]{saveButton, editButton, deleteButton}) {
            btn.setPreferredSize(new Dimension(100, 30));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            actionPanel.add(btn);
        }

        // Filters Panel
        JPanel filterPanel = new JPanel();
        filterPanel.setBounds(20, 120, 1360, 50);
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        filterPanel.setBackground(Color.WHITE);
        mainPanel.add(filterPanel);

        filterPanel.add(new JLabel("ALL ITEMS:"));
        JTextField allItemsField = new JTextField("Search food...");
        allItemsField.setPreferredSize(new Dimension(200, 30));
        filterPanel.add(allItemsField);

        filterPanel.add(new JLabel("CATEGORIES:"));
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Vegetables", "Fruits", "Protein", "Carb", "Dairy"});
        categoryBox.setPreferredSize(new Dimension(200, 30));
        filterPanel.add(categoryBox);

        filterPanel.add(new JLabel("MEAL PLAN:"));
        JComboBox<String> mealPlanBox = new JComboBox<>(new String[]{"Breakfast", "Snack1", "Lunch", "Snack2", "Dinner"});
        mealPlanBox.setPreferredSize(new Dimension(200, 30));
        filterPanel.add(mealPlanBox);

        // Scrollable list of food items
        JPanel foodListPanel = new JPanel();
        foodListPanel.setLayout(new BoxLayout(foodListPanel, BoxLayout.Y_AXIS));
        foodListPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(foodListPanel);
        scrollPane.setBounds(20, 180, 1360, 580);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane);

        // Add header row
        JPanel headerPanel = new JPanel(null);
        headerPanel.setPreferredSize(new Dimension(1320, 30));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        String[] headers = {"FOODS", "CATEGORY", "CALORIES", "PROTEIN", "SUGAR", "FAT", "FRUIT"};
        int headerX = 130;
        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i]);
            label.setBounds(headerX + i * 180, 5, 170, 20);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            headerPanel.add(label);
        }
        foodListPanel.add(headerPanel);

        // Food data
        String[][] foodData = {
            {"Chicken Breast", "Protein", "165", "31", "0", "3.6", "No", "C:\\Users\\THINKPAD\\Downloads\\cb.jpg"},
            {"Salmon", "Protein", "208", "20", "0", "13", "No", "C:\\Users\\THINKPAD\\Downloads\\slm.jpg"},
            {"Apple", "Fruit", "95", "0.5", "19", "0.3", "Yes", "src/images/apple.png"},
            {"Banana", "Fruit", "105", "1.3", "14", "0.4", "Yes", "src/images/banana.png"},
            {"Brown Rice", "Carb", "216", "5", "0.7", "1.8", "No", "src/images/rice.png"},
            {"Broccoli", "Vegetable", "55", "3.7", "1.7", "0.6", "No", "src/images/broccoli.png"},
            {"Greek Yogurt", "Dairy", "100", "10", "4", "0.7", "No", "src/images/yogurt.png"},
            {"Oatmeal", "Carb", "150", "5", "1", "2.5", "No", "src/images/oatmeal.png"},
        };

        for (String[] food : foodData) {
            JPanel foodPanel = new JPanel(null);
            foodPanel.setPreferredSize(new Dimension(1320, 120));
            foodPanel.setBackground(Color.white);
            foodPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Load image
            ImageIcon icon = new ImageIcon(food[7]);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setBounds(10, 10, 100, 100);
            foodPanel.add(imageLabel);

            // Horizontal food info layout
            int xStart = 130;
            int y = 45;
            int spacing = 180;

            JLabel name = new JLabel(food[0]);
            name.setBounds(xStart, y, 150, 20);
            foodPanel.add(name);

            JLabel category = new JLabel(food[1]);
            category.setBounds(xStart + spacing, y, 150, 20);
            foodPanel.add(category);

            JLabel calories = new JLabel(food[2] + " kcal");
            calories.setBounds(xStart + spacing * 2, y, 100, 20);
            foodPanel.add(calories);

            JLabel protein = new JLabel(food[3] + "g");
            protein.setBounds(xStart + spacing * 3, y, 100, 20);
            foodPanel.add(protein);

            JLabel sugar = new JLabel(food[4] + "g");
            sugar.setBounds(xStart + spacing * 4, y, 100, 20);
            foodPanel.add(sugar);

            JLabel fat = new JLabel(food[5] + "g");
            fat.setBounds(xStart + spacing * 5, y, 100, 20);
            foodPanel.add(fat);

            JLabel fruits = new JLabel(food[6]);
            fruits.setBounds(xStart + spacing * 6, y, 100, 20);
            foodPanel.add(fruits);


            foodListPanel.add(Box.createVerticalStrut(10));
            foodListPanel.add(foodPanel);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Food("demo@example.com");
    }
}
