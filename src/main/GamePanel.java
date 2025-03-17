package main;

import entity.Player;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable {
    int playerY = 400, playerX = 100, fps = 60;
    int originalTileSize = 16;
    int scale = 3;
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    public int tileSize = originalTileSize * scale;
    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = tileSize * maxScreenRow;

    long currentTime = System.nanoTime();

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    private Image backgroundImage; // Background image variable

    // Screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Load the background image using ImageIO
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/res/background/forest.jpg"));
        } catch (IOException e) {
            e.printStackTrace(); // Print error if image not found
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps, delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw background image
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
        }

        // Draw the player
        player.draw(g2);

        g2.dispose();
    }
}
