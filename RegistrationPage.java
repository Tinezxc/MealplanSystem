
package com.mycompany.registrationpage;

import javax.swing.*;
import java.awt.*;

public class RegistrationPage {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Registration Page");
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
        panel.setBounds(250, 100, 400, 400); 
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 150)); 

        
        JLabel registrationLabel = new JLabel("Registration");
        registrationLabel.setForeground(Color.GREEN);
        registrationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        registrationLabel.setBounds(140, 10, 200, 30);
        panel.add(registrationLabel);

        
        JLabel nameLabel = new JLabel("Full Name:", JLabel.LEFT);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 50, 250, 20); 
        panel.add(nameLabel);

       
        JTextField nameField = new JTextField();
        nameField.setBounds(50, 70, 300, 30);
        panel.add(nameField);

        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 110, 200, 20);
        panel.add(emailLabel);

        
        JTextField emailField = new JTextField();
        emailField.setBounds(50, 130, 300, 30);
        panel.add(emailField);

       
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 170, 200, 20);
        panel.add(passwordLabel);

       
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 190, 300, 30);
        panel.add(passwordField);

       
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 240, 100, 30);
        registerButton.setBackground(Color.GREEN);
        panel.add(registerButton);

        
        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(100, 280, 200, 30);
        panel.add(loginLabel);
        
        JLabel loginLabel1 = new JLabel(" Login", SwingConstants.CENTER);
        loginLabel1.setForeground(Color.GREEN);
        loginLabel1.setBounds(100, 280, 330, 30);
        panel.add(loginLabel1);
        
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
