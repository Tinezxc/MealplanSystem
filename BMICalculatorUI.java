package com.mycompany.bmicalcu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.Shape;

public class BMICalculatorUI extends JFrame {
    private RoundedTextField nameField, ageField, weightField, heightField;
    private JComboBox<String> genderBox;
    private JLabel resultLabel;

    public BMICalculatorUI() {
        setTitle("BMI Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new RoundedPanel(30);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        add(mainPanel);

        JLabel title = new JLabel("BMI Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        nameField = createInput(mainPanel, "Name:");
        ageField = createInput(mainPanel, "Age:");

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        styleComboBox(genderBox);
        mainPanel.add(createLabeledComponent("Gender:", genderBox));

        weightField = createInput(mainPanel, "Weight (kg):");
        heightField = createInput(mainPanel, "Height (cm):");

        // Button panel to hold both buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton calcButton = new RoundedButton("Calculate BMI");
        JButton clearButton = new RoundedButton("Clear");

        calcButton.setBackground(new Color(66, 133, 244));
        calcButton.setForeground(Color.WHITE);

        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);

        buttonPanel.add(calcButton);
        buttonPanel.add(clearButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resultLabel.setForeground(new Color(33, 33, 33));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(resultLabel);

        JLabel viewDietLabel = new JLabel("<HTML><U>View Diet Plan</U></HTML>", SwingConstants.CENTER);
        viewDietLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        viewDietLabel.setForeground(new Color(25, 118, 210));
        viewDietLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewDietLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(viewDietLabel);

        // Action listeners
        calcButton.addActionListener(e -> calculateBMI());

        clearButton.addActionListener(e -> {
            nameField.setText("");
            ageField.setText("");
            weightField.setText("");
            heightField.setText("");
            genderBox.setSelectedIndex(0);
            resultLabel.setText("");
        });

        viewDietLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Diet Plan feature coming soon...");
            }
        });

        setVisible(true);
    }

    private RoundedTextField createInput(JPanel parent, String label) {
        RoundedTextField field = new RoundedTextField(20);
        field.setPreferredSize(new Dimension(200, 35));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setBackground(new Color(245, 245, 245));
        parent.add(createLabeledComponent(label, field));
        return field;
    }

    private JPanel createLabeledComponent(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(300, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private void styleComboBox(JComboBox<String> box) {
        box.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        box.setPreferredSize(new Dimension(200, 35));
        box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double heightCm = Double.parseDouble(heightField.getText());
            double heightM = heightCm / 100;
            double bmi = weight / (heightM * heightM);
            String category;

            if (bmi < 18.5) category = "Underweight";
            else if (bmi < 24.9) category = "Normal";
            else if (bmi < 29.9) category = "Overweight";
            else category = "Obese";

            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, category));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        }
    }

    // Rounded JPanel
    class RoundedPanel extends JPanel {
        private int radius;

        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
        }
    }

    // Rounded JButton
    class RoundedButton extends JButton {
        RoundedButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
            g.setColor(getBackground().darker());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        }

        public void setContentAreaFilled(boolean b) {
            super.setContentAreaFilled(false);
        }
    }

    // Rounded JTextField
    class RoundedTextField extends JTextField {
        private int radius;

        public RoundedTextField(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            return shape.contains(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BMICalculatorUI::new);
    }
}
