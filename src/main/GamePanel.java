package main;

import Tile.tileManager;
import entity.Player;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable {

    public final int tileState = 0;

    int fps = 60;
    int originalTileSize = 16;
    int scale = 3;
   public int maxScreenCol = 16;
   public int maxScreenRow = 12;
    public int tileSize = originalTileSize * scale;
   public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    long currentTime = System.nanoTime();

    public int gameState;
    tileManager tileM = new tileManager(this);
    Sound sound = new Sound();

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

//   public CollisionChecker cChecker = new CollisionChecker(this);

    Player player = new Player(this, keyH);

    private Image backgroundImage; // Background image variable

    public SuperObject obj[] = new SuperObject[10];


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

        //Title Screen


            // Draw background image
            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
            }


            // Draw the player
        try {
            tileM.draw(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.draw(g2);

            g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.stop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
   }
}
