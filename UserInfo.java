package com.mycompany.ui;

import com.mycompany.model.User;
import dao.UserDAO;
import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;

public class UserInfo {
    private final User user;
    private JTextField fullNameField, ageField, birthdateField, heightField, weightField, allergiesField;
    private JComboBox<String> genderBox, goalBox, dietTypeBox;

    private JFrame frame;

    public UserInfo(User user) {
        this.user = user;

        initUI();
        populateFields();
        setFieldsEditable(false);
        frame.setVisible(true);
    }

    private void initUI() {
        frame = new JFrame("User  Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Left navigation panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setBounds(0, 0, 150, frame.getHeight());
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);
        leftPanel.add(Box.createVerticalStrut(200));

        String[] navTitles = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navTitles) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(120, 40));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(new Color(60, 60, 60));
            navButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            navButton.setBorder(new RoundedBorder(10));
            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(70));
        }

        // Main panel with form
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        mainPanel.setBounds(150, 20, frame.getWidth() - 170, frame.getHeight() - 40);
        frame.add(mainPanel);

        // Profile picture circular image
        ImageIcon icon = new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pfp.jpg");
        Image circularImage = createCircularImage(icon.getImage(), 120);
        JLabel profilePic = new JLabel(new ImageIcon(circularImage));
        profilePic.setBounds(100, 30, 120, 120);
        mainPanel.add(profilePic);

        // Title
        JLabel dashboardTitle = new JLabel("User  Information");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardTitle.setBounds(250, 70, 300, 50);
        mainPanel.add(dashboardTitle);

        // Create form fields and labels
        fullNameField = createTextField(250, 350, 300);
        ageField = createTextField(250, 400, 300);
        genderBox = createComboBox(new String[]{"Male", "Female"}, 250, 450, 300);
        birthdateField = createTextField(250, 500, 300);
        heightField = createTextField(800, 350, 300);
        weightField = createTextField(800, 400, 300);
        goalBox = createComboBox(new String[]{"Lose Weight", "Maintain", "Gain Muscle"}, 800, 450, 300);
        dietTypeBox = createComboBox(new String[]{"Vegetarian", "Vegan", "Keto", "Paleo"}, 800, 500, 300);
        allergiesField = createTextField(250, 550, 850);

        // Add labels and fields to main panel
        mainPanel.add(createLabel("Full Name:", 150, 350));
        mainPanel.add(fullNameField);

        mainPanel.add(createLabel("Age:", 150, 400));
        mainPanel.add(ageField);

        mainPanel.add(createLabel("Gender:", 150, 450));
        mainPanel.add(genderBox);

        mainPanel.add(createLabel("Birthdate:", 150, 500));
        mainPanel.add(birthdateField);

        mainPanel.add(createLabel("Height (cm):", 700, 350));
        mainPanel.add(heightField);

        mainPanel.add(createLabel("Weight (kg):", 700, 400));
        mainPanel.add(weightField);

        mainPanel.add(createLabel("Goal:", 700, 450));
        mainPanel.add(goalBox);

        mainPanel.add(createLabel("Diet Type:", 700, 500));
        mainPanel.add(dietTypeBox);

        mainPanel.add(createLabel("Allergies:", 150, 550));
        mainPanel.add(allergiesField);

        // Buttons
        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 14);

        JButton editButton = createButton("✍️ EDIT", 1030, 100, 120, emojiFont);
        JButton saveButton = createButton("💽 SAVE", 1170, 100, 120, emojiFont);
        JButton signOutButton = createButton("🚪 SIGN OUT", 1170, 670, 150, emojiFont);

        for (JButton btn : new JButton[]{editButton, saveButton, signOutButton}) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setBorder(new RoundedBorder(10));
        }

        mainPanel.add(editButton);
        mainPanel.add(saveButton);
        mainPanel.add(signOutButton);

        // Action Listeners
        editButton.addActionListener(e -> setFieldsEditable(true));

        saveButton.addActionListener(e -> {
    try {
        // Basic input validation
        String fullName = fullNameField.getText().trim();
        if (fullName.isEmpty()) throw new IllegalArgumentException("Full Name cannot be empty.");

        int age = Integer.parseInt(ageField.getText().trim());
        if (age <= 0) throw new IllegalArgumentException("Age must be positive.");

        String birthdate = birthdateField.getText().trim();
        if (birthdate.isEmpty()) throw new IllegalArgumentException("Birthdate cannot be empty.");

        double height = Double.parseDouble(heightField.getText().trim());
        if (height <= 0) throw new IllegalArgumentException("Height must be positive.");

        double weight = Double.parseDouble(weightField.getText().trim());
        if (weight <= 0) throw new IllegalArgumentException("Weight must be positive.");

        // Update user model
        user.setFullName(fullName);
        user.setAge(age);
        user.setBirthdate(birthdate);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAllergies(allergiesField.getText().trim());
        user.setGender((String) genderBox.getSelectedItem());
        user.setGoal((String) goalBox.getSelectedItem());
        user.setDietType((String) dietTypeBox.getSelectedItem());

        // Save to database
        try (Connection conn = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);
            userDAO.updateUser(user);
        }

        setFieldsEditable(false);
        JOptionPane.showMessageDialog(frame, "User information saved successfully!");

    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(frame, "Please enter valid numbers for Age, Height, and Weight.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException iae) {
        JOptionPane.showMessageDialog(frame, iae.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});

        signOutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to sign out?", "Confirm Sign Out", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                // Optional: Redirect to login screen or exit app
            }
        });

        // Resize listener for responsive layout
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();
                leftPanel.setBounds(0, 0, 150, frameHeight);
                mainPanel.setBounds(150, 20, frameWidth - 170, frameHeight - 40);
                frame.repaint();
            }
        });
    }

    private void populateFields() {
        fullNameField.setText(user.getFullName());
        ageField.setText(String.valueOf(user.getAge()));
        birthdateField.setText(user.getBirthdate());
        heightField.setText(String.valueOf(user.getHeight()));
        weightField.setText(String.valueOf(user.getWeight()));
        allergiesField.setText(user.getAllergies());
        genderBox.setSelectedItem(user.getGender());
        goalBox.setSelectedItem(user.getGoal());
        dietTypeBox.setSelectedItem(user.getDietType());
    }

    private void setFieldsEditable(boolean editable) {
        fullNameField.setEditable(editable);
        ageField.setEditable(editable);
        birthdateField.setEditable(editable);
        heightField.setEditable(editable);
        weightField.setEditable(editable);
        allergiesField.setEditable(editable);
        genderBox.setEnabled(editable);
        goalBox.setEnabled(editable);
        dietTypeBox.setEnabled(editable);
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

    private JButton createButton(String text, int x, int y, int width, Font font) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, 30);
        button.setFont(font);
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

    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 2, radius + 2, radius + 2, radius + 2);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius + 2, radius + 2, radius + 2, radius + 2);
            return insets;
        }
    }
}
