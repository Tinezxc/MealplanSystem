package com.mycompany.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class SplashScreen {

    private CircularProgressBar circularProgress;
    private JFrame frame;
    private final int logoDiameter = 300;  
    private final int thickness = 25; 

    public SplashScreen() {
        frame = new JFrame("Loading...");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\bg.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setLayout(null);
        frame.setContentPane(background);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\THINKPAD\\Downloads\\LOGO.jpg");
        Image circularLogo = createCircularImage(logoIcon.getImage(), logoDiameter);
        JLabel logoLabel = new JLabel(new ImageIcon(circularLogo));

        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        logoLabel.setBounds(centerX - logoDiameter / 2, centerY - logoDiameter / 2, logoDiameter, logoDiameter);
        background.add(logoLabel);

        JLabel percentageLabel = new JLabel("0%", SwingConstants.CENTER);
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 26));
        percentageLabel.setForeground(Color.WHITE);
        percentageLabel.setBounds(centerX - 50, centerY + logoDiameter / 2 + 20, 100, 40);
        background.add(percentageLabel);

        int progressBarSize = logoDiameter + thickness;
        circularProgress = new CircularProgressBar(logoDiameter, thickness);
        circularProgress.setBounds(centerX - progressBarSize / 2, centerY - progressBarSize / 2, progressBarSize, progressBarSize);
        background.add(circularProgress);

        frame.setVisible(true);


        Timer timer = new Timer(30, e -> {
            int value = circularProgress.getValue();
            if (value < 100) {
                circularProgress.setValue(value + 1);
                percentageLabel.setText((value + 1) + "%");
            } else {
                ((Timer) e.getSource()).stop();
                frame.dispose();
                new LoginPage(); 
            }
        });
        timer.start();
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


    private static class CircularProgressBar extends JComponent {
        private int value = 0;
        private final int thickness;  
        private final int diameter; 

        public CircularProgressBar(int diameter, int thickness) {
            this.diameter = diameter;
            this.thickness = thickness;

            int size = diameter + thickness;
            setPreferredSize(new Dimension(size, size));
            setMinimumSize(new Dimension(size, size));
            setMaximumSize(new Dimension(size, size));
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = Math.min(100, Math.max(0, value));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = Math.min(getWidth(), getHeight());

            int arcDiameter = size - thickness;
            int arcX = thickness / 2;
            int arcY = thickness / 2;

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawOval(arcX, arcY, arcDiameter, arcDiameter);

            g2d.setColor(Color.BLACK);
            int angle = (int) (360 * (value / 100.0));
            g2d.drawArc(arcX, arcY, arcDiameter, arcDiameter, 90, -angle);

            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new);
    }
}
