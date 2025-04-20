
package com.mycompany.food;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Food {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Food::new);
    }

    public Food() {
        JFrame frame = new JFrame("FOODS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(815, 560);
        frame.setLayout(null);

        // Sidebar
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.GREEN);
        sidePanel.setBounds(0, 0, 90, 530);
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

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(90, 0, 710, 550);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Top Panel (Search + User)
        JPanel topRightPanel = new JPanel(null);
        topRightPanel.setBackground(Color.WHITE);
        topRightPanel.setBounds(0, 0, 710, 40);
        mainPanel.add(topRightPanel);

        JButton backButton = new JButton("<");
        backButton.setBounds(15, 5, 43, 30);
        backButton.setBackground(Color.WHITE);
        topRightPanel.add(backButton);

        JLabel scheduleTitle = new JLabel("FOODS");
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

        // Action Buttons Panel (Save, Edit, Delete)
        JPanel actionPanel = new JPanel();
        actionPanel.setBounds(427, 45, 280, 40);
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        actionPanel.setBackground(Color.WHITE);
        mainPanel.add(actionPanel);

        JButton saveButton = new JButton("Save");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        JButton[] actionButtons = {saveButton, editButton, deleteButton};
        for (JButton btn : actionButtons) {
            btn.setPreferredSize(new Dimension(80, 30));
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setFocusPainted(false);
        }

        actionPanel.add(saveButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);

      // New Filters Panel: All Items, Categories, Meal Plan
      JPanel filterPanel = new JPanel();
      filterPanel.setBounds(10, 90, 690, 40); // Shift left and stretch across
      filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
      filterPanel.setBackground(Color.WHITE);
      mainPanel.add(filterPanel);

     // ALL ITEMS Label + Text Field
     JLabel allItemsLabel = new JLabel("ALL ITEMS:");
     filterPanel.add(allItemsLabel);

     JTextField allItemsField = new JTextField("Search food...");
     allItemsField.setPreferredSize(new Dimension(120, 30));
     filterPanel.add(allItemsField);

     // CATEGORIES Label + ComboBox
     JLabel categoryLabel = new JLabel("CATEGORIES:");
     filterPanel.add(categoryLabel);

     String[] categories = {"Vegetables", "Fruits", "Protein", "Carb", "Dairy"};
     JComboBox<String> categoryBox = new JComboBox<>(categories);
     categoryBox.setPreferredSize(new Dimension(130, 30));
     filterPanel.add(categoryBox);

     // MEAL PLAN Label + ComboBox
     JLabel mealPlanLabel = new JLabel("MEAL PLAN:");
     filterPanel.add(mealPlanLabel);

     String[] mealPlans = {"Breakfast", "Snack1", "Lunch", "Snack2", "Dinner"};
     JComboBox<String> mealPlanBox = new JComboBox<>(mealPlans);
     mealPlanBox.setPreferredSize(new Dimension(130, 30));
     filterPanel.add(mealPlanBox);



        // Table Setup
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
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 135, 690, 375); // Adjusted for filter panel
        mainPanel.add(scrollPane);

        frame.setVisible(true);
    }
}