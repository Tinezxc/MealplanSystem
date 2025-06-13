package com.mycompany.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class EmailVerification {
    private final String sentCode;
    private final String email;  // ✅ Store user's actual email

    public EmailVerification(String email, String sentCode) {
        this.email = email;      // ✅ Assign email
        this.sentCode = sentCode;

        JFrame frame = new JFrame("Email Verification");
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
        int panelHeight = 350;
        panel.setBounds((screenWidth - panelWidth) / 2, (screenHeight - panelHeight) / 2, panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        JLabel titleLabel = new JLabel("EMAIL VERIFICATION", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(70, 50, 250, 20);
        panel.add(titleLabel);

        JLabel codeLabel = new JLabel("Enter the 6-digit code sent to your email:");
        codeLabel.setForeground(Color.BLACK);
        codeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        codeLabel.setBounds(50, 110, 300, 20);
        panel.add(codeLabel);

        JTextField codeField = new JTextField();
        codeField.setBounds(50, 135, 300, 30);
        panel.add(codeField);

        JButton verifyButton = new JButton("Verify");
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setBackground(Color.BLACK);
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 12));
        verifyButton.setBounds(130, 180, 140, 30);
        panel.add(verifyButton);

        verifyButton.addActionListener(e -> {
            String enteredCode = codeField.getText().trim();
            if (enteredCode.equals(sentCode)) {
                JOptionPane.showMessageDialog(frame, "Verification successful! You can now change your password.");
                new NewPassword(email);  // ✅ Pass correct email
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect verification code.");
            }
        });

        frame.setVisible(true);
    }

    // Method to create a circular image (used for the logo)
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
}
