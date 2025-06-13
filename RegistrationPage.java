package com.mycompany.ui;

import database.DatabaseConnection;
import com.mycompany.model.User;
import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationPage {

    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public RegistrationPage() {
        JFrame frame = new JFrame("Registration Page");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(true);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\bg.png");
        JLabel background = new JLabel();
        background.setLayout(null);
        frame.setContentPane(background);

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
        int panelHeight = 500;
        int xPos = (screenWidth - panelWidth) / 2;
        int yPos = (screenHeight - panelHeight) / 2;
        panel.setBounds(xPos, yPos, panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        // Circular logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\LOGO.jpg");
        Image logoImage = logoIcon.getImage();
        Image circularLogo = createCircularImage(logoImage, 130);
        JLabel logoLabel = new JLabel(new ImageIcon(circularLogo));
        logoLabel.setBounds(120, 4, 150, 150);
        panel.add(logoLabel);

        JLabel registerTitle = new JLabel("Register", SwingConstants.CENTER);
        registerTitle.setFont(new Font("Arial", Font.BOLD, 20));
        registerTitle.setForeground(Color.BLACK);
        registerTitle.setBounds(150, 140, 100, 40);
        panel.add(registerTitle);

        // Add input fields
        addLabelAndField(panel, "Full Name:", 170, 190, false);
        addLabelAndField(panel, "Username:", 230, 250, false);
        addLabelAndField(panel, "Email:", 290, 310, false);
        addLabelAndField(panel, "Password:", 350, 370, true);

        JButton registerButton = new JButton("Next");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLACK);
        registerButton.setBounds(150, 420, 100, 30);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            String fullName = fullNameField.getText().trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid email address.", "Invalid Email", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(frame, "Password must be at least 6 characters long.", "Weak Password", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                UserDAO userDAO = new UserDAO(conn);

                String checkQuery = "SELECT * FROM users WHERE email = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Email already registered.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = new User(email, fullName);
                user.setFullName(fullName);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);

                boolean success = userDAO.saveUser(user);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Registration successful! Proceeding to next form.");
                    frame.dispose();
                    new FillUpForm(user); // Replace with LoginPage() if you prefer
                } else {
                    JOptionPane.showMessageDialog(frame, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel alreadyAccountLabel = new JLabel("Already have an account?");
        alreadyAccountLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        alreadyAccountLabel.setBounds(125, 460, 150, 20);
        panel.add(alreadyAccountLabel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        loginLabel.setBounds(250, 460, 50, 20);
        panel.add(loginLabel);

        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginPage();
                frame.dispose();
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();
                Image scaledImage = originalIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setBounds(0, 0, frameWidth, frameHeight);
                centerPanel(panel, frame, panelWidth, panelHeight);
            }
        });

        frame.setVisible(true);
    }

    private void centerPanel(JPanel panel, JFrame frame, int panelWidth, int panelHeight) {
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        panel.setLocation((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2);
    }

    private void addLabelAndField(JPanel panel, String labelText, int labelY, int fieldY, boolean isPassword) {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, labelY, 300, 20);
        panel.add(label);

        JTextField field;
        if (isPassword) {
            passwordField = new JPasswordField();
            passwordField.setBounds(50, fieldY, 300, 30);
            panel.add(passwordField);
        } else {
            field = new JTextField();
            field.setBounds(50, fieldY, 300, 30);
            panel.add(field);

            switch (labelText) {
                case "Full Name:" -> fullNameField = field;
                case "Username:" -> usernameField = field;
                case "Email:" -> emailField = field;
            }
        }
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
        SwingUtilities.invokeLater(RegistrationPage::new);
    }
}
