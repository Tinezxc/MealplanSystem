
package com.mycompany.userinfo;

import javax.swing.*;
import java.awt.*;

public class UserInfo {
    public UserInfo(String email, String username) {
        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setLayout(null);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 90, 520);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(40));
        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(110, 50));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.BLACK);
            navButton.setBackground(Color.GREEN);
            navButton.setBorderPainted(false);
            navButton.setFont(new Font("Arial", Font.PLAIN, 7));
            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(40));
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 705, 563);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JLabel profilePic = new JLabel(new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pfp.jpg"));
        profilePic.setBounds(50, 30, 80, 80);
        mainPanel.add(profilePic);

        JButton backButton = new JButton("<");
        backButton.setBounds(25, 40, 50, 30);
        backButton.setBackground(Color.WHITE);
        mainPanel.add(backButton);

        JLabel dashboardTitle = new JLabel("User Information");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 16));
        dashboardTitle.setBounds(80, 10, 150, 90);
        mainPanel.add(dashboardTitle);

        JLabel userLabel = new JLabel(username);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(160, 130, 200, 20);
        mainPanel.add(userLabel);

        JLabel emailLabel = new JLabel(email);
        emailLabel.setBounds(160, 150, 200, 20);
        mainPanel.add(emailLabel);

        JTextField fullNameField = createTextField(150, 210, mainPanel);
        JTextField ageField = createTextField(150, 250, mainPanel);
        JComboBox<String> genderBox = createComboBox(new String[]{"Male", "Female"}, 150, 289, mainPanel);
        JTextField birthdateField = createTextField(150, 327, mainPanel);
        JTextField heightField = createTextField(470, 210, mainPanel);
        JTextField weightField = createTextField(470, 250, mainPanel);
        JComboBox<String> goalBox = createComboBox(new String[]{"Lose Weight", "Maintain", "Gain Muscle"}, 470, 289, mainPanel);
        JComboBox<String> dietTypeBox = createComboBox(new String[]{"Vegetarian", "Vegan", "Keto", "Paleo"}, 470, 327, mainPanel);
        JTextField allergiesField = createTextField(150, 365, mainPanel);

        mainPanel.add(createLabel("Full Name:", 50, 210));
        mainPanel.add(createLabel("Age", 50, 250));
        mainPanel.add(createLabel("Gender", 50, 289));
        mainPanel.add(createLabel("Birthdate", 50, 327));
        mainPanel.add(createLabel("Height", 385, 210));
        mainPanel.add(createLabel("Weight", 385, 250));
        mainPanel.add(createLabel("Goal", 385, 289));
        mainPanel.add(createLabel("Diet Type", 385, 327));
        mainPanel.add(createLabel("Allergies:", 50, 365));

        JButton editButton = createButton("EDIT", 400, 100);
        JButton saveButton = createButton("SAVE", 520, 100);
        JButton signOutButton = createButton("SIGN OUT", 520, 450);

        mainPanel.add(editButton);
        mainPanel.add(saveButton);
        mainPanel.add(signOutButton);

        frame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 20);
        return label;
    }

    private JTextField createTextField(int x, int y, JPanel panel) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 180, 25);
        panel.add(field);
        return field;
    }

    private JComboBox<String> createComboBox(String[] options, int x, int y, JPanel panel) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(x, y, 180, 25);
        panel.add(comboBox);
        return comboBox;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        return button;
    }

    public static void main(String[] args) {
        new UserInfo("demo@example.com", "demoUser");
    }
}
