package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean up, left, right, punch, kick, sp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.chooseCharacterState){
            if(code == KeyEvent.VK_A){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_D){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 8){
                gp.ui.commandNum = 8;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum != 9){
                    gp.ui.characterChoice = gp.ui.commandNum;
                    gp.ui.commandAbt = gp.ui.commandNum;
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
                    gp.gameState = gp.playState;
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
                    // Menu logic here
                }

                if(gp.ui.commandNum == 2){
                    gp.gameState = gp.abState;
                    System.out.println("Changed to About Us state: " + gp.gameState); // Debug print
                }

                if(gp.ui.commandNum == 3){
                    System.exit(0);
                }
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