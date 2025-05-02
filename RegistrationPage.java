package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NewPassword {
    public NewPassword(String email) {
        JFrame frame = new JFrame("New Password");
        frame.setUndecorated(true);  // Optional: Remove window title bar for fullscreen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fullscreen setup (optional)
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);
        frame.setLayout(null);

        // Background image setup
        ImageIcon originalIcon = new ImageIcon("D:\\My Documents\\Downloads\\BackGroundUI.png");
        JLabel background = new JLabel();
        background.setLayout(null);
        frame.setContentPane(background);

        // Panel setup (with rounded corners)
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.dispose();
            }
        };

        int panelWidth = 400;
        int panelHeight = 350;
        panel.setSize(panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        // Center the panel whenever the window is resized
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                // Resize background image to fit window
                Image scaledImage = originalIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setBounds(0, 0, frameWidth, frameHeight);

                // Re-center the panel
                panel.setLocation((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2);
            }
        });

        // Title label
        JLabel titleLabel = new JLabel("NEW PASSWORD", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(70, 50, 250, 20);
        panel.add(titleLabel);

        // New password label and field
        JLabel newPasswordLabel = new JLabel("Enter New Password:");
        newPasswordLabel.setForeground(Color.BLACK);
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        newPasswordLabel.setBounds(50, 110, 300, 20);
        panel.add(newPasswordLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(50, 135, 300, 30);
        panel.add(newPasswordField);

        // Confirm password label and field
        JLabel confirmPasswordLabel = new JLabel("Confirm New Password:");
        confirmPasswordLabel.setForeground(Color.BLACK);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmPasswordLabel.setBounds(50, 170, 300, 20);
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(50, 195, 300, 30);
        panel.add(confirmPasswordField);

        // Reset password button
        JButton resetButton = new JButton("Reset Password");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.BLACK);
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setBounds(130, 240, 140, 30);
        panel.add(resetButton);

        // Back to login label
        JLabel backToLogin = new JLabel("Back to Login", SwingConstants.CENTER);
        backToLogin.setForeground(Color.BLUE);
        backToLogin.setFont(new Font("Arial", Font.PLAIN, 10));
        backToLogin.setBounds(150, 270, 100, 20);
        backToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(backToLogin);

        // Make the window visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new NewPassword("user@example.com"); // Example email
    }
}
