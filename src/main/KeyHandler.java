package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entity.Dummy;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean up, left, right, punch, kick, sp;
    public boolean isEnteringName = false;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean p1Up, p1Down, p1EnterPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        char typedChar = e.getKeyChar();
        if (gp.gameState == gp.winNameInputState) { // Only check for win name input
            if (Character.isLetterOrDigit(typedChar) || typedChar == ' ') {
                if (gp.ui.playerName.length() < 15) { // Limit name length
                    gp.ui.playerName += typedChar;
                }
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
//        System.out.println("Current Game State: " + gp.gameState);
        if (gp.gameState == gp.chooseCharacterState) {
            gp.ui.commandAbt = 0;
            if (code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 1) {
                    gp.ui.commandNum = 1;
                }
            }

            if (code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 8) {
                    gp.ui.commandNum = 8;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandAbt == 0) {
                    gp.cchoice = gp.ui.commandNum;  // Use commandNum directly as the choice
                    gp.player.getPlayerImage(gp.cchoice);  // Update player image
                    // Create a new dummy with the current game panel (contains updated choice)
                    gp.dummy = new Dummy(gp);
                    gp.gameState = gp.playState;
                    // **ADD THIS LINE HERE:** Start the round manager when entering playState
                    gp.roundManager.startMatch();
                }
            }
        }
        if (gp.gameState == gp.PVP_INSTRUCTION_P1) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.PVP_CHOOSE_P1;
                gp.pvpPlayer = 1; // Indicate Player 1 is choosing
                gp.ui.commandNum = 1; // Reset character selection command for Player 1
            }
        } else if (gp.gameState == gp.PVP_CHOOSE_P1) {
            if (gp.pvpPlayer == 1) {
                if (code == KeyEvent.VK_A) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 1) {
                        gp.ui.commandNum = 8;
                    }
                }
                if (code == KeyEvent.VK_D) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 8) {
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    gp.player1Choice = gp.ui.commandNum;
                    gp.gameState = gp.PVP_P1_CONFIRMED;
                }
            }
        } else if (gp.gameState == gp.PVP_P1_CONFIRMED) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.PVP_CHOOSE_P2;
                gp.pvpPlayer = 2; // Indicate Player 2 is choosing
                gp.ui.commandNum = 1; // Reset character selection command for Player 2
            }
        } else if (gp.gameState == gp.PVP_CHOOSE_P2) {
            if (gp.pvpPlayer == 2) {
                if (code == KeyEvent.VK_LEFT) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 1) {
                        gp.ui.commandNum = 8;
                    }
                }
                if (code == KeyEvent.VK_RIGHT) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 8) {
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    gp.player2Choice = gp.ui.commandNum;
                    gp.gameState = gp.playerVsPlayerPlayState;
                    gp.initializePvPPlayers();
                }
            }
        }
        if(gp.gameState == gp.choosingState){
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.chooseCharacterState; // Go to character select for PvNPC
                    gp.ui.commandNum = 0;
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.PVP_INSTRUCTION_P1;  // Go to PvP character choosing
                    gp.ui.commandNum = 0; // Reset commandNum for the next screen
                     // Indicate Player 1 is choosing first
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        if(gp.gameState == gp.tileState){
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3){
                    gp.ui.commandNum = 3;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.choosingState;
                    gp.ui.commandNum = 0; // Reset command number for choosing state
                }

                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.leaderboardState;
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.abState;
                    System.out.println("Changed to About Us state: " + gp.gameState); // Debug print
                }

                if(gp.ui.commandNum == 3){
                    System.exit(0);
                }
            }
            if (code == KeyEvent.VK_L) { // Direct access from title screen (optional)
                gp.gameState = gp.leaderboardState;
            }
        }

        if (gp.gameState == gp.abState){
            if (code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 1;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }
            if (code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 2;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.tileState;
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.abState2;
                    gp.ui.commandNum = 0;
                }
            }
        }

        if (gp.gameState == gp.abState2){
            if (code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 1;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }
            if (code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 2;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.abState;
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.abState3;

                    gp.ui.commandNum = 0;

                }
            }
        }

        if (gp.gameState == gp.abState3){
            if (code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 1;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }
            if (code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 2;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.abState2;
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.abState4;

                    gp.ui.commandNum = 0;

                }
            }
        }

        if (gp.gameState == gp.abState4){
            if (code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 1;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }
            if (code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 2;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.abState3;
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.abState5;

                    gp.ui.commandNum = 0;

                }
            }
        }

        if (gp.gameState == gp.abState5){
            if (code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 1){
                    gp.ui.commandNum = 1;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }
            if (code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 1;
                }
                System.out.println("About Us nav: " + gp.ui.commandNum); // Debug print
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.abState4;
                }
            }
        }


        if (gp.gameState == gp.roundTransitionState) {
            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                // Skip the rest of the transition and start the next round immediately
                if (gp.roundManager.getPlayerWins() >= (gp.roundManager.getMaxRounds() / 2 + 1)) {
                    gp.gameState = gp.winState;
                } else if (gp.roundManager.getDummyWins() >= (gp.roundManager.getMaxRounds() / 2 + 1)) {
                    gp.gameState = gp.gameOverState;
                } else {
                    gp.roundManager.startNewRound();
                }
            }
        }

        if (gp.gameState == gp.winState) { // Only handle ENTER for win state
            System.out.println("Current Game State: " + gp.gameState);
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.winNameInputState;
                System.out.println("Transitioning to winNameInputState");
            }
            if (code == KeyEvent.VK_L) {
                gp.gameState = gp.leaderboardState;
            }
        } else if (gp.gameState == gp.winNameInputState) { // Only handle name submission for win
            if (code == KeyEvent.VK_ENTER) {
                // Submit the name and score
                gp.leaderboardManager.addEntry(gp.ui.playerName, gp.roundManager.getPlayerWins()); // Access through gp
                gp.ui.playerName = ""; // Clear the name for next time
                gp.gameState = gp.leaderboardState; // Go to leaderboard after submitting
            }
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (gp.ui.playerName.length() > 0) {
                    gp.ui.playerName = gp.ui.playerName.substring(0, gp.ui.playerName.length() - 1);
                }
            }
        }


        if (gp.gameState == gp.gameOverState || gp.gameState == gp.winState) {
            if (code == KeyEvent.VK_L) {
                gp.gameState = gp.leaderboardState;
            } else if (code == KeyEvent.VK_ENTER) {
                if (gp.gameState == gp.gameOverState) {
                    gp.gameState = gp.gameOverNameInputState; // Go to name input from game over
                } else if (gp.gameState == gp.winState) {
                    gp.gameState = gp.winNameInputState; // Go to name input from win
                }
            }
        } else if (gp.gameState == gp.gameOverNameInputState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.leaderboardManager.addEntry(gp.ui.playerName, 0); // Assuming 0 score for game over
                gp.ui.playerName = "";
                gp.gameState = gp.leaderboardState;
            }
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (gp.ui.playerName.length() > 0) {
                    gp.ui.playerName = gp.ui.playerName.substring(0, gp.ui.playerName.length() - 1);
                }
            }
        } else if (gp.gameState == gp.winNameInputState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.leaderboardManager.addEntry(gp.ui.playerName, gp.roundManager.getPlayerWins());
                gp.ui.playerName = "";
                gp.gameState = gp.leaderboardState;
            }
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (gp.ui.playerName.length() > 0) {
                    gp.ui.playerName = gp.ui.playerName.substring(0, gp.ui.playerName.length() - 1);
                }
            }
        } else if (gp.gameState == gp.leaderboardState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.tileState; // Go back to the main menu (adjust as needed)
            }
        }else if (gp.gameState == gp.playerVsPlayerPlayState) {
            // Player 1 controls
            if (code == KeyEvent.VK_W) up = true;   // Jump
            if (code == KeyEvent.VK_A) left = true; // Walk Left
            if (code == KeyEvent.VK_D) right = true; // Walk Right
            if (code == KeyEvent.VK_J) punch = true; // Attack 1
            if (code == KeyEvent.VK_K) kick = true;  // Attack 2
            if (code == KeyEvent.VK_L) sp = true;
        }

            // Handle different key presses based on game state
        if (gp.gameState == gp.playState) {
            handlePlayState(code);
        } else if (gp.gameState == gp.pauseState) {
            handlePauseState(code);
        } else if (gp.gameState == gp.gameOverState) {
            handleGameOverState(code);
        } else if (gp.gameState == gp.winState) {
            handleWinState(code);
        }
    }

    private void handlePlayState(int code) {
        if (code == KeyEvent.VK_W) {
            up = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if (code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_J) {
            punch = true;
        }
        if (code == KeyEvent.VK_K) {
            kick = true;
        }
        if (code == KeyEvent.VK_L) {
            sp = true;
        }

        // Add pause functionality
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
    }

    private void handlePauseState(int code) {
        // Resume game when ESC is pressed again
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

    private void handleGameOverState(int code) {
        // Restart game when ENTER is pressed
        if (code == KeyEvent.VK_ENTER) {
            gp.restart();
        }
    }

    private void handleWinState(int code) {
        // Restart game when ENTER is pressed
        if (code == KeyEvent.VK_ENTER) {
            gp.restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_J) {
            punch = false;
        }
        if (code == KeyEvent.VK_K) {
            kick = false;
        }
        if (code == KeyEvent.VK_L) {
            sp = false;
        }
        if (gp.gameState == gp.playerVsPlayerPlayState) {
            // Player 1 controls release
            if (code == KeyEvent.VK_W) up = false;
            if (code == KeyEvent.VK_A) left = false;
            if (code == KeyEvent.VK_D) right = false;
            if (code == KeyEvent.VK_J) punch = false;
            if (code == KeyEvent.VK_K) kick = false;
            if (code == KeyEvent.VK_L) sp = false;
        }

    }
}