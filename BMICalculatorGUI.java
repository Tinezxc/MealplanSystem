package com.mycompany.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorGUI extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font RESULT_FONT = new Font("Segoe UI", Font.BOLD, 18);
    
    private JTextField weightField;
    private JTextField heightField;
    private JLabel resultLabel;
    private JLabel categoryLabel;

    public BMICalculatorGUI() {
        setTitle("BMI Calculator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Weight label and field
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(weightLabel, gbc);

        weightField = new JTextField();
        weightField.setFont(LABEL_FONT);
        weightField.setToolTipText("Enter your weight in kilograms");
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(weightField, gbc);

        // Height label and field
        JLabel heightLabel = new JLabel("Height (m):");
        heightLabel.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(heightLabel, gbc);

        heightField = new JTextField();
        heightField.setFont(LABEL_FONT);
        heightField.setToolTipText("Enter your height in meters");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(heightField, gbc);

        // Calculate button
        JButton calculateButton = new JButton("Calculate BMI");
        calculateButton.setFont(BUTTON_FONT);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(calculateButton, gbc);

        // Result label
        resultLabel = new JLabel("Your BMI: ");
        resultLabel.setFont(RESULT_FONT);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 5, 10);
        add(resultLabel, gbc);

        // Category label
        categoryLabel = new JLabel("Category: ");
        categoryLabel.setFont(LABEL_FONT);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(categoryLabel, gbc);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(LABEL_FONT);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(clearButton, gbc);

        // Action listener for Calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });

        // Action listener for Clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void calculateBMI() {
        String weightText = weightField.getText().trim();
        String heightText = heightField.getText().trim();

        if (weightText.isEmpty() || heightText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both weight and height.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double weight, height;
        try {
            weight = Double.parseDouble(weightText);
            height = Double.parseDouble(heightText);

            if (weight <= 0 || height <= 0) {
                JOptionPane.showMessageDialog(this, "Weight and height must be positive numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for weight and height.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double bmi = weight / (height * height);
        resultLabel.setText(String.format("Your BMI: %.2f", bmi));
        categoryLabel.setText("Category: " + getBMICategory(bmi));
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private void clearFields() {
        weightField.setText("");
        heightField.setText("");
        resultLabel.setText("Your BMI: ");
        categoryLabel.setText("Category: ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BMICalculatorGUI calculatorGUI = new BMICalculatorGUI();
            calculatorGUI.setVisible(true);
        });
    }
}
