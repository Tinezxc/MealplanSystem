package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class LoginPage {

    public LoginPage () {
        JFrame frame = new JFrame("Login Page");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true); // Optional: remove title bar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        ImageIcon originalIcon = new ImageIcon("D:\\My Documents\\Downloads\\BackGroundUI.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, screenWidth, screenHeight);
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
        int panelHeight = 480;
        int xPos = (screenWidth - panelWidth) / 2;
        int yPos = (screenHeight - panelHeight) / 2;
        panel.setBounds(xPos, yPos, panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        int verticalOffset = 20;

        // Logo (fallback if missing)
        JLabel logoLabel;
        try {
            ImageIcon logoIcon = new ImageIcon("D:\\My Documents\\Downloads\\LogoUi.jpg");
            Image logoImage = logoIcon.getImage();
            Image circularLogo = createCircularImage(logoImage, 130);
            logoLabel = new JLabel(new ImageIcon(circularLogo));
        } catch (Exception e) {
            logoLabel = new JLabel("No Logo", SwingConstants.CENTER);
            logoLabel.setFont(new Font("Arial", Font.BOLD, 18));
            logoLabel.setForeground(Color.GRAY);
            logoLabel.setOpaque(true);
            logoLabel.setBackground(Color.LIGHT_GRAY);
        }
        logoLabel.setBounds(120, 10, 150, 150);
        panel.add(logoLabel);

        // Centered "Login"
        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 24));
        loginTitle.setForeground(Color.BLACK);
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setBounds(0, 160, panelWidth, 40);
        panel.add(loginTitle);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setBounds(50, 190 + verticalOffset, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 210 + verticalOffset, 300, 30);
        panel.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(50, 250 + verticalOffset, 300, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 270 + verticalOffset, 300, 30);
        panel.add(passwordField);

        // Remember Me
        JCheckBox rememberMe = new JCheckBox("Remember Me");
        rememberMe.setForeground(Color.GRAY);
        rememberMe.setFont(new Font("Arial", Font.PLAIN, 10));
        rememberMe.setBounds(47, 300 + verticalOffset, 120, 20);
        rememberMe.setBackground(Color.WHITE);
        panel.add(rememberMe);

        // Forgot Password
        JLabel forgotPassword = new JLabel("Forgot Password?");
        forgotPassword.setForeground(Color.GRAY);
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 10));
        forgotPassword.setBounds(265, 300 + verticalOffset, 120, 20);
        forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(forgotPassword);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setBounds(150, 340 + verticalOffset, 100, 30);
        panel.add(loginButton);

        // Register
        JLabel registerLabel = new JLabel("Don't have an account?");
        registerLabel.setForeground(Color.BLACK);
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        registerLabel.setBounds(120, 380 + verticalOffset, 200, 20);
        panel.add(registerLabel);

        JLabel registerLabel1 = new JLabel("Register", SwingConstants.CENTER);
        registerLabel1.setForeground(Color.BLUE);
        registerLabel1.setFont(new Font("Arial", Font.PLAIN, 10));
        registerLabel1.setBounds(155, 380 + verticalOffset, 200, 20);
        registerLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(registerLabel1);

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
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
