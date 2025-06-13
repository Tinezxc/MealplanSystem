package com.mycompany.ui;

import database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class LoginPage {

    public LoginPage() {
        JFrame frame = new JFrame("Login Page");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Background image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\bg.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, screenWidth, screenHeight);
        frame.setContentPane(background);
        background.setLayout(null);

        // Main login panel
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

        int panelWidth = 400, panelHeight = 480;
        panel.setBounds((screenWidth - panelWidth) / 2, (screenHeight - panelHeight) / 2, panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        int verticalOffset = 20;

        // Logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\LOGO.jpg");
        Image logoImage = logoIcon.getImage();
        Image circularLogo = createCircularImage(logoImage, 130);
        JLabel logoLabel = new JLabel(new ImageIcon(circularLogo));
        logoLabel.setBounds(120, 10, 150, 150);
        panel.add(logoLabel);

        // Login title
        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 24));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setBounds(0, 160, panelWidth, 40);
        panel.add(loginTitle);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 190 + verticalOffset, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 210 + verticalOffset, 300, 30);
        panel.add(emailField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 250 + verticalOffset, 300, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 270 + verticalOffset, 300, 30);
        passwordField.setEchoChar('•'); 
        panel.add(passwordField);

        // Show password checkbox
        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Arial", Font.PLAIN, 10));
        showPassword.setBounds(47, 300 + verticalOffset, 120, 20);
        showPassword.setBackground(Color.WHITE);
        panel.add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0); 
            } else {
                passwordField.setEchoChar('•'); 
            }
        });

        // Forgot password
        JLabel forgotPassword = new JLabel("Forgot Password?");
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 10));
        forgotPassword.setForeground(Color.GRAY);
        forgotPassword.setBounds(265, 300 + verticalOffset, 120, 20);
        forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(forgotPassword);

        forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ForgotPassword();
            }
        });

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setBounds(150, 350 + verticalOffset, 100, 30);
        panel.add(loginButton);

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Invalid email format.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Authenticate user
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String query = "SELECT * FROM users WHERE email = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, email);
                    stmt.setString(2, password); // NOTE: Plaintext, not secure
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Login successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                        new UserInfo(email); // Pass email
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Database connection failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Register link
        JLabel registerLabel = new JLabel("Don't have an account?");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        registerLabel.setBounds(120, 390 + verticalOffset, 200, 20);
        panel.add(registerLabel);

        JLabel registerLabel1 = new JLabel("Register", SwingConstants.CENTER);
        registerLabel1.setForeground(Color.BLUE);
        registerLabel1.setFont(new Font("Arial", Font.PLAIN, 10));
        registerLabel1.setBounds(150, 390 + verticalOffset, 200, 20);
        registerLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(registerLabel1);

        registerLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new RegistrationPage();
            }
        });

        frame.setVisible(true);
    }

    // Create circular logo
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

    // Email validation
    private boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
