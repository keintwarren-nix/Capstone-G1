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
    public int cchoice = 0;
    public RoundManager roundManager;
    public final int roundTransitionState = 13;
    public final int leaderboardState = 14;
    public final int gameOverNameInputState = 15;
    public final int winNameInputState = 16;
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
    public final int abState = 7;
    public final int abState2 = 8;
    public final int abState3 = 9;
    public final int abState4 = 10;
    public final int abState5 = 11;
    public final int settingState = 12;

    public final int char1 = 21;
    public final int char3 = 22;
    public final int char2 = 23;
    public final int char4 = 24;
    public final int char5 = 25;
    public final int char6 = 26;
    public final int char7 = 27;
    public final int char8 = 28;


    public tileManager tileM = new tileManager(this);
    public LeaderboardManager leaderboardManager = new LeaderboardManager();
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);

    public Sound sound = new Sound();

    public Dummy dummy = new Dummy(this);
    public Player player = new Player(this, keyH);
    Image backgroundImage;
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        roundManager = new RoundManager(this);
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
            // First update entities based on their current state
            if (player.health <= 0) {
                // Player is dead, update death effect
                player.update(); // This will handle the death effect

                // Continue updating dummy based on its health
                if (dummy.health > 0) {
                    dummy.update();
                } else {
                    dummy.update(); // This will handle dummy's death effect if health <= 0
                }
            } else if (player.health > 0 && dummy.health > 0) {
                // Both entities are alive, update normally
                player.update();
                dummy.update();
            } else if (player.health > 0 && dummy.health <= 0) {
                // Dummy is dead, player is alive
                player.update();
                dummy.update(); // This will handle the death effect
            }

            // Only let RoundManager end the round if death animations are complete
            boolean readyForRoundEnd = true;

            // Check if we're waiting for death animations
            if (player.health <= 0) {
                if (!player.isDeathComplete()) {
                    readyForRoundEnd = false;
                }
            }

            if (dummy.health <= 0) {
                if (!dummy.isDeathComplete()) {
                    readyForRoundEnd = false;
                }
            }

            // Only update round manager if we're not waiting on animations
            if (readyForRoundEnd) {
                System.out.println("Player Health: " + player.health);
                System.out.println("Dummy Health: " + dummy.health);
                roundManager.update();
            }

        } else if (gameState == pauseState) {
            sound.stop();
        } else if (gameState == roundTransitionState) {
            // Handle round transitions in the round manager
            roundManager.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else if (gameState == choosingState) {
            ui.draw(g2);
        } else if (gameState == winState) {
            ui.drawWinScreen(g2); // Call the method to draw the win screen
        } else if (gameState == winNameInputState) {
            ui.drawNameInputDialog(g2,"Enter your name: ");
        } else {
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

            // Draw player - the player's draw method will handle whether to show
            // the character sprite or death effect
            player.draw(g2);

            // Draw dummy - same approach as player
            dummy.draw(g2);

            // Draw UI
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void setupGame() {
        gameState = tileState;

        // Always stop any existing music first
        if (gameState != tileState) {
            sound.playMusic(1);
            if (gameState == winState || gameState == gameOverState || gameState == pauseState || gameState == tileState || gameState == roundTransitionState) {
                // Don't play music for these states
                sound.stopMusic();
            }
        }
    }

    public void restart() {
        // Stop any currently playing music first
        sound.stopMusic();

        // Start a new match with rounds
        roundManager.startMatch();

        // Reset game state to play state
        gameState = playState;

        // Play background music
        // sound.playMusic(1);
    }
}