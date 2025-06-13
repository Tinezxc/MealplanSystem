package com.mycompany.ui;

import com.mycompany.model.User;
import dao.UserInfoDAO;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;

public class FillUpForm {
    private final int PANEL_WIDTH = 520;
    private final int PANEL_HEIGHT = 720;

    public FillUpForm(User user) {
        JFrame frame = new JFrame("Fill Up Form");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\bg.png");
        JLabel background = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new GridBagLayout());
        frame.setContentPane(background);

        JPanel formPanel = createStyledFormPanel();
        GridBagConstraints gbc = createGbc();

        // Logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\LOGO.jpg");
        JLabel logoLabel = new JLabel(new ImageIcon(createCircularImage(logoIcon.getImage(), 130)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(logoLabel, gbc);

        // Title
        JLabel titleLabel = new JLabel("Additional Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 20, 20);
        formPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(8, 20, 8, 20);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Form Fields
        JTextField ageField = new JTextField();
        JTextField heightField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField birthdateField = new JTextField();
        JTextField allergiesField = new JTextField();

        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JComboBox<String> goalCombo = new JComboBox<>(new String[]{"Lose Weight", "Gain Muscle", "Maintain"});
        JComboBox<String> dietCombo = new JComboBox<>(new String[]{
            "Balanced", "Low Carb", "High Protein", "Vegetarian", "Vegan", "Keto", "Paleo", "Other"
        });

        // Prefill if values exist
        ageField.setText(user.getAge() > 0 ? String.valueOf(user.getAge()) : "");
        heightField.setText(user.getHeight() > 0 ? String.valueOf(user.getHeight()) : "");
        weightField.setText(user.getWeight() > 0 ? String.valueOf(user.getWeight()) : "");
        if (user.getBirthdate() != null) birthdateField.setText(user.getBirthdate());
        if (user.getAllergies() != null) allergiesField.setText(user.getAllergies());
        if (user.getGender() != null) genderCombo.setSelectedItem(user.getGender());
        if (user.getGoal() != null) goalCombo.setSelectedItem(user.getGoal());
        if (user.getDietType() != null) dietCombo.setSelectedItem(user.getDietType());

        // Age & Gender
        addField(formPanel, gbc, "Age:", ageField, 2, 0);
        addField(formPanel, gbc, "Gender:", genderCombo, 2, 1);

        // Birthdate
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Birthdate (YYYY-MM-DD):"), gbc);
        gbc.gridy = 5;
        formPanel.add(birthdateField, gbc);
        birthdateField.setPreferredSize(new Dimension(PANEL_WIDTH - 80, 30));
        gbc.gridwidth = 1;

        // Height & Weight
        addField(formPanel, gbc, "Height (cm):", heightField, 6, 0);
        addField(formPanel, gbc, "Weight (kg):", weightField, 6, 1);

        // Goal & Diet Type
        addField(formPanel, gbc, "Goal:", goalCombo, 8, 0);
        addField(formPanel, gbc, "Diet Type:", dietCombo, 8, 1);

        // Allergies
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Allergies (if any):"), gbc);
        gbc.gridy = 11;
        formPanel.add(allergiesField, gbc);
        allergiesField.setPreferredSize(new Dimension(PANEL_WIDTH - 80, 30));

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLACK);
        submitButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridy = 12;
        gbc.insets = new Insets(20, 20, 20, 20);
        formPanel.add(submitButton, gbc);

        // Action Listener
        submitButton.addActionListener(e -> {
            try {
                String ageText = ageField.getText().trim();
                String heightText = heightField.getText().trim();
                String weightText = weightField.getText().trim();

                if (ageText.isEmpty() || heightText.isEmpty() || weightText.isEmpty()) {
                    throw new NumberFormatException("One or more fields are empty.");
                }

                user.setAge(Integer.parseInt(ageText));
                user.setHeight(Double.parseDouble(heightText.replace(",", ".")));
                user.setWeight(Double.parseDouble(weightText.replace(",", ".")));
                user.setBirthdate(birthdateField.getText().trim());
                user.setGender((String) genderCombo.getSelectedItem());
                user.setGoal((String) goalCombo.getSelectedItem());
                user.setDietType((String) dietCombo.getSelectedItem());
                user.setAllergies(allergiesField.getText().trim());

                // Get a connection from your DatabaseConnection helper class
                Connection conn = DatabaseConnection.getConnection();

                // Create DAO with connection
                UserInfoDAO userInfoDAO = new UserInfoDAO(conn);

                // Save user info
                userInfoDAO.saveUserInfo(user);

                JOptionPane.showMessageDialog(frame,
                        "Information submitted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                new LoginPage(); // Or navigate to the next page
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid numeric values for Age, Height, and Weight.\nDetails: " + ex.getMessage(),
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Database error: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        background.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createStyledFormPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 230));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 20, 8, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int row, int col) {
        gbc.gridx = col;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridy = row + 1;
        panel.add(field, gbc);
        field.setPreferredSize(new Dimension(180, 30));
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
        SwingUtilities.invokeLater(() -> {
            User dummyUser = new User("demo@example.com", "Demo User");
            dummyUser.setUserId(1); // Example user ID; required for foreign key!
            new FillUpForm(dummyUser);
        });
    }
}