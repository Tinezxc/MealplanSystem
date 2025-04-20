
package com.mycompany.registrationpage;

import javax.swing.*;
import java.awt.*;


public class RegistrationPage {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationPage::new);
    }
    public RegistrationPage() {
        JFrame frame = new JFrame("Login Page");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Load and scale background
        ImageIcon originalIcon = new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pic.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 900, 600);
        frame.setContentPane(background);
        background.setLayout(null);

        // Registration Panel
        JPanel panel = new JPanel();
        panel.setBounds(250, 100, 400, 400);
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 150));
        background.add(panel);

        JLabel registrationLabel = new JLabel("Registration");
        registrationLabel.setForeground(Color.GREEN);
        registrationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        registrationLabel.setBounds(140, 10, 200, 30);
        panel.add(registrationLabel);

        // Full Name
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 50, 250, 20);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(50, 70, 300, 30);
        panel.add(nameField);

        // Username (moved down below Full Name)
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 110, 250, 20);
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(50, 130, 300, 30);
        panel.add(userField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 170, 200, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 190, 300, 30);
        panel.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 230, 200, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 250, 300, 30);
        panel.add(passwordField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 300, 100, 30);
        registerButton.setBackground(Color.GREEN);
        panel.add(registerButton);

        // Login Link
        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(100, 340, 200, 30);
        panel.add(loginLabel);

        JLabel loginLabel1 = new JLabel(" Login");
        loginLabel1.setForeground(Color.GREEN);
        loginLabel1.setBounds(250, 340, 100, 30);
        loginLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(loginLabel1);

        background.add(panel);
        frame.setVisible(true);
    }
}
