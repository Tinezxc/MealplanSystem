
package com.mycompany.forgetpassword;

import javax.swing.*;
import java.awt.*;

public class ForgotPassword {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Forgot Password");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Load and scale the background image to fit the frame
        ImageIcon originalIcon = new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pic.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 900, 600);
        frame.setContentPane(background);
        background.setLayout(null); // To place components manually

        // Forgot Password panel
        JPanel panel = new JPanel();
        panel.setBounds(250, 150, 400, 250);
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 150));

        JLabel forgotLabel = new JLabel("Forgot Password", SwingConstants.CENTER);
        forgotLabel.setForeground(Color.GREEN);
        forgotLabel.setFont(new Font("Arial", Font.BOLD, 20));
        forgotLabel.setBounds(100, 10, 200, 30);
        panel.add(forgotLabel);

        JLabel emailLabel = new JLabel("Enter your email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 60, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 85, 300, 30);
        panel.add(emailField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 130, 100, 30);
        submitButton.setBackground(Color.GREEN);
        panel.add(submitButton);

        // Back to login label
        JLabel backLabel = new JLabel("Remember your password?", SwingConstants.CENTER);
        backLabel.setForeground(Color.WHITE);
        backLabel.setBounds(100, 170, 200, 20);
        panel.add(backLabel);

        JLabel loginLabel = new JLabel("Back to Login", SwingConstants.CENTER);
        loginLabel.setForeground(Color.GREEN);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 12));
        loginLabel.setBounds(100, 190, 200, 20);
        panel.add(loginLabel);
        
        // Back button
        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(50, 210, 50, 20);
        panel.add(backButton);

        background.add(panel);
        frame.setVisible(true);
    }
}