package com.mycompany.ui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Notification {
    private JTextArea notificationArea;

    public Notification(String email) {
        JFrame frame = new JFrame("Notification");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);

        // Left-side panel
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
            navButton.setMaximumSize(new Dimension(120, 200));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(new Color(60, 60, 60));
            navButton.setBorder(new RoundedBorder(10));
            navButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(90));
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JLabel dashboardTitle = new JLabel("NOTIFICATION");
        dashboardTitle.setBackground(Color.WHITE);
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 28));
        dashboardTitle.setBounds(20, 50, 300, 50);
        mainPanel.add(dashboardTitle);

        // Search Bar Panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(973, 63, 330, 35);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new RoundedBorder(10));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(new LineBorder(Color.BLACK));
        searchField.setBackground(Color.WHITE);
        searchPanel.add(searchField, BorderLayout.CENTER);

        JLabel emojiLabel = new JLabel("🔍");
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        emojiLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 8));
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchPanel.add(emojiLabel, BorderLayout.EAST);

        mainPanel.add(searchPanel);

        JButton userButton = new JButton("👤") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());

                g2d.setColor(Color.BLACK);
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
        userButton.setBounds(1310, 63, 35, 35);
        userButton.setFocusPainted(false);
        userButton.setBorder(BorderFactory.createEmptyBorder());
        userButton.setContentAreaFilled(false);
        userButton.setOpaque(false);
        mainPanel.add(userButton);

        notificationArea = new JTextArea();
        notificationArea.setEditable(false);
        notificationArea.setFont(new Font("Arial", Font.PLAIN, 18));
        notificationArea.setText(
                "- Meal Plan updated for next week\n" +
                        "- Progress Report is ready to view\n" +
                        "- System maintenance on April 18\n" +
                        "- Notification bell sound test\n"
        );

        JScrollPane scrollPane = new JScrollPane(notificationArea);
        scrollPane.setBounds(50, 120, 1330, 500);
        mainPanel.add(scrollPane);

        JButton clearButton = new JButton("Clear All");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(Color.WHITE);
        clearButton.setBounds(50, 650, 130, 30);
        clearButton.addActionListener(e -> notificationArea.setText("")); // Clear notifications
        mainPanel.add(clearButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setBackground(Color.WHITE);
        refreshButton.setBounds(220, 650, 130, 30);
        refreshButton.addActionListener(e -> refreshNotifications()); // Refresh notifications
        mainPanel.add(refreshButton);

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                leftPanel.setBounds(0, 0, 150, frameHeight);
                mainPanel.setBounds(150, 0, frameWidth - 150, frameHeight);

                frame.repaint();
            }
        });

        frame.setVisible(true);
    }

    private void refreshNotifications() {
        // Logic to refresh notifications can be added here
        // For demonstration, we will just append a message
        notificationArea.append("- New notification added\n");
    }

    static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notification("demo@example.com"));
    }
}
