package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler2 implements KeyListener {

    GamePanel gp;
    public boolean p2UpPressed, p2DownPressed, p2LeftPressed, p2RightPressed;
    public boolean p2PunchPressed, p2KickPressed, p2SpecialPressed;
    public boolean p2EnterPressed;  // You might need other key presses for actions (attack, etc.) for player 2

    public boolean p1UpPressed, p1DownPressed, p1EnterPressed;
    public KeyHandler2(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playerVsPlayerPlayState) {
            // Player 2 controls
            if (code == KeyEvent.VK_UP) p2UpPressed = true;
            if (code == KeyEvent.VK_LEFT) p2LeftPressed = true;
            if (code == KeyEvent.VK_RIGHT) p2RightPressed = true;
            if (code == KeyEvent.VK_B) p2PunchPressed = true;
            if (code == KeyEvent.VK_N) p2KickPressed = true;
            if (code == KeyEvent.VK_M) p2SpecialPressed = true;
        } else if (gp.gameState == gp.playerVsPlayerChoosingState) {
            // Player 1 controls for choosing (using arrow keys temporarily via keyH2)
//            if (gp.pvpPlayer == 1) {
//                if (code == KeyEvent.VK_UP) p1UpPressed = true;
//                if (code == KeyEvent.VK_DOWN) p1DownPressed = true;
//                if (code == KeyEvent.VK_ENTER) p1EnterPressed = true;
//            }
//            // Player 2 controls for choosing (using WASD via keyH2)
//            else if (gp.pvpPlayer == 2) {
//                if (code == KeyEvent.VK_W) p2UpPressed = true;
//                if (code == KeyEvent.VK_S) p2DownPressed = true;
//                if (code == KeyEvent.VK_ENTER) p2EnterPressed = true;
//            }
            if (gp.pvpPlayer == 2) {
                if (code == KeyEvent.VK_W) p2UpPressed = true;
                if (code == KeyEvent.VK_S) p2DownPressed = true;
                if (code == KeyEvent.VK_ENTER) p2EnterPressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playerVsPlayerPlayState) {
            // Player 2 controls release
            if (code == KeyEvent.VK_UP) p2UpPressed = false;
            if (code == KeyEvent.VK_LEFT) p2LeftPressed = false;
            if (code == KeyEvent.VK_RIGHT) p2RightPressed = false;
            if (code == KeyEvent.VK_B) p2PunchPressed = false;
            if (code == KeyEvent.VK_N) p2KickPressed = false;
            if (code == KeyEvent.VK_M) p2SpecialPressed = false;
        } else if (gp.gameState == gp.playerVsPlayerChoosingState) {
            // Player 2 controls release for choosing
            if (code == KeyEvent.VK_W && gp.pvpPlayer == 2) p2UpPressed = false;
            if (code == KeyEvent.VK_S && gp.pvpPlayer == 2) p2DownPressed = false;
            if (code == KeyEvent.VK_ENTER && gp.pvpPlayer == 2) p2EnterPressed = false;

            // Player 1 controls release for choosing
//            if (code == KeyEvent.VK_UP && gp.pvpPlayer == 1) p1UpPressed = false;
//            if (code == KeyEvent.VK_DOWN && gp.pvpPlayer == 1) p1DownPressed = false;
//            if (code == KeyEvent.VK_ENTER && gp.pvpPlayer == 1) p1EnterPressed = false;

        }
    }
}