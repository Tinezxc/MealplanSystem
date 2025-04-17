
package com.mycompany.userinfo;

import javax.swing.*;
import java.awt.*;

public class UserInfo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInfo().UIInfo());
    }

    private void UIInfo() {
        JFrame frame = new JFrame("User Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);
        frame.setLayout(null);

        // Left-side color panel with navigation buttons (no borders)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 120, 400);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Vertical layout

        String[] navItems = {"Dashboard", "Meal Plan", "Schedule", "Progress", "Notification"};
        for (String item : navItems) {
        JButton navButton = new JButton(item);
        navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        navButton.setMaximumSize(new Dimension(100, 30));
        navButton.setBorderPainted(false);
        navButton.setFocusPainted(false);
        navButton.setContentAreaFilled(false);
        navButton.setForeground(Color.WHITE); // Optional: white text for visibility
        navButton.setFont(new Font("Arial", Font.BOLD, 12));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Space between buttons
        leftPanel.add(navButton);
}

frame.add(leftPanel);

frame.add(leftPanel);

        // Main background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE); // Set background color
        mainPanel.setBounds(80, 0, 570, 400);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Profile Picture
        JLabel profilePic = new JLabel(new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/Screenshot 2025-03-27 201751.png")); // Placeholder image
        profilePic.setBounds(50, 30, 80, 80);
        mainPanel.add(profilePic);
        
        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(10, 30, 35, 25);
        backButton.setBounds(50, 50, 50, 25);
        mainPanel.add(backButton);

        // Username & Email
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setBounds(150, 30, 200, 20);
        mainPanel.add(usernameLabel);

        JLabel emailLabel = new JLabel("emailmrn@gmail.com");
        emailLabel.setBounds(150, 50, 200, 20);
        mainPanel.add(emailLabel);

        // Form Fields
        mainPanel.add(createLabel("Full Name:", 50, 120));
        mainPanel.add(createTextField(150, 120));

        mainPanel.add(createLabel("Age", 50, 150));
        mainPanel.add(createTextField(150, 150));

        mainPanel.add(createLabel("Gender", 50, 180));
        mainPanel.add(createComboBox(new String[]{"Male", "Female"}, 150, 180));

        mainPanel.add(createLabel("Birthdate", 50, 210));
        mainPanel.add(createTextField(150, 210));

        mainPanel.add(createLabel("Height", 330, 120));
        mainPanel.add(createTextField(400, 120));

        mainPanel.add(createLabel("Weight", 330, 150));
        mainPanel.add(createTextField(400, 150));

        mainPanel.add(createLabel("Goal", 330, 180));
        mainPanel.add(createComboBox(new String[]{"Lose Weight", "Maintain", "Gain Muscle"}, 400, 180));

        mainPanel.add(createLabel("Diet Type", 330, 210));
        mainPanel.add(createComboBox(new String[]{"Vegetarian", "Vegan", "Keto", "Paleo"}, 400, 210));

        mainPanel.add(createLabel("Allergies:", 50, 250));
        JTextField allergiesField = createTextField(150, 250);
        
        mainPanel.add(allergiesField);
        mainPanel.add(createButton("EDIT", 200, 300));
        mainPanel.add(createButton("SAVE", 310, 300));
        mainPanel.add(createButton("Sign Out", 420, 300));

        frame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 25);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 25);
        return textField;
    }

    private JComboBox<String> createComboBox(String[] options, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(x, y, 150, 25);
        return comboBox;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 25);
        return button;
    }
}
