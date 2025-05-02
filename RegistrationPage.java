package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class RegistrationPage {

    public RegistrationPage() {
        JFrame frame = new JFrame("Registration Page");
        frame.setUndecorated(true); // Removes title bar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fullscreen
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);
        frame.setLayout(null);

        // Background image setup
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALLAN JUSTINE\\Downloads\\BackGroundUI.png");
        JLabel background = new JLabel();
        background.setLayout(null);
        frame.setContentPane(background);

        // Panel setup
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.dispose();
            }
        };

        int panelWidth = 400;
        int panelHeight = 500;
        panel.setSize(panelWidth, panelHeight);
        panel.setLayout(null);
        background.add(panel);

        // Center panel initially
        SwingUtilities.invokeLater(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            panel.setLocation((screenSize.width - panelWidth) / 2, (screenSize.height - panelHeight) / 2);
        });

        // Circular logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ALLAN JUSTINE\\Downloads\\LogoUi.jpg");
        Image logoImage = logoIcon.getImage();
        Image circularLogo = createCircularImage(logoImage, 130);
        JLabel logoLabel = new JLabel(new ImageIcon(circularLogo));
        logoLabel.setBounds(120, 4, 150, 150);
        panel.add(logoLabel);

        JLabel loginTitle = new JLabel("Register", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 20));
        loginTitle.setForeground(Color.BLACK);
        loginTitle.setBounds(150, 140, 100, 40);
        panel.add(loginTitle);

        addLabelAndField(panel, "Full Name:", 170, 190);
        addLabelAndField(panel, "Username:", 230, 250);
        addLabelAndField(panel, "Email:", 290, 310);
        addLabelAndField(panel, "Password:", 350, 370, true);

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

        // Dynamic resize behavior
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                // Resize background
                Image scaledImage = originalIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setBounds(0, 0, frameWidth, frameHeight);

                // Recenter the panel
                panel.setLocation((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2);
            }
        });

        frame.setVisible(true);
    }

    private void addLabelAndField(JPanel panel, String labelText, int labelY, int fieldY) {
        addLabelAndField(panel, labelText, labelY, fieldY, false);
    }

    private void addLabelAndField(JPanel panel, String labelText, int labelY, int fieldY, boolean isPassword) {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, labelY, 300, 20);
        panel.add(label);

        JTextField field = isPassword ? new JPasswordField() : new JTextField();
        field.setBounds(50, fieldY, 300, 30);
        panel.add(field);
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
