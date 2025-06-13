package com.mycompany.ui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Notification {
    private JPanel notificationsPanel;
    private List<NotificationItem> notifications;
    private JFrame frame;
    private JScrollPane scrollPane;

    public Notification(String email) {
        notifications = new ArrayList<>();
        initializeNotifications();

        frame = new JFrame("Notification");
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
                    case "DASHBOARD" -> new Dashboard(email);
                    case "MEAL PLAN" -> new MealPlan(email);
                    case "SCHEDULE" -> new Schedule(email);
                    case "PROGRESS" -> new ProgressTracker(email);
                    case "NOTIFICATION" -> new Notification(email);
                    default -> JOptionPane.showMessageDialog(null, "Coming soon!");
                }
            });

            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(70));
        }

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        frame.add(mainPanel);

        JLabel dashboardTitle = new JLabel("NOTIFICATION");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 28));
        dashboardTitle.setBounds(20, 50, 300, 50);
        mainPanel.add(dashboardTitle);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(973, 63, 330, 35);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new RoundedBorder(10, Color.GRAY, 1));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
        
        userButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new UserInfo(email); // Open Forgot Password screen on click
            }
        });

        notificationsPanel = new JPanel();
        notificationsPanel.setLayout(new BoxLayout(notificationsPanel, BoxLayout.Y_AXIS));
        notificationsPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(notificationsPanel);
scrollPane.setBounds(50, 120, 1330, 500);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

// Smoother scrolling
scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Adjust this value if needed
mainPanel.add(scrollPane);

        refreshNotificationsUI();

        JButton clearButton = new JButton("Clear All");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(Color.WHITE);
        clearButton.setBounds(50, 650, 130, 30);
        clearButton.addActionListener(e -> clearAllNotifications());
        mainPanel.add(clearButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setBackground(Color.WHITE);
        refreshButton.setBounds(220, 650, 130, 30);
        refreshButton.addActionListener(e -> addRandomNotification());
        mainPanel.add(refreshButton);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();
                leftPanel.setBounds(0, 0, 150, frameHeight);
                mainPanel.setBounds(150, 0, frameWidth - 150, frameHeight);
                frame.repaint();
            }
        });

        frame.setVisible(true);
    }

    private void initializeNotifications() {
        notifications.add(new NotificationItem("Your meal plan for the week is ready!", "Info"));
        notifications.add(new NotificationItem("Don't forget to prep your meals for the week!", "Reminder"));
        notifications.add(new NotificationItem("Check your grocery list for the upcoming week.", "Info"));
        notifications.add(new NotificationItem("Time to log your meals for today!", "Reminder"));
        notifications.add(new NotificationItem("You have a new recipe suggestion for dinner.", "Tip"));
        notifications.add(new NotificationItem("Remember to include more vegetables in your meals.", "Tip"));
        notifications.add(new NotificationItem("Your meal plan has been updated with new recipes.", "Info"));
        notifications.add(new NotificationItem("Don't forget to track your water intake!", "Reminder"));
        notifications.add(new NotificationItem("You have a nutrition consultation scheduled for tomorrow.", "Alert"));
        notifications.add(new NotificationItem("Try a new healthy snack this week!", "Tip"));
        notifications.add(new NotificationItem("Plan your meals for the upcoming holiday.", "Reminder"));
        notifications.add(new NotificationItem("You have unread messages from your nutritionist.", "Alert"));
        notifications.add(new NotificationItem("It's time to review your dietary goals.", "Reminder"));
        notifications.add(new NotificationItem("Join the meal prep workshop this Saturday!", "Alert"));
        notifications.add(new NotificationItem("Don't skip breakfast! It's essential for your day.", "Tip"));
    }

    private void refreshNotificationsUI() {
        notificationsPanel.removeAll();
        for (NotificationItem item : notifications) {
            notificationsPanel.add(createNotificationPanel(item));
            notificationsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        notificationsPanel.revalidate();
        notificationsPanel.repaint();
    }

    private JPanel createNotificationPanel(NotificationItem item) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10, Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panel.setBackground(item.isRead() ? new Color(230, 230, 230) : Color.WHITE);

        JLabel categoryLabel = new JLabel(item.getCategory());
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        categoryLabel.setForeground(getCategoryColor(item.getCategory()));

        JLabel timeLabel = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(item.getTimestamp()));
        timeLabel.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        timeLabel.setForeground(Color.DARK_GRAY);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(categoryLabel, BorderLayout.WEST);
        topPanel.add(timeLabel, BorderLayout.EAST);

        JTextArea messageArea = new JTextArea(item.getMessage());
        messageArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        messageArea.setOpaque(false);

        JButton markReadBtn = new JButton(item.isRead() ? "Read" : "Mark as Read");
        markReadBtn.setFocusPainted(false);
        markReadBtn.setEnabled(!item.isRead());
        markReadBtn.addActionListener(e -> {
            item.setRead(true);
            refreshNotificationsUI();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(markReadBtn);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(messageArea, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private Color getCategoryColor(String category) {
        return switch (category.toLowerCase()) {
            case "alert" -> Color.RED.darker();
            case "warning" -> Color.ORANGE.darker();
            case "info" -> new Color(0, 120, 215);
            case "reminder" -> new Color(34, 139, 34);
            case "tip" -> Color.BLUE;
            default -> Color.GRAY;
        };
    }

    private void clearAllNotifications() {
        int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear all notifications?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            notifications.clear();
            refreshNotificationsUI();
        }
    }

    private void addRandomNotification() {
        String[] sampleMessages = {
            "Don't forget to log your progress!",
            "Your meal plan has pending items.",
            "Time to drink water!",
            "Schedule your workout session today.",
            "Update your profile details.",
            "You have unread messages.",
            "Reminder: Check today's schedule.",
            "Alert: System maintenance tonight."
        };
        String[] categories = {"Reminder", "Info", "Alert", "Warning"};
        Random rnd = new Random();
        String message = sampleMessages[rnd.nextInt(sampleMessages.length)];
        String category = categories[rnd.nextInt(categories.length)];
        notifications.add(new NotificationItem(message, category));
        refreshNotificationsUI();

        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, message, "New Notification (" + category + ")", JOptionPane.INFORMATION_MESSAGE);
    }

    static class NotificationItem {
        private final String message;
        private final String category;
        private final Date timestamp;
        private boolean read;

        public NotificationItem(String message, String category) {
            this.message = message;
            this.category = category;
            this.timestamp = new Date();
            this.read = false;
        }

        public String getMessage() { return message; }
        public String getCategory() { return category; }
        public Date getTimestamp() { return timestamp; }
        public boolean isRead() { return read; }
        public void setRead(boolean read) { this.read = read; }
    }

    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color borderColor;
        private final int thickness;

        public RoundedBorder(int radius) {
            this(radius, Color.GRAY, 1);
        }

        public RoundedBorder(int radius, Color borderColor, int thickness) {
            this.radius = radius;
            this.borderColor = borderColor;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x + thickness / 2, y + thickness / 2, width - thickness, height - thickness, radius, radius);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notification("demo@example.com"));
    }
}
