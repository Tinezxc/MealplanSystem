package com.mycompany.registrationpage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class RegistrationPage {

    public RegistrationPage() {
        JFrame frame = new JFrame("Registration Page");
        frame.setSize(1550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALLAN JUSTINE\\Downloads\\BackGroundUI.png");
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
        int panelHeight = 500; // reduced height since we removed buttons
        int xPos = (frame.getWidth() - panelWidth) / 2;
        int yPos = (frame.getHeight() - panelHeight) / 2;
        panel.setBounds(xPos, yPos, panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ALLAN JUSTINE\\Downloads\\LogoUi.jpg");
        Image logoImage = logoIcon.getImage();
        Image circularLogo = createCircularImage(logoImage, 130);
        JLabel logoLabel = new JLabel(new ImageIcon(circularLogo));
        logoLabel.setBounds(120, 4, 150, 150);
        panel.add(logoLabel);
        
        // "Login" text label under logo
        JLabel loginTitle = new JLabel("Register", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 20));
        loginTitle.setForeground(Color.BLACK);
        loginTitle.setBounds(150, 140, 100, 40);
        panel.add(loginTitle);

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(50, 170, 300, 20);
        panel.add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(50, 190, 300, 30);
        panel.add(fullNameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 230, 300, 20);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 250, 300, 30);
        panel.add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 290, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 310, 300, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 350, 300, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 370, 300, 30);
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLACK);
        registerButton.setBounds(150, 420, 100, 30);
        panel.add(registerButton);

        JLabel alreadyAccountLabel = new JLabel("Already have an account?");
        alreadyAccountLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        alreadyAccountLabel.setBounds(125, 460, 150, 20);
        panel.add(alreadyAccountLabel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        loginLabel.setBounds(250, 460, 50, 20);
        panel.add(loginLabel);

        frame.setVisible(true);
    }

    private Image createCircularImage(Image image, int diameter) {
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2d.setClip(clip);
        g2d.drawImage(image, 0, 0, diameter, diameter, null);
        g2d.dispose();
        return circularImage;
    }

    public static void main(String[] args) {
        new RegistrationPage();
    }
}
