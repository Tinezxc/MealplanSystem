package com.mycompany.ui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MealPlan {
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static final String[] MEALS = {"Breakfast", "Lunch", "Dinner", "Snack"};
    private static final String[] WEEKS = {"Week 1", "Week 2", "Week 3", "Week 4"};

    static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
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

    public MealPlan(String email) {
        JFrame frame = new JFrame("Meal Plan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(200));

        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(120, 40));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(new Color(60, 60, 60));
            navButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            navButton.setBorder(new RoundedBorder(10));

            navButton.addActionListener(e -> {
                frame.dispose();
                switch (title) {
                    case "DASHBOARD": new Dashboard(email); break;
                    case "MEAL PLAN": new MealPlan(email); break;
                    case "SCHEDULE": new Schedule(email); break;
                    case "PROGRESS": new ProgressTracker(email); break;
                    case "NOTIFICATION": new Notification(email); break;
                    default: JOptionPane.showMessageDialog(null, "Coming soon!");
                }
            });

            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(70));
        }

        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(150, 0, 1400, 100);
        topPanel.setOpaque(false);
        frame.add(topPanel);

        JLabel titleLabel = new JLabel("MEAL PLAN");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBounds(20, 20, 300, 40);
        topPanel.add(titleLabel);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(955, 20, 330, 35);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new RoundedBorder(10));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(null);
        searchField.setBackground(Color.WHITE);
        searchPanel.add(searchField, BorderLayout.CENTER);

        JLabel emojiLabel = new JLabel("🔍");
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));
        emojiLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchPanel.add(emojiLabel, BorderLayout.EAST);

        topPanel.add(searchPanel);

        JButton userButton = new JButton("👤") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.black);
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

        userButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        userButton.setBounds(1290, 20, 35, 35);
        userButton.setFocusPainted(false);
        userButton.setBorder(BorderFactory.createEmptyBorder());
        userButton.setContentAreaFilled(false);
        userButton.setOpaque(false);
        topPanel.add(userButton);
        
        userButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new UserInfo(email); // Open Forgot Password screen on click
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBounds(875, 65, 470, 35);
        buttonPanel.setOpaque(false);
        topPanel.add(buttonPanel);

        JButton addButton = new JButton("ADD");
        JButton saveButton = new JButton("SAVE");
        JButton editButton = new JButton("EDIT");
        JButton deleteButton = new JButton("DELETE");

        for (JButton btn : new JButton[]{addButton, saveButton, editButton, deleteButton}) {
            btn.setPreferredSize(new Dimension(70, 27));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);
            btn.setBorder(new RoundedBorder(10));
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            buttonPanel.add(btn);
        }

        JPanel mealPanel = new JPanel(new BorderLayout());
        mealPanel.setBounds(200, 110, 1290, 640);
        frame.add(mealPanel);

        JPanel headerPanel = new JPanel(new GridLayout(1, MEALS.length + 1, 20, 20));
        headerPanel.setOpaque(false);

        JPanel comboBoxWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        comboBoxWrapper.setOpaque(false);
        JComboBox<String> weekComboBox = new JComboBox<>(WEEKS);
        weekComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        weekComboBox.setPreferredSize(new Dimension(200, 30));
        comboBoxWrapper.add(weekComboBox);
        headerPanel.add(comboBoxWrapper);

        for (String meal : MEALS) {
            JLabel label = new JLabel(meal, SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            headerPanel.add(label);
        }

        JPanel gridPanel = new JPanel(new GridLayout(DAYS.length, MEALS.length + 1, 20, 20));
        gridPanel.setOpaque(false);
        for (String day : DAYS) {
            JLabel dayLabel = new JLabel(day.toUpperCase(), SwingConstants.CENTER);
            dayLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            gridPanel.add(dayLabel);

            for (String meal : MEALS) {
                JButton mealButton = new JButton("+");
                mealButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                mealButton.setFocusPainted(false);
                mealButton.setBackground(Color.WHITE);
                mealButton.setBorder(new RoundedBorder(10));
                mealButton.setPreferredSize(new Dimension(80, 40));
                mealButton.addActionListener(new MealButtonActionListener(day, meal));
                gridPanel.add(mealButton);
            }
        }

        mealPanel.add(headerPanel, BorderLayout.NORTH);
        mealPanel.add(gridPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Add functionality not implemented yet."));
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Meal plan saved successfully."));
        editButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Edit functionality not implemented yet."));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Delete functionality not implemented yet."));

        frame.setVisible(true);
    }

    private class MealButtonActionListener implements ActionListener {
        private final String day;
        private final String meal;

        public MealButtonActionListener(String day, String meal) {
            this.day = day;
            this.meal = meal;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Meal added for " + day + ": " + meal);
        }
    }

    public static void main(String[] args) {
        new MealPlan("demo@example.com");
    }
}
