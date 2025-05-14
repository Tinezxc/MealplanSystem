package com.mycompany.food;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        scheduleTitle.setBounds(100, 25, 200, 30);
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

        // Table Section
        String[] columns = {"ALL ITEMS", "CATEGORIES", "CALORIES", "PROTEIN (g)", "SUGAR (g)", "FAT (g)", "FRUITS"};
        String[][] data = {
                {"Chicken Breast", "Protein", "165", "31", "0", "3.6", "No"},
                {"Salmon", "Protein", "208", "20", "0", "13", "No"},
                {"Apple", "Fruit", "95", "0.5", "19", "0.3", "Yes"},
                {"Banana", "Fruit", "105", "1.3", "14", "0.4", "Yes"},
                {"Brown Rice", "Carb", "216", "5", "0.7", "1.8", "No"},
                {"Broccoli", "Vegetable", "55", "3.7", "1.7", "0.6", "No"},
                {"Greek Yogurt", "Dairy", "100", "10", "4", "0.7", "No"},
                {"Oatmeal", "Carb", "150", "5", "1", "2.5", "No"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 180, 1360, 580);
        mainPanel.add(scrollPane);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Food("demo@example.com");
    }
}
