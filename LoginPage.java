
package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;


public class LoginPage {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
    public LoginPage() {


        JFrame frame = new JFrame("Login Page");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pic.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 900, 600);
        frame.setContentPane(background);
        background.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(250, 150, 400, 350);
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 150));

        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setForeground(Color.GREEN);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setBounds(150, 10, 100, 30);
        panel.add(loginLabel);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 50, 250, 20);
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(50, 70, 300, 30);
        panel.add(userField);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 110, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 130, 300, 30);
        panel.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 170, 300, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 190, 300, 30);
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 240, 100, 30);
        loginButton.setBackground(Color.GREEN);
        panel.add(loginButton);
        
             

        // Forgot Password
        JLabel forgotPass = new JLabel("Forgot Password?", SwingConstants.CENTER);
        forgotPass.setForeground(Color.GREEN);
        forgotPass.setFont(new Font("Arial", Font.PLAIN, 10));
        forgotPass.setBounds(240, 225, 130, 20);
        panel.add(forgotPass);


        // Register Option
        JLabel registerLabel = new JLabel("Don't have an account?");
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setBounds(100, 280, 200, 20);
        panel.add(registerLabel);

        JLabel registerLabel1 = new JLabel("Register", SwingConstants.CENTER);
        registerLabel1.setForeground(Color.GREEN);
        registerLabel1.setBounds(100, 280, 320, 20);
        panel.add(registerLabel1);


        background.add(panel);
        frame.setVisible(true);
    }

}
