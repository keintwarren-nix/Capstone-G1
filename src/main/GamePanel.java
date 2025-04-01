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

    // World Settings
    public Ui ui = new Ui(this);
    public final int maxWorldCol = 420, maxWorldRow = 100;

    long currentTime = System.nanoTime();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int winState = 4;
    public final int choosingState = 5;
    public final int chooseCharacterState = 6;

    public tileManager tileM = new tileManager(this);

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public Sound sound = new Sound();

    public Dummy dummy = new Dummy(this);
    public Player player = new Player(this, keyH);

    private Image backgroundImage;
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/res/background/forest.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
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
        if (gameState == playState) {
            // Check if player is dead
            if (player.health <= 0) {
                // Update player's death effect
                player.deathEffect.update();

                // Check if death animation is complete to transition to game over
                if (player.deathEffect.isComplete()) {
                    gameState = gameOverState;
                }

                // If dummy is still alive, let it move but don't allow it to attack the dead player
                if (dummy.health > 0) {
                    dummy.update();
                } else {
                    dummy.deathEffect.update();
                }
            }
            // Both characters are alive
            else if (player.health > 0 && dummy.health > 0) {
                player.update();
                dummy.update();
            }
            // Player alive, dummy dead
            else if (player.health > 0 && dummy.health <= 0) {
                player.update();
                dummy.deathEffect.update();

                // Check if death animation is complete to transition to win state
                if (dummy.deathEffect.isComplete()) {
                    gameState = winState;
//                    sound.playMusic(5); // Play victory music
                }
            }
        } else if (gameState == pauseState) {
            // Game is paused, don't update
            sound.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == titleState)
        {
            ui.draw(g2);

        }else if (gameState == choosingState){
            ui.draw(g2);
        }else {

            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
            }

            try {
                tileM.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            // Draw player based on state
            if (player.health > 0) {
                player.draw(g2);
            } else {
                player.deathEffect.draw(g2);
            }
            // Draw dummy based on state
            if (dummy.health > 0) {
                dummy.draw(g2);
            } else {
                dummy.deathEffect.draw(g2);
            }
            // Draw UI
            ui.draw(g2);
            g2.dispose();

        }
    }

    public void setupGame() {
        aSetter.setObject();
        gameState = tileState;
        // Always stop any existing music first
        if(gameState != tileState) {
            sound.playMusic(1);
            if (gameState == winState || gameState == gameOverState) {
                // Don't play music for these states
                sound.stopMusic();
            }
        }

//        gameState = playState;
    }

    public void restart() {
        // Stop any currently playing music first
        sound.stopMusic();

        // Reset player
        player.setDefaultValues();

        // Reset dummy
        dummy = new Dummy(this);

        // Reset game state
        gameState = playState;

        // Play background music (after stopping any previous music)
//        sound.playMusic(1);
    }
}