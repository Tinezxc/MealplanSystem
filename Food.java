package com.mycompany.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class RoundedBorder extends javax.swing.border.AbstractBorder {

    private final int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(radius, radius, radius, radius);
        return insets;
    }
}

public class Food {

    private JPanel foodListPanel;

    public Food(String email) {
        JFrame frame = new JFrame("FOODS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(150));

        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(120, 40));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(new Color(60, 60, 60));
            navButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            navButton.setBorder(new MealPlan.RoundedBorder(10));

            navButton.addActionListener(e -> {
                frame.dispose();
                switch (title) {
                    case "DASHBOARD":
                        new Dashboard(email);
                        break;
                    case "MEAL PLAN":
                        new MealPlan(email);
                        break;
                    case "SCHEDULE":
                        new Schedule(email);
                        break;
                    case "PROGRESS":
                        new ProgressTracker(email);
                        break;
                    case "NOTIFICATION":
                        new Notification(email);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Coming soon!");
                }
            });

            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(70));
        }

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        frame.add(mainPanel);

        JLabel scheduleTitle = new JLabel("FOODS");
        scheduleTitle.setFont(new Font("Arial", Font.BOLD, 28));
        scheduleTitle.setBounds(20, 20, 200, 30);
        mainPanel.add(scheduleTitle);

        JButton userButton = new JButton("👤") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.gray);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawOval(1, 1, getWidth() - 2, getHeight() - 2);
                g2d.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean contains(int x, int y) {
                int radius = getWidth() / 2;
                int centerX = radius;
                int centerY = getHeight() / 2;
                return (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= Math.pow(radius, 2);
            }
        };
        userButton.setFont(new Font("👤", Font.PLAIN, 14));
        userButton.setBounds(1325, 70, 40, 35);
        userButton.setFocusPainted(false);
        userButton.setBorder(BorderFactory.createEmptyBorder());
        userButton.setContentAreaFilled(false);
        mainPanel.add(userButton);

        userButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new UserInfo(email); // Open Forgot Password screen on click
            }
        });

        // Filter panel with buttons on the right
        JPanel filterPanel = new JPanel(new BorderLayout());
        filterPanel.setBounds(10, 120, 1360, 50);
        filterPanel.setBackground(Color.WHITE);
        mainPanel.add(filterPanel);

        // Left side filters
        JPanel filtersLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filtersLeft.setBackground(Color.WHITE);
        filtersLeft.add(new JLabel("ALL ITEMS:"));
        JTextField allItemsField = new JTextField("Search food...");
        allItemsField.setPreferredSize(new Dimension(200, 30));
        allItemsField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        filtersLeft.add(allItemsField);

        filtersLeft.add(new JLabel("CATEGORIES:"));
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Vegetables", "Fruits", "Protein", "Carb", "Dairy"});
        categoryBox.setPreferredSize(new Dimension(200, 30));
        categoryBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        filtersLeft.add(categoryBox);

        filtersLeft.add(new JLabel("MEAL PLAN:"));
        JComboBox<String> mealPlanBox = new JComboBox<>(new String[]{"Breakfast", "Lunch", "Dinner", "Snack"});
        mealPlanBox.setPreferredSize(new Dimension(200, 30));
        mealPlanBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        filtersLeft.add(mealPlanBox);
        filterPanel.add(filtersLeft, BorderLayout.WEST);

        // Right side buttons
        JPanel buttonsRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); // 10px gaps for better spacing
        buttonsRight.setBackground(Color.WHITE);
        buttonsRight.setPreferredSize(new Dimension(400, 50)); // Set enough width to contain all buttons

        String[] buttonLabels = {"ADD", "SAVE", "EDIT", "DELETE"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(80, 30));
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            buttonsRight.add(button);

            switch (label) {
                case "ADD" ->
                    button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Add functionality not implemented yet."));
                case "SAVE" ->
                    button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Food list saved successfully."));
                case "EDIT" ->
                    button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Edit functionality not implemented yet."));
                case "DELETE" ->
                    button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Delete functionality not implemented yet."));
            }
        }
        filterPanel.add(buttonsRight, BorderLayout.EAST); // Ensure it's added to EAST

        JPanel headerPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(211, 211, 211));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        headerPanel.setBounds(20, 180, 1360, 30);
        headerPanel.setOpaque(false);

        String[] headers = {"FOODS", "CATEGORY", "CALORIES", "PROTEIN", "SUGAR", "FAT", "FRUIT"};
        int headerX = 130;
        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i]);
            label.setBounds(headerX + i * 180, 5, 170, 20);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            headerPanel.add(label);
        }
        mainPanel.add(headerPanel);

        foodListPanel = new JPanel();
        foodListPanel.setLayout(new BoxLayout(foodListPanel, BoxLayout.Y_AXIS));
        foodListPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(foodListPanel);
        scrollPane.setBounds(20, 210, 1360, 550);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);

        String[][] foodData = {
            {"Chicken Breast", "Protein", "165", "31", "0", "3.6", "No", "C:\\Users\\THINKPAD\\Downloads\\cb.jpg"},
            {"Salmon", "Protein", "208", "20", "0", "13", "No", "C:\\Users\\THINKPAD\\Downloads\\slm.jpg"},
            {"Apple", "Fruit", "95", "0.5", "19", "0.3", "Yes", "src/images/apple.png"},
            {"Banana", "Fruit", "105", "1.3", "14", "0.4", "Yes", "src/images/banana.png"},
            {"Brown Rice", "Carb", "216", "5", "0.7", "1.8", "No", "src/images/rice.png"},
            {"Broccoli", "Vegetable", "55", "3.7", "1.7", "0.6", "No", "src/images/broccoli.png"},
            {"Greek Yogurt", "Dairy", "100", "10", "4", "0.7", "No", "src/images/yogurt.png"},
            {"Oatmeal", "Carb", "150", "5", "1", "2.5", "No", "src/images/oatmeal.png"},};

        for (String[] food : foodData) {
            JPanel foodPanel = new JPanel(null);
            foodPanel.setPreferredSize(new Dimension(1320, 120));
            foodPanel.setBackground(Color.white);
            foodPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            ImageIcon icon = new ImageIcon(food[7]);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setBounds(10, 10, 100, 100);
            foodPanel.add(imageLabel);

            int xStart = 130, y = 45, spacing = 180;
            foodPanel.add(createLabel(food[0], xStart, y));
            foodPanel.add(createLabel(food[1], xStart + spacing, y));
            foodPanel.add(createLabel(food[2] + " kcal", xStart + spacing * 2, y));
            foodPanel.add(createLabel(food[3] + "g", xStart + spacing * 3, y));
            foodPanel.add(createLabel(food[4] + "g", xStart + spacing * 4, y));
            foodPanel.add(createLabel(food[5] + "g", xStart + spacing * 5, y));
            foodPanel.add(createLabel(food[6], xStart + spacing * 6, y));

            foodListPanel.add(Box.createVerticalStrut(10));
            foodListPanel.add(foodPanel);
        }

        frame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 20);
        return label;
    }

    public static void main(String[] args) {
        new Food("demo@example.com");
    }
}
