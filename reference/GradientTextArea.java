package reference;

import javax.swing.*;
import java.awt.*;

public class GradientTextArea extends JPanel {

    public GradientTextArea() {
        super(new BorderLayout());

        // Create the text area
        JTextArea textArea = new JTextArea();

        // Create the gradient color object
        GradientPaint gradient = new GradientPaint(0, 0, Color.LIGHT_GRAY, getWidth(), getHeight(), Color.DARK_GRAY);

        // Create a custom panel to paint the gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (gradient != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // Add the text area and background panel to the main panel
        backgroundPanel.add(textArea, BorderLayout.CENTER);
        add(backgroundPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gradient Text Area");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientTextArea panel = new GradientTextArea();
        frame.getContentPane().add(panel);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
