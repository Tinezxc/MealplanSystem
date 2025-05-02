package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ForgotPassword {
    public ForgotPassword() {
        JFrame frame = new JFrame("Forgot Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        frame.setLayout(null);

        // Background image setup
        ImageIcon originalIcon = new ImageIcon("D:\\My Documents\\Downloads\\BackgroundUI.png");
        JLabel background = new JLabel();
        background.setLayout(null);
        frame.setContentPane(background);

        // Panel setup
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

        // UI Components
        JLabel titleLabel = new JLabel("FORGOT PASSWORD", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(70, 50, 250, 20);
        panel.add(titleLabel);

        JLabel emailLabel = new JLabel("Enter your Email:");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        emailLabel.setBounds(50, 110, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 135, 300, 30);
        panel.add(emailField);

        JButton resetButton = new JButton("Reset Password");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.BLACK);
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setBounds(130, 180, 140, 30);
        panel.add(resetButton);

        JLabel backToLogin = new JLabel("Back to Login", SwingConstants.CENTER);
        backToLogin.setForeground(Color.BLUE);
        backToLogin.setFont(new Font("Arial", Font.PLAIN, 10));
        backToLogin.setBounds(150, 220, 100, 20);
        backToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(backToLogin);

        // Add resizing logic
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                // Update background image
                Image scaledImage = originalIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setBounds(0, 0, frameWidth, frameHeight);

                // Center the panel
                panel.setLocation((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ForgotPassword::new);
    }
}
