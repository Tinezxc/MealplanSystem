package com.mycompany.userinfo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class UserInfo {
    public UserInfo(String email, String demoUser) {
        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        // Add space at the top before navigation buttons
        leftPanel.add(Box.createVerticalStrut(150)); // Adjust height as needed

        // Navigation Buttons
        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(120, 200));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.BLACK);
            navButton.setBackground(Color.GRAY);
            navButton.setBorderPainted(false);
            navButton.setFont(new Font("Arial", Font.PLAIN, 10));
            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(90));
        }

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JLabel profilePic = new JLabel(new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pfp.jpg"));
        profilePic.setBounds(100, 30, 120, 120);
        mainPanel.add(profilePic);

        JLabel dashboardTitle = new JLabel("User Information");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardTitle.setBounds(150, 70, 300, 50);
        mainPanel.add(dashboardTitle);

        JLabel userLabel = new JLabel(demoUser);
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userLabel.setBounds(250, 220, 400, 30);
        mainPanel.add(userLabel);

        JLabel emailLabel = new JLabel(email);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setBounds(250, 250, 400, 30);
        mainPanel.add(emailLabel);

        // Fields
        JTextField fullNameField = createTextField(250, 350, 300);
        JTextField ageField = createTextField(250, 400, 300);
        JComboBox<String> genderBox = createComboBox(new String[]{"Male", "Female"}, 250, 450, 300);
        JTextField birthdateField = createTextField(250, 500, 300);
        JTextField heightField = createTextField(800, 350, 300);
        JTextField weightField = createTextField(800, 400, 300);
        JComboBox<String> goalBox = createComboBox(new String[]{"Lose Weight", "Maintain", "Gain Muscle"}, 800, 450, 300);
        JComboBox<String> dietTypeBox = createComboBox(new String[]{"Vegetarian", "Vegan", "Keto", "Paleo"}, 800, 500, 300);
        JTextField allergiesField = createTextField(250, 550, 850);

        mainPanel.add(createLabel("Full Name:", 150, 350));
        mainPanel.add(fullNameField);
        mainPanel.add(createLabel("Age", 150, 400));
        mainPanel.add(ageField);
        mainPanel.add(createLabel("Gender", 150, 450));
        mainPanel.add(genderBox);
        mainPanel.add(createLabel("Birthdate", 150, 500));
        mainPanel.add(birthdateField);
        mainPanel.add(createLabel("Height", 700, 350));
        mainPanel.add(heightField);
        mainPanel.add(createLabel("Weight", 700, 400));
        mainPanel.add(weightField);
        mainPanel.add(createLabel("Goal", 700, 450));
        mainPanel.add(goalBox);
        mainPanel.add(createLabel("Diet Type", 700, 500));
        mainPanel.add(dietTypeBox);
        mainPanel.add(createLabel("Allergies:", 150, 550));
        mainPanel.add(allergiesField);

        // Disable editability
        fullNameField.setEditable(false);
        ageField.setEditable(false);
        birthdateField.setEditable(false);
        heightField.setEditable(false);
        weightField.setEditable(false);
        allergiesField.setEditable(false);
        genderBox.setEnabled(false);
        goalBox.setEnabled(false);
        dietTypeBox.setEnabled(false);

        JButton editButton = createButton("EDIT", 1000, 100, 120);
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(Color.BLACK);
        JButton saveButton = createButton("SAVE", 1150, 100, 120);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.BLACK);
        JButton signOutButton = createButton("SIGN OUT", 1150, 700, 120);
        signOutButton.setForeground(Color.WHITE);
        signOutButton.setBackground(Color.BLACK);

        mainPanel.add(editButton);
        mainPanel.add(saveButton);
        mainPanel.add(signOutButton);

        frame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private JTextField createTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 30);
        return textField;
    }

    private JComboBox<String> createComboBox(String[] options, int x, int y, int width) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(x, y, width, 30);
        return comboBox;
    }

    private JButton createButton(String text, int x, int y, int width) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, 30);
        return button;
    }

    private Image createCircularImage(Image image, int diameter) {
        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = masked.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(image.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH), 0, 0, null);
        g2.dispose();
        return masked;
    }

    public static void main(String[] args) {
        new UserInfo("demo@example.com", "demoUser");
    }
}
