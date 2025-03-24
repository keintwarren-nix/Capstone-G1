package main;

import Tile.tileManager;
import entity.Dummy;
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

    //World Setting
    public final int
            maxWorldCol = 420,
            maxWorldRow = 100;

    long currentTime = System.nanoTime();

    public int gameState;
    public tileManager tileM = new tileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

   public CollisionChecker cChecker = new CollisionChecker(this);

   public AssetSetter aSetter = new AssetSetter(this);
    public Sound sound = new Sound();

    public Dummy dummy = new Dummy(this);
   public Player player = new Player(this, keyH);

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
        if(dummy.health > 0) {
            dummy.update();
        }
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

            //tile
        try {
            tileM.draw(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //player
        player.draw(g2);
        if(dummy.health > 0) {
            dummy.draw(g2);
        }
        g2.dispose();
    }

    public void setupGame(){
        aSetter.setObject();
        sound.playMusic(1);
    }

}
