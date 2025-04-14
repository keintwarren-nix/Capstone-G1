package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Disney Clash");

        TitleScreen titleScreen = new TitleScreen(() -> {
            SwingUtilities.invokeLater(() -> {
                window.getContentPane().removeAll();  // Remove TitleScreen
                GamePanel gamePanel = new GamePanel();  // Initialize GamePanel
                window.add(gamePanel);
                window.pack();
                window.setLocationRelativeTo(null);   // Center window
                gamePanel.setupGame();                // Set up the game
                gamePanel.startGameThread();          // Start the game thread (game loop)
                gamePanel.requestFocusInWindow();    // Ensure GamePanel has focus for input
            });
        });

        window.add(titleScreen);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
