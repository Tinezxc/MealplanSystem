

package com.mycompany.progress;
import javax.swing.*;
import java.awt.*;

public class Progress {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Progress Tracker");
        frame.setSize(800, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, 90, 500);
        leftPanel.setLayout(new GridLayout(5, 1));
        frame.add(leftPanel);

        String[] navigatorTitles = {"DASHBOARD", "MEAL PLAN", "PROGRESS", "SCHEDULE", "NOTIFICATION"};
        for (String title : navigatorTitles) {
            JButton navigatorButton = new JButton(title);
            navigatorButton.setBackground(Color.GREEN);
            navigatorButton.setBorderPainted(false);
            navigatorButton.setFocusPainted(false);
            navigatorButton.setFont(new Font("Arial", Font.PLAIN, 7));
            leftPanel.add(navigatorButton);
        }

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(80, 0, 720, 500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        JButton backButton = new JButton("<");
        backButton.setBackground(Color.WHITE);
        backButton.setBounds(15, 10, 43, 25);
        mainPanel.add(backButton);

        JLabel titleLabel = new JLabel("PROGRESS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(70, 10, 150, 30);
        mainPanel.add(titleLabel);

        JTextField searchField = new JTextField(20);
        searchField.setBounds(327, 5, 200, 30);
        mainPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(530, 5, 80, 30);
        mainPanel.add(searchButton);

        JButton userButton = new JButton("👤");
        userButton.setBackground(Color.WHITE);
        userButton.setBounds(615, 5, 80, 30);
        mainPanel.add(userButton);

        // Nutrient Progress Bars
        String[] nutrients = {"Calories", "Fats", "Protein", "Carbohydrates", "Fiber", "Water Intake"};
        int yPos = 110;
        for (String nutrient : nutrients) {
            JLabel nutrientLabel = new JLabel(nutrient + ":");
            nutrientLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            nutrientLabel.setBounds(20, yPos, 120, 25);
            mainPanel.add(nutrientLabel);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            progressBar.setBounds(140, yPos, 500, 25);
            mainPanel.add(progressBar);

            yPos += 40;
        }

        JLabel totalGainLabel = new JLabel("Total Gain: %");
        totalGainLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalGainLabel.setBounds(140, yPos, 300, 30);
        mainPanel.add(totalGainLabel);

        JButton updateButton = new JButton("Update Progress");
        updateButton.setBounds(20, 420, 180, 30);
        mainPanel.add(updateButton);

        frame.setVisible(true);
    }
}
