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
                    // Recreate dummy based on choice
                    gp.gameState = gp.playState; // Now go to map state
                    gp.cchoice = gp.ui.commandNum;  // Set chosen character
                    gp.player.getPlayerImage(gp.cchoice);  // Update player image
                    gp.dummy = new Dummy(gp);
                    gp.roundManager.startMatch();
                }
            }

        }


        if (gp.gameState == gp.choosingMapState) {
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
                    gp.ui.mapChoice = gp.ui.commandNum; // Save map selection
                    gp.gameState = gp.chooseCharacterState; // Move to play state
                   // Start the round
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
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.choosingMapState;
                }

                if(gp.ui.commandNum == 1){
                    // Player vs Player logic here
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.tileState;
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
    }
}