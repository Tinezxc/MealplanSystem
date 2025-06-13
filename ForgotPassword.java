package com.mycompany.ui;

import database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class ForgotPassword {
    public ForgotPassword() {
        JFrame frame = new JFrame("Forgot Password");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\bg.png");
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
        int panelHeight = 300;
        panel.setBounds((screenWidth - panelWidth) / 2, (screenHeight - panelHeight) / 2, panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        JLabel titleLabel = new JLabel("FORGOT PASSWORD", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(70, 50, 250, 20);
        panel.add(titleLabel);

        JLabel emailLabel = new JLabel("Enter your Email:");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        emailLabel.setBounds(50, 110, 300, 20);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 135, 300, 30);
        panel.add(emailField);

        JButton resetButton = new JButton("Send Code");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.BLACK);
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.setBounds(130, 180, 140, 30);
        panel.add(resetButton);

        JLabel backToLogin = new JLabel("Back to Login", SwingConstants.CENTER);
        backToLogin.setForeground(Color.BLUE);
        backToLogin.setFont(new Font("Arial", Font.PLAIN, 10));
        backToLogin.setBounds(150, 220, 100, 20);
        backToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(backToLogin);

        resetButton.addActionListener(e -> {
            String email = emailField.getText().trim().toLowerCase();

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your email.");
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM users WHERE LOWER(email) = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(frame, "No account found with that email.");
                    return;
                }

                // Generate a 6-digit code
                String code = String.format("%06d", new Random().nextInt(999999));

                // Send email
                String subject = "Password Reset Code";
                String message = "Your verification code is: " + code;

                String senderEmail = "neverdd.11@gmail.com";
                String appPassword = "mfmtnysykkbviqup";

                EmailService emailService = new EmailService(senderEmail, appPassword);
                emailService.sendEmail(email, subject, message);

                JOptionPane.showMessageDialog(frame, "Verification code sent to your email.");

                // Move to verification screen
                new EmailVerification(email, code);
                frame.dispose();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to send email: " + ex.getMessage());
            }
        });

        backToLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginPage();
                frame.dispose();
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();
                Image scaledImage1 = originalIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage1));
                background.setBounds(0, 0, frameWidth, frameHeight);
                panel.setLocation((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ForgotPassword::new);
    }
}
