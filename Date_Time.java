package com.mycompany.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import javax.swing.border.AbstractBorder;

public class DateTime {
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
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius + 1, radius + 1, radius + 1, radius + 1);
            return insets;
        }
    }

    private void styleSpinner(JSpinner spinner) {
        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        spinner.setBorder(new RoundedBorder(10));
        spinner.setBackground(Color.WHITE);
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tf.setBackground(Color.WHITE);
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorder(new RoundedBorder(10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(33, 150, 243));
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(33, 150, 243));
            }
        });

        Dimension size = new Dimension(100, 35);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
    }

    public DateTime(String email) {
        JFrame frame = new JFrame("Date Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(1000, 600));

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

        // Main white panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JLabel titleLabel = new JLabel("DATE AND TIME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(20, 40, 300, 40);
        mainPanel.add(titleLabel);

        // User Button 
        JButton userButton = new JButton("👤") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // user circle background
                g2d.setColor(Color.WHITE);
                g2d.fillOval(0, 0, getWidth(), getHeight());

                // border circle
                g2d.setColor(Color.LIGHT_GRAY);
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
        userButton.setBounds(1268, 40, 35, 35);
        userButton.setFocusPainted(false);
        userButton.setBorder(BorderFactory.createEmptyBorder());
        userButton.setContentAreaFilled(false);
        userButton.setOpaque(false);
        mainPanel.add(userButton);

        // Date picker label and spinner
        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(550, 220, 200, 40);
        mainPanel.add(dateLabel);

        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setBounds(660, 200, 200, 50);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        mainPanel.add(dateSpinner);

        // Time picker label and spinner
        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setBounds(550, 300, 200, 40);
        mainPanel.add(timeLabel);

        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        JSpinner timeSpinner = new JSpinner(timeModel);
        timeSpinner.setBounds(660, 280, 200, 50);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        mainPanel.add(timeSpinner);

        // Style the spinners
        styleSpinner(dateSpinner);
        styleSpinner(timeSpinner);

        // Save and Edit Buttons
        JButton saveButton = new JButton("SAVE");
        JButton editButton = new JButton("EDIT");
        saveButton.setBounds(600, 400, 100, 35);
        editButton.setBounds(730, 400, 100, 35);
        mainPanel.add(saveButton);
        mainPanel.add(editButton);

        // Style buttons
        styleButton(saveButton);
        styleButton(editButton);

        // Action listener for SAVE button
        saveButton.addActionListener(e -> {
            Date selectedDate = (Date) dateSpinner.getValue();
            Date selectedTime = (Date) timeSpinner.getValue();
            JOptionPane.showMessageDialog(frame, "Saved Date: " + selectedDate + "\nSaved Time: " + selectedTime);
        });

        // Action listener for EDIT button
        editButton.addActionListener(e -> {
            // Logic to edit the selected date and time can be added here
            // For demonstration, we will just show a message
            JOptionPane.showMessageDialog(frame, "You can now edit the date and time.");
        });

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                // Keep left panel fixed width, full height
                leftPanel.setBounds(0, 0, 150, frameHeight);

                // Main panel adjusts width and height but keeps original component positions/sizes
                mainPanel.setBounds(150, 0, frameWidth - 150, frameHeight);

                frame.repaint();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DateTime("demo@example.com"));
    }
}
