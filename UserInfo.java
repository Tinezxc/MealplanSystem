package com.mycompany.ui;

import dao.UserInfoDAO;
import com.mycompany.model.User;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserInfo {
    private JTextField fullNameField, ageField, birthdateField, heightField, weightField, allergiesField;
    private JComboBox<String> genderBox, goalBox, dietTypeBox;
    private User currentUser;
    private UserInfoDAO userInfoDAO;

    public UserInfo(String email) {
        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);
        leftPanel.add(Box.createVerticalStrut(200));

        String[] navtitle = {"DASHBOARD", "MEAL PLAN", "SCHEDULE", "PROGRESS", "NOTIFICATION"};
        for (String title : navtitle) {
            JButton navButton = new JButton(title);
            navButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navButton.setMaximumSize(new Dimension(120, 40));
            navButton.setFocusPainted(false);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(new Color(60, 60, 60));
            navButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            navButton.setBorder(new RoundedBorder(10));

            navButton.addActionListener(e -> {
                frame.dispose();
                switch (title) {
                    case "DASHBOARD":
                        new Dashboard(email);
                        break;
                    case "MEAL PLAN":
                        new MealPlan(email);
                        break;
                    case "SCHEDULE":
                        new Schedule(email);
                        break;
                    case "PROGRESS":
                        new ProgressTracker(email);
                        break;
                    case "NOTIFICATION":
                        new Notification(email);
                        break;
                }
            });

            leftPanel.add(navButton);
            leftPanel.add(Box.createVerticalStrut(70));
        }

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

        ImageIcon icon;
        try {
            icon = new ImageIcon("C:/Users/THINKPAD/Pictures/Screenshots/pfp.jpg");
            if (icon.getIconWidth() <= 0) throw new Exception("Image not loaded");
        } catch (Exception ex) {
            icon = new ImageIcon(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB));
        }

        Image circularImage = createCircularImage(icon.getImage(), 120);
        JLabel profilePic = new JLabel(new ImageIcon(circularImage));
        profilePic.setBounds(100, 30, 120, 120);
        mainPanel.add(profilePic);

        JLabel dashboardTitle = new JLabel("User Information");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 30));
        dashboardTitle.setBounds(250, 70, 300, 50);
        mainPanel.add(dashboardTitle);

        fullNameField = createTextField(250, 350, 300);
        ageField = createTextField(250, 400, 300);
        genderBox = createComboBox(new String[]{"Male", "Female", "Walmart Bag", "NPC", "Giga Chad", "Sigma", "Non-Binary", "Bisexual"}, 250, 450, 300);
        birthdateField = createTextField(250, 500, 300);
        heightField = createTextField(800, 350, 300);
        weightField = createTextField(800, 400, 300);
        goalBox = createComboBox(new String[]{"Lose Weight", "Maintain", "Gain Muscle"}, 800, 450, 300);
        dietTypeBox = createComboBox(new String[]{"Vegetarian", "Vegan", "Keto", "Paleo"}, 800, 500, 300);
        allergiesField = createTextField(250, 550, 850);

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

        fullNameField.setEditable(false);
        ageField.setEditable(false);
        birthdateField.setEditable(false);
        heightField.setEditable(false);
        weightField.setEditable(false);
        allergiesField.setEditable(false);
        genderBox.setEnabled(false);
        goalBox.setEnabled(false);
        dietTypeBox.setEnabled(false);

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

        editButton.addActionListener(e -> setFieldsEditable(true));

        saveButton.addActionListener(e -> {
            try {
                updateUserFromFields();
                userInfoDAO.saveUserInfo(currentUser);
                setFieldsEditable(false);
                JOptionPane.showMessageDialog(frame, "User information saved successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to save: " + ex.getMessage());
            }
        });

        signOutButton.addActionListener(e -> frame.dispose());

        // Load User Info
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prototype", "root", "");
            userInfoDAO = new UserInfoDAO(conn);
            currentUser = userInfoDAO.getUserInfoByEmail(email);
            if (currentUser != null) {
                populateUserInfo();
            } else {
                JOptionPane.showMessageDialog(frame, "User not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
        }

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

    private void populateUserInfo() {
        fullNameField.setText(currentUser.getFullName());
        ageField.setText(String.valueOf(currentUser.getAge()));
        genderBox.setSelectedItem(currentUser.getGender());
        birthdateField.setText(currentUser.getBirthdate());
        heightField.setText(String.valueOf(currentUser.getHeight()));
        weightField.setText(String.valueOf(currentUser.getWeight()));
        goalBox.setSelectedItem(currentUser.getGoal());
        dietTypeBox.setSelectedItem(currentUser.getDietType());
        allergiesField.setText(currentUser.getAllergies());
    }

    private void setFieldsEditable(boolean editable) {
        fullNameField.setEditable(false); // Read-only in this UI
        ageField.setEditable(editable);
        birthdateField.setEditable(editable);
        heightField.setEditable(editable);
        weightField.setEditable(editable);
        allergiesField.setEditable(editable);
        genderBox.setEnabled(editable);
        goalBox.setEnabled(editable);
        dietTypeBox.setEnabled(editable);
    }

    private void updateUserFromFields() {
        currentUser.setAge(Integer.parseInt(ageField.getText()));
        currentUser.setGender((String) genderBox.getSelectedItem());
        currentUser.setBirthdate(birthdateField.getText());
        currentUser.setHeight(Double.parseDouble(heightField.getText()));
        currentUser.setWeight(Double.parseDouble(weightField.getText()));
        currentUser.setGoal((String) goalBox.getSelectedItem());
        currentUser.setDietType((String) dietTypeBox.getSelectedItem());
        currentUser.setAllergies(allergiesField.getText());
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
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius + 1, radius + 1, radius + 1, radius + 1);
            return insets;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInfo("demo@example.com"));
    }
}
