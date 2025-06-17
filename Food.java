

package com.mycompany.food;

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

        String[] headers = {"FOODS", "CATEGORY", "CALORIES", "PROTEIN", "SUGAR", "FAT"};
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
            {"Boiled Egg", "Protein", "78", "6.3", "0.6", "5.3", "D:\\My Documents\\Downloads\\boiled_egg.png"},
            {"Oatmeal with Banana and Peanut Butter", "Carb", "250", "6", "10", "8", "D:\\My Documents\\Downloads\\oatmeal_banana_pb.png"},
            {"Pandesal with Peanut Butter", "Carb", "220", "7", "5", "11", "D:\\My Documents\\Downloads\\pandesal_pb.png"},
            {"Arroz Caldo with Chicken Breast", "Protein", "300", "20", "2", "10", "D:\\My Documents\\Downloads\\arroz_caldo.png"},
            {"Taho", "Dairy", "180", "9", "14", "5", "D:\\My Documents\\Downloads\\taho.png"},
            {"Champorado with Tablea and Brown Rice", "Carb", "240", "4", "15", "6", "D:\\My Documents\\Downloads\\champorado.png"},
            {"Tinapa with Brown Rice", "Protein", "350", "25", "0", "20", "D:\\My Documents\\Downloads\\tinapa.png"},
            {"Tuyo with Tomato and Rice", "Protein", "300", "20", "2", "15", "D:\\My Documents\\Downloads\\tuyo.png"},
            {"Hard Boiled Eggs with Kamote", "Protein", "270", "12", "8", "10", "D:\\My Documents\\Downloads\\egg_kamote.png"},
            {"Ginisang Munggo", "Vegetable", "180", "12", "6", "4", "D:\\My Documents\\Downloads\\munggo.png"},
            {"Whole Wheat Pandesal with Kesong Puti", "Dairy", "210", "8", "4", "9", "D:\\My Documents\\Downloads\\pandesal_keso.png"},
            {"Tortang Talong", "Vegetable", "190", "10", "3", "13", "D:\\My Documents\\Downloads\\tortang_talong.png"},
            {"Fresh Fruit Salad", "Vegetable", "120", "1", "22", "3", "D:\\My Documents\\Downloads\\fruit_salad.png"},
            {"Brown Rice with Scrambled Egg", "Carb", "280", "10", "1", "9", "D:\\My Documents\\Downloads\\rice_egg.png"},
            {"Fish Lumpia", "Protein", "200", "15", "2", "10", "D:\\My Documents\\Downloads\\fish_lumpia.png"},
            {"Ampalaya Omelet", "Vegetable", "150", "10", "2", "8", "D:\\My Documents\\Downloads\\ampalaya_omelet.png"},
            {"Saging na Saba", "Carb", "120", "1", "27", "0.3", "D:\\My Documents\\Downloads\\saba.png"},
            {"Vegetable Okoy", "Vegetable", "230", "5", "12", "14", "D:\\My Documents\\Downloads\\okoy.png"},
            {"Chicken Tocino", "Protein", "260", "22", "8", "14", "D:\\My Documents\\Downloads\\tocino.png"},
            {"Fresh Mango Slices with Greek Yogurt", "Dairy", "160", "8", "18", "3", "D:\\My Documents\\Downloads\\mango_yogurt.png"},
            {"Chocolate Oatmeal and Chia Champorado", "Carb", "240", "6", "16", "7", "D:\\My Documents\\Downloads\\chia_champorado.png"},
            {"Malunggay Pancake", "Carb", "190", "5", "10", "8", "D:\\My Documents\\Downloads\\malunggay_pancake.png"},
            {"Fried Rice", "Carb", "240", "6", "2", "10", "D:\\My Documents\\Downloads\\fried_rice.png"},
            {"Fresh Lumpia with Garlic Sauce", "Vegetable", "200", "4", "6", "8", "D:\\My Documents\\Downloads\\fresh_lumpia.png"},
            {"Homemade Sardines with Wheat Toast", "Protein", "280", "18", "2", "15", "D:\\My Documents\\Downloads\\sardines_toast.png"},
            {"Tinolang Isda", "Protein", "230", "20", "2", "9", "D:\\My Documents\\Downloads\\tinolang_isda.png"},
            {"Ginataang Mais", "Carb", "210", "4", "14", "5", "D:\\My Documents\\Downloads\\ginataang_mais.png"},
            {"Banana Oatmeal", "Carb", "200", "5", "18", "4", "D:\\My Documents\\Downloads\\banana_oatmeal.png"},
            {"Chia Seed Pudding with Mango", "Dairy", "190", "6", "16", "9", "D:\\My Documents\\Downloads\\chia_pudding.png"},
            {"Banana Cue", "Carb", "220", "2", "40", "8", "D:\\My Documents\\Downloads\\banana_cue.png"},
            {"Kamote Cue", "Carb", "200", "1", "38", "6", "D:\\My Documents\\Downloads\\kamote_cue.png"},
            {"Kutsinta", "Carb", "100", "1", "20", "1", "D:\\My Documents\\Downloads\\kutsinta.png"},
            {"Espasol", "Carb", "150", "2", "25", "4", "D:\\My Documents\\Downloads\\espasol.png"},
            {"Boiled Saging na Saba", "Carb", "120", "1", "27", "0.3", "D:\\My Documents\\Downloads\\boiled_saba.png"},
            {"Boiled Corn on the Cob", "Carb", "130", "3", "29", "1", "D:\\My Documents\\Downloads\\boiled_corn.png"},
            {"Papaya Slices", "Vegetable", "55", "0.9", "14", "0.2", "D:\\My Documents\\Downloads\\papaya.png"},
            {"Pineapple Cubes", "Fruit", "60", "0.5", "16", "0.1", "D:\\My Documents\\Downloads\\pineapple.png"},
            {"Mango Strips", "Fruit", "99", "1.4", "25", "0.6", "D:\\My Documents\\Downloads\\mango.png"},
            {"Cucumber Slices with Vinegar", "Vegetable", "20", "1", "4", "0.1", "D:\\My Documents\\Downloads\\cucumber.png"},
            {"Tomato Slices with Salt", "Vegetable", "15", "0.8", "3", "0.2", "D:\\My Documents\\Downloads\\tomato.png"},
            {"Boiled Peanuts", "Protein", "160", "8", "6", "14", "D:\\My Documents\\Downloads\\peanuts.png"},
            {"Baked Lumpiang Gulay", "Vegetable", "180", "6", "10", "8", "D:\\My Documents\\Downloads\\lumpiang_gulay.png"},
            {"Pan de Coco", "Carb", "260", "6", "35", "10", "D:\\My Documents\\Downloads\\pan_de_coco.png"},
            {"Ube Halaya", "Carb", "200", "2", "38", "6", "D:\\My Documents\\Downloads\\ube_halaya.png"},
            {"Fresh Buko Juice", "Beverage", "90", "0", "22", "0", "D:\\My Documents\\Downloads\\buko_juice.png"},
            {"Grilled Corn", "Carb", "150", "3", "28", "2", "D:\\My Documents\\Downloads\\grilled_corn.png"},
            {"Pakwan", "Fruit", "50", "1", "12", "0.1", "D:\\My Documents\\Downloads\\pakwan.png"},
            {"Boiled Quail Eggs", "Protein", "160", "13", "1", "11", "D:\\My Documents\\Downloads\\quail_eggs.png"},
            {"Cheesy Nilupak", "Carb", "230", "4", "25", "12", "D:\\My Documents\\Downloads\\nilupak.png"},
            {"Malunggay Smoothie", "Vegetable", "130", "3", "18", "5", "D:\\My Documents\\Downloads\\malunggay_smoothie.png"},
            {"Baked Sweet Potato with Cinnamon", "Carb", "180", "2", "30", "4", "D:\\My Documents\\Downloads\\baked_cinnamon.png"},
            {"Turmeric Ginger Tea with Kasuy", "Beverage", "70", "2", "6", "4", "D:\\My Documents\\Downloads\\turmeric_tea.png"},
            {"Lowfat Yogurt with Ripe Mango", "Dairy", "150", "6", "22", "3", "D:\\My Documents\\Downloads\\yogurt_mango.png"},};

        for (String[] food : foodData) {
            JPanel foodPanel = new JPanel(null);
            foodPanel.setPreferredSize(new Dimension(1320, 120));
            foodPanel.setBackground(Color.white);
            foodPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // ✅ Corrected index: use food[6] for the image path
            ImageIcon icon = new ImageIcon(food[6]);
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
