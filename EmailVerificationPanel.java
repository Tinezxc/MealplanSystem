package com.mycompany.loginpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailVerificationPanel {

    public EmailVerificationPanel() {
        
        JFrame frame = new JFrame("Email Verification");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 340, 230);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE); 
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); 
        frame.add(panel);

        
        JLabel titleLabel = new JLabel("Email Verification", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(90, 10, 160, 30);
        panel.add(titleLabel);

        
        JLabel codeLabel = new JLabel("Enter Verification Code:");
        codeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        codeLabel.setBounds(30, 60, 200, 20);
        panel.add(codeLabel);

        
        JTextField codeField = new JTextField();
        codeField.setBounds(30, 80, 280, 30);
        panel.add(codeField);

        
        JButton resendButton = new JButton("Resend Code");
        resendButton.setForeground(Color.DARK_GRAY);
        resendButton.setBackground(Color.WHITE);
        resendButton.setBounds(30, 120, 280, 30);
        panel.add(resendButton);

        
        JButton verifyButton = new JButton("Verify");
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setBackground(Color.BLACK);
        verifyButton.setBounds(120, 160, 100, 30);
        panel.add(verifyButton);

        
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText();

                
                if (code.equals("123456")) {
                    JOptionPane.showMessageDialog(frame, "Email Verified Successfully!");
                    
                    frame.dispose();  
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid verification code.");
                }
            }
        });

        
        resendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(frame, "Verification code has been resent to your email.");
            }
        });

        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmailVerificationPanel();
    }
}
