package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up, right, left, punch, kick, sp;
    private long lastUpPressTime = 0;
    private final long upCooldown = 100; // 1.5 seconds cooldown
    private boolean upPressed = false; // To prevent holding W

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if (code == KeyEvent.VK_W && !upPressed) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpPressTime >= upCooldown) {
                up = true;
                lastUpPressTime = currentTime;
                upPressed = true; // Mark W as pressed
            }
        }
        if (code == KeyEvent.VK_G) {
            punch = true;
        }
        if (code == KeyEvent.VK_H) {
            kick = true;
        }
        if (code == KeyEvent.VK_J) {
            sp = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_W) {
            up = false;
            upPressed = false; // Reset to allow W press again after release
        }
        if (code == KeyEvent.VK_G) {
            punch = false;
        }
        if (code == KeyEvent.VK_H) {
            kick = false;
        }
        if (code == KeyEvent.VK_J) {
            sp = false;
        }
    }
}
