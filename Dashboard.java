package com.mycompany.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard {

    public Dashboard(String email) {
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));

        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);

        // === Left Panel ===
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
        frame.dispose(); // Close current Dashboard

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


        // === Main Panel ===
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Title
        JLabel dashboardTitle = new JLabel("DASHBOARD");
        dashboardTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        dashboardTitle.setBounds(20, 70, 300, 50);
        mainPanel.add(dashboardTitle);

        // === Search Bar Panel with emoji on right ===
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(860, 70, 330, 35);  
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new RoundedBorder(10));

        // JTextField inside the search panel
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(null);
        searchField.setBackground(Color.WHITE);
        searchPanel.add(searchField, BorderLayout.CENTER);

        // 🔍 Emoji Label on the right inside the text field
        JLabel emojiLabel = new JLabel("🔍");
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));  
        emojiLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 8));
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchPanel.add(emojiLabel, BorderLayout.EAST);

        mainPanel.add(searchPanel);

        // User Button (👤)
        JButton userButton = new JButton("👤") {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill white circle background
        g2d.setColor(Color.WHITE);
        g2d.fillOval(0, 0, getWidth(), getHeight());

        // Draw black border circle
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(1)); 
        g2d.drawOval(1, 1, getWidth() - 2, getHeight() - 2);

        g2d.dispose();

        // Draw the icon/text on top
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
        userButton.setBounds(1200, 70, 35, 35);  // Circular size (square bounds)
        userButton.setFocusPainted(false);
        userButton.setBorder(BorderFactory.createEmptyBorder());
        userButton.setContentAreaFilled(false);
        userButton.setOpaque(false);
        mainPanel.add(userButton);

        userButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new UserInfo(email); // Open Forgot Password screen on click
            }
        });
        
        // Categories Label
        JLabel categoriesLabel = new JLabel("CATEGORIES");
        categoriesLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        categoriesLabel.setBounds(150, 150, 250, 40);
        mainPanel.add(categoriesLabel);

        // Load images for categories
        ImageIcon[] categoryImages = new ImageIcon[5];
        categoryImages[0] = new ImageIcon(new ImageIcon("C:/Users/THINKPAD/Downloads/protein.jpg")
        .getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
          categoryImages[1] = new ImageIcon(new ImageIcon("C:/Users/THINKPAD/Downloads/download (45).jpg")
        .getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
          categoryImages[2] = new ImageIcon(new ImageIcon("C:/Users/THINKPAD/Downloads/c.jpg")
        .getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
          categoryImages[3] = new ImageIcon(new ImageIcon("C:/Users/THINKPAD/Downloads/v.jpg")
        .getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
           categoryImages[4] = new ImageIcon(new ImageIcon("C:/Users/THINKPAD/Downloads/f.jpg")
        .getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
          
        
        // Category Boxes with images
        int xPos = 150;
        for (int i = 0; i < 5; i++) {
            JPanel categoryBox = new JPanel();
            categoryBox.setBounds(xPos, 200, 180, 120);
            categoryBox.setBackground(new Color(255, 255, 255, 200));
            categoryBox.setBorder(new CompoundBorder(
                    new LineBorder(Color.LIGHT_GRAY, 1, true),
                    new EmptyBorder(10, 10, 10, 10)));
            categoryBox.setLayout(new BorderLayout());

            // Image label
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(categoryImages[i]);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            categoryBox.add(imageLabel, BorderLayout.CENTER);

            mainPanel.add(categoryBox);
            xPos += 230;
        }

        // Notification Label
        JLabel notificationLabel = new JLabel("NOTIFICATION");
        notificationLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        notificationLabel.setBounds(150, 360, 250, 40);
        mainPanel.add(notificationLabel);

        // Notification Panels
        int yPos = 410;
        for (int i = 0; i < 2; i++) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setBounds(150, yPos, 1100, 120);
            notificationPanel.setBackground(new Color(255, 255, 255, 230));
            notificationPanel.setBorder(new CompoundBorder(
                    new LineBorder(new Color(200, 200, 200), 1, true),
                    new EmptyBorder(10, 10, 10, 10)));
            notificationPanel.setLayout(new BorderLayout());

            JLabel notificationTitle = new JLabel("Notification");
            notificationTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
            JTextArea notificationText = new JTextArea(i == 0 ? "It's time for your lunch baby!" : "notif.");
            notificationText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            notificationText.setLineWrap(true);
            notificationText.setWrapStyleWord(true);
            notificationText.setEditable(false);
            notificationText.setBackground(new Color(0, 0, 0, 0));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBackground(new Color(255, 255, 255, 0));
            JButton readButton = new JButton("READ");
            JButton deleteButton = new JButton("DELETE");
            styleButton(readButton);
            styleButton(deleteButton);
            buttonPanel.add(readButton);
            buttonPanel.add(deleteButton);

            notificationPanel.add(notificationTitle, BorderLayout.NORTH);
            notificationPanel.add(notificationText, BorderLayout.CENTER);
            notificationPanel.add(buttonPanel, BorderLayout.SOUTH);

            mainPanel.add(notificationPanel);
            yPos += 140;
        }

        frame.setVisible(true);
    }

    // Utility method to apply modern style to buttons
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 240));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setBorder(new RoundedBorder(10));
    }

    // Custom border class for rounded corners
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius + 1, radius + 1, radius + 1, radius + 1);
            return insets;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard("demo@example.com"));
    }
}
