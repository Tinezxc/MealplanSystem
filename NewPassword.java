package com.mycompany.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewPassword {
    public NewPassword(String email) {
        JFrame frame = new JFrame("New Password");
        frame.setSize(1550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\bg.png");
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

        JLabel titleLabel = new JLabel("NEW PASSWORD", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(70, 50, 250, 20);
        panel.add(titleLabel);

        JLabel newPasswordLabel = new JLabel("Enter New Password:");
        newPasswordLabel.setForeground(Color.BLACK);
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        newPasswordLabel.setBounds(50, 110, 300, 20);
        panel.add(newPasswordLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(50, 135, 300, 30);
        panel.add(newPasswordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm New Password:");
        confirmPasswordLabel.setForeground(Color.BLACK);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmPasswordLabel.setBounds(50, 170, 300, 20);
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(50, 195, 300, 30);
        panel.add(confirmPasswordField);

        JButton resetButton = new JButton("Reset Password");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.BLACK);
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setBounds(130, 240, 140, 30);
        panel.add(resetButton);

        JLabel backToLogin = new JLabel("Back to Login", SwingConstants.CENTER);
        backToLogin.setForeground(Color.BLUE);
        backToLogin.setFont(new Font("Arial", Font.PLAIN, 10));
        backToLogin.setBounds(150, 270, 100, 20);
        backToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(backToLogin);

        // Password reset logic
        resetButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.");
                return;
            }

            // TODO: Replace this simulated update with real DB update logic
            System.out.println("Password for " + email + " updated to: " + newPassword);
            JOptionPane.showMessageDialog(frame, "Password updated successfully!");

            new LoginPage(); // Go back to login screen
            frame.dispose();
        });

        backToLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginPage();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

}
