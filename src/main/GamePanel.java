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
    public int pvpPlayer = 0;
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
    public final int playerVsPlayerChoosingState = 17; // NEW STATE
    public final int playerVsPlayerPlayState = 18;
    public final int PVP_INSTRUCTION_P1 = 30;
    public final int PVP_CHOOSE_P1 = 31;
    public final int PVP_P1_CONFIRMED = 32;
    public final int PVP_CHOOSE_P2 = 33;
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

//    public AssetSetter aSetter = new AssetSetter(this);
    public Sound sound = new Sound();

    public Dummy dummy = new Dummy(this);
    public Player player;
     public Player player2;
    Image backgroundImage;
    public SuperObject obj[] = new SuperObject[10];
    KeyHandler2 keyH2 = new KeyHandler2(this);
    public int player1Choice = 0; // To store Player 1's chosen character
    public int player2Choice = 0; // To store Player 2's chosen character
    public int commandNum = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.addKeyListener(keyH2);

        player = new Player(this, keyH);


        player2 = new Player(this, keyH2);
        player2.playerNum = 2; // Indicate it's Player 2
        player2.worldX = 200; // Set a different starting position for Player 2



        dummy = new Dummy(this);

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
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }
        }
    }
    public void startGamePvP() {
        // Initialize the first player
        player = new Player(this, keyH);
        player.setDefaultValues();
        player.worldX = tileSize * 3; // Example starting position
        player.worldY = tileSize * 3;
        player.playerNum = 1;
        player.getPlayerImagePVP(player1Choice);
        // Initialize the second player
        player2 = new Player(this, keyH2); // For now, using the same KeyHandler. We'll fix this later.
        player2.setDefaultValues();
        player2.worldX = tileSize * 5; // Example starting position, different from player 1
        player2.worldY = tileSize * 3;
        player2.playerNum = 2;
        player2.getPlayerImagePVP(player2Choice);

        gameState = playerVsPlayerPlayState;

        this.requestFocusInWindow(); // Ensure GamePanel has focus

    }

    public void update() throws IOException {
        if (gameState == playState) {
            player.update();
            dummy.update();

            System.out.println("Player Health (before RoundManager): " + player.health);
            System.out.println("Dummy Health (before RoundManager): " + dummy.health);
            roundManager.update();

            if (player.health <= 0) {
                player.deathEffect.update();
                if (dummy.health > 0) {
                    dummy.update();
                } else dummy.deathEffect.update();
            } else if (player.health > 0 && dummy.health > 0) {
                player.update();
                dummy.update();
            } else if (player.health > 0 && dummy.health <= 0) {
                player.update();
                dummy.deathEffect.update();
            }
        } else if (gameState == pauseState) {
            sound.stop();
        } else if (gameState == roundTransitionState) {
            // Just wait for the round manager to handle the transition
        }
        else if (gameState == playerVsPlayerChoosingState) {
            processPVPCharacterChoiceInput(); // Call the new input processing method
        }else if (gameState == playerVsPlayerPlayState) {
            if (player != null && player.health > 0) {
                player.handleKeyInput(keyH, player2); // Player 1 uses keyH
                player.handleJump();
                player.updateSprite();
                player.updateHealthIcon();
                player.update();
                // Keep the update for cooldown and other logic
            }


            if (player2 != null && player2.health > 0) {
                player2.handleKeyInput2(keyH2, player); // Player 2 uses keyH2
                player2.handleJump();
                player2.updateSprite();
                player2.updateHealthIcon();
                player2.update();
            }

            // Check for game over in PvP
            if (player != null && player.health <= 0 && (player2 != null && player2.health > 0)) {
                gameState = gameOverState;
                ui.gameOverMessage = "Player 2 Wins!";
            } else if (player2 != null && player2.health <= 0 && (player != null && player.health > 0)) {
                gameState = gameOverState;
                ui.gameOverMessage = "Player 1 Wins!";
            } else if ((player == null || player.health <= 0) && (player2 == null || player2.health <= 0)) {
                gameState = gameOverState;
                ui.gameOverMessage = "It's a Draw!";
            }
        }
    }
    public void processPVPCharacterChoiceInput() {
        if (pvpPlayer == 1) {
            if (keyH.p1Up) {
                commandNum--;
                if (commandNum < 0) commandNum = 7;
            }
            if (keyH.p1Down) {
                commandNum++;
                if (commandNum > 7) commandNum = 0;
            }
            if (keyH.p1EnterPressed) {
                player1Choice = commandNum + 1; // Store Player 1's choice (1-8)
                pvpPlayer = 2; // Move to Player 2's selection
                commandNum = 0; // Reset commandNum for Player 2
                keyH.p1EnterPressed = false; // Consume the input
            }
        } else if (pvpPlayer == 2) {
            if (keyH2.p2UpPressed) {
                commandNum--;
                if (commandNum < 0) commandNum = 7;
            }
            if (keyH2.p2DownPressed) {
                commandNum++;
                if (commandNum > 7) commandNum = 0;
            }
            if (keyH2.p2EnterPressed) {
                player2Choice = commandNum + 1; // Store Player 2's choice (1-8)
                startGamePvP(); // Start the PvP game after both choose
                keyH2.p2EnterPressed = false; // Consume the input
            }
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
        } else if (gameState == PVP_INSTRUCTION_P1) {
            ui.draw(g2); // ui.draw() will call drawPVPInstructionScreen
        } else if (gameState == PVP_CHOOSE_P1) {
            ui.draw(g2);
        } else if (gameState == PVP_P1_CONFIRMED) {
            ui.draw(g2); // ui.draw() will call drawPVPInstructionScreen
        } else if (gameState == PVP_CHOOSE_P2) {
            ui.draw(g2);
    }else if (gameState == winState) {
            ui.drawWinScreen(g2); // Call the method to draw the win screen
        } else if (gameState == winNameInputState) {
            ui.drawNameInputDialog(g2,"Enter your name: ");
        } else if (gameState == playerVsPlayerPlayState) {
            if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
            try {
                tileM.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < obj.length; i++) if (obj[i] != null) obj[i].draw(g2, this);
            if (player != null) player.draw(g2);
            if (player2 != null) player2.draw(g2);
            ui.draw(g2);
        } else {
            if (backgroundImage != null) g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
            try {
                tileM.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < obj.length; i++) if (obj[i] != null) obj[i].draw(g2, this);
            if (player.health > 0) player.draw(g2); else player.deathEffect.draw(g2);
            if (dummy.health > 0) dummy.draw(g2); else dummy.deathEffect.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }
    }
    public void initializePvPPlayers() {
        // Based on gp.p1CharacterChoice, create and position Player 1
        System.out.println("Player 1 chose character: " + player1Choice);
        player= new Player(this, keyH); // Assuming you have a Player class and KeyHandler
        player.setDefaultValues(); // Example starting position
        player.getPlayerImage(player1Choice);

        // Based on gp.p2CharacterChoice, create and position Player 2
        System.out.println("Player 2 chose character: " + player2Choice);
        player2 = new Player(this, keyH2); // Assuming you have a second KeyHandler for Player 2
        player2.setDefaultValues2(); // Example starting position
        player2.getPlayerImage(player2Choice);
    }
    public void setupGame() {
        gameState = tileState;
        player.setDefaultValues(); // Set initial position, etc.
        player.playerNum = 1;
        dummy.setDefaultValues();
        // Always stop any existing music first
        if (gameState != tileState) {
            sound.playMusic(1);
            if (gameState == winState || gameState == gameOverState || gameState == pauseState || gameState == tileState || gameState == roundTransitionState) {
                // Don't play music for these states
                sound.stopMusic();
            }
        }
    }
        public void restart(){
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
