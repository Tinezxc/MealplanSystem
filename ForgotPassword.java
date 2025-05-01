package com.mycompany.forgotpassword;

import javax.swing.*;
import java.awt.*;

public class ForgotPassword {
    public ForgotPassword() {
        JFrame frame = new JFrame("Forgot Password");
        frame.setSize(1550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:/Users/THINKPAD/Downloads/bg.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 1550, 800);
        frame.setContentPane(background);
        background.setLayout(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2d.dispose();
            }
        };
        int panelWidth = 400;
        int panelHeight = 350;
        int xPos = (frame.getWidth() - panelWidth) / 2;
        int yPos = (frame.getHeight() - panelHeight) / 2;
        panel.setBounds(xPos, yPos, panelWidth, panelHeight);
        panel.setLayout(null);

        background.add(panel);

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

        frame.setVisible(true);
    }
    
}
