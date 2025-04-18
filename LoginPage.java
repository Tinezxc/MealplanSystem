
package com.mycompany.loginpage;


import javax.swing.*;
import java.awt.*;

public class LoginPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
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
        background.setLayout(null); 

        // Login panel
        JPanel panel = new JPanel();
        panel.setBounds(250, 150, 400, 300);
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 150));

        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setForeground(Color.GREEN);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setBounds(150, 10, 100, 30);
        panel.add(loginLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 50, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 70, 300, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 110, 300, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 130, 300, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 30);
        loginButton.setBackground(Color.GREEN);
        panel.add(loginButton);

        // Register Labels
        JLabel registerLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setBounds(100, 220, 200, 20);
        panel.add(registerLabel);

        JLabel registerLabel1 = new JLabel("Register", SwingConstants.CENTER);
        registerLabel1.setForeground(Color.GREEN);
        registerLabel1.setFont(new Font("Arial", Font.BOLD, 12));
        registerLabel1.setBounds(100, 240, 200, 20);
        panel.add(registerLabel1);

        background.add(panel); // Add panel to background
        frame.setVisible(true);
    }
}