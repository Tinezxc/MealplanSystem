package com.mycompany.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProgressTracker {

    public ProgressTracker(String email) {
        JFrame frame = new JFrame("PROGRESS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALLAN JUSTINE\\Downloads\\LogoUi.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 1550, 800);
        frame.setContentPane(background);
        background.setLayout(null);

        // Left-side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(200));

        // Navigation Buttons
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
                frame.dispose(); // Close current screen

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

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Title
        JLabel titleLabel = new JLabel("PROGRESS TRACKER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(20, 55, 400, 40);
        mainPanel.add(titleLabel);

        // ---- New topPanel for search and user button ----
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(830, 40, 520, 70);
        topPanel.setOpaque(false);
        mainPanel.add(topPanel);

        // Search panel with rounded border and emoji
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(0, 20, 345, 35);
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

        // Search action on emoji click
        emojiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String searchText = searchField.getText();
                JOptionPane.showMessageDialog(frame, "Search functionality not implemented for: " + searchText);
            }
        });

        // Circular user button with custom painting
        JButton userButton = new JButton("👤") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
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
        userButton.setBounds(350, 20, 35, 35);
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

        // Optional user button action
        userButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "User button clicked!");
        });

        // Select food button
        JButton selectedFoodButton = new JButton("Select Food");
        selectedFoodButton.setBackground(Color.WHITE);
        selectedFoodButton.setBounds(1070, 160, 180, 30);
        mainPanel.add(selectedFoodButton);

        selectedFoodButton.addActionListener(e -> {
            frame.dispose();
            new Food(email); // Open Food screen
        });

        // Nutrient data
        String[] nutrients = {"Calories", "Fats", "Protein", "Carbohydrates", "Fiber", "Water Intake"};
        int[] values = {1650, 10, 70, 150, 12, 1800}; // Max values
        String[] units = {"kcal", "g", "g", "g", "g", "ml"};

        int yPos = 200;
        for (int i = 0; i < nutrients.length; i++) {
            JLabel nutrientLabel = new JLabel(nutrients[i] + ":");
            nutrientLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            nutrientLabel.setBounds(50, yPos, 200, 35);
            mainPanel.add(nutrientLabel);

            JProgressBar progressBar = new JProgressBar(0, values[i]);
            progressBar.setValue(0); // Start at zero
            progressBar.setString("0 " + units[i]);
            progressBar.setStringPainted(true);
            progressBar.setFont(new Font("Arial", Font.PLAIN, 16));
            progressBar.setBounds(250, yPos, 1000, 35);
            mainPanel.add(progressBar);

            yPos += 60;
        }

        int totalGain = 0; // Total gain is initially zero

        JLabel totalGainLabel = new JLabel("Total Gain: " + totalGain);
        totalGainLabel.setFont(new Font("Arial", Font.BOLD, 22));
        totalGainLabel.setBounds(250, yPos, 400, 40);
        mainPanel.add(totalGainLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ProgressTracker("demo@example.com");
    }

    static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
