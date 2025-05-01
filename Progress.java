package com.mycompany.progress;

import javax.swing.*;
import java.awt.*;

public class Progress {
    public Progress(String email) {

        JFrame frame = new JFrame("PROGRESS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1550, 800);
        frame.setLayout(null);
        
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALLAN JUSTINE\\Downloads\\LogoUi.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 1550, 800);
        frame.setContentPane(background);
        background.setLayout(null);

        // Left-side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setBounds(0, 0, 150, 800);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        frame.add(leftPanel);

        leftPanel.add(Box.createVerticalStrut(150));
        

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

        // Main panel (right side)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(150, 0, 1400, 800);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        // Back button
        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(20, 60, 60, 30);
        mainPanel.add(backButton);

        // Title
        JLabel titleLabel = new JLabel("PROGRESS TRACKER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(100, 55, 300, 40);
        mainPanel.add(titleLabel);

        // Search field and buttons
        JTextField searchField = new JTextField();
        searchField.setBounds(830, 55, 300, 30);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(1140, 55, 100, 30);
        searchButton.setBackground(Color.WHITE);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBounds(1250, 55, 60, 30);
        userButton.setBackground(Color.WHITE);
        mainPanel.add(userButton);

        // Nutrient progress bars
        String[] nutrients = {"Calories", "Fats", "Protein", "Carbohydrates", "Fiber", "Water Intake"};
        int[] values = {0, 0, 0, 0, 0, 0};
        int[] maxValues = {100, 100, 100, 100, 100, 100};

        int yPos = 200;
        for (int i = 0; i < nutrients.length; i++) {
            JLabel nutrientLabel = new JLabel(nutrients[i] + ":");
            nutrientLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            nutrientLabel.setBounds(50, yPos, 200, 35);
            mainPanel.add(nutrientLabel);

            JProgressBar progressBar = new JProgressBar(0, maxValues[i]);
            progressBar.setValue(values[i]);
            progressBar.setStringPainted(true);
            progressBar.setFont(new Font("Arial", Font.PLAIN, 16));
            progressBar.setBounds(250, yPos, 1000, 35);
            mainPanel.add(progressBar);

            yPos += 60;
        }

        // Total Gain
        int totalGain = 0;
        for (int value : values) {
            totalGain += value;
        }

        JLabel totalGainLabel = new JLabel("Total Gain: " + totalGain + "%");
        totalGainLabel.setFont(new Font("Arial", Font.BOLD, 22));
        totalGainLabel.setBounds(250, yPos + 10, 400, 40);
        mainPanel.add(totalGainLabel);

        // Update Progress button
        JButton updateButton = new JButton("Update Progress");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        updateButton.setBackground(Color.WHITE);
        updateButton.setBounds(50, 680, 250, 40);
        mainPanel.add(updateButton);

        frame.setVisible(true);
        
        
    }

    public static void main(String[] args) {
        new Progress("demo@example.com");
    }
}
