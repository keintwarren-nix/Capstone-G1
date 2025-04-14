package main;

import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JPanel {

    private Image backgroundImage;
    private JProgressBar progressBar;

    public TitleScreen(Runnable onContinue) {
        // Load background image
        ImageIcon icon = new ImageIcon("src/res/background/loadingScreen.png");
        backgroundImage = icon.getImage();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Create and style the progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(300, 20));
        progressBar.setBorderPainted(false);
        progressBar.setForeground(new Color(100, 150, 255));
        progressBar.setBackground(new Color(230, 230, 230));

        // Position the progress bar higher using padding
        JPanel progressContainer = new JPanel(new GridBagLayout());
        progressContainer.setOpaque(false);

        JPanel innerWrapper = new JPanel();
        innerWrapper.setOpaque(false);
        innerWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        innerWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0)); // move it up 80px from bottom
        innerWrapper.add(progressBar);

        progressContainer.add(innerWrapper);
        add(progressContainer, BorderLayout.SOUTH);

        // Timer for progress update (every 30 ms)
        Timer updateTimer = new Timer(30, null);
        final int[] progress = {0};
        updateTimer.addActionListener(e -> {
            progress[0]++;
            progressBar.setValue(progress[0]);
            if (progress[0] >= 100) {
                updateTimer.stop();
                if (onContinue != null) {
                    onContinue.run();
                }
            }
        });
        updateTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image scaled to fit
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
