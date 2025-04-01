package entity;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DeathEffect {
    GamePanel gp;
    Image deathImage;
    BufferedImage entityImage; // Entity sprite for jump and fall animation
    int x, y;
    boolean deathEffectActive = true;
    boolean jumpActive = false;
    long startTime;
    private Entity entity; // Generic reference to either Player or Dummy
    int deathEffectDuration = 1000; // Show death effect for 1 second
    int jumpHeight = 200;
    int jumpSpeed = 12;
    int fallSpeed = 10;

    int originalY;
    boolean falling = false;
    boolean completed = false;

    public DeathEffect(GamePanel gp, Entity entity) {
        this.gp = gp;
        this.entity = entity;
        loadDeathEffect();
    }

    private void loadDeathEffect() {
        try {
            deathImage = new ImageIcon(getClass().getResource("/res/objects/purple_death.gif")).getImage();
            // Store the entity's current image for the death animation
            if (entity instanceof Dummy) {
                entityImage = ((Dummy)entity).up1;
            } else if (entity instanceof Player) {
                entityImage = ((Player)entity).up2;
            }
        } catch (Exception e) {
            System.out.println("Error loading death effect resources");
            e.printStackTrace();
        }
    }

    public void startEffect(int x, int y) {
        this.x = x;
        this.y = y;
        originalY = y;
        deathEffectActive = true;
        jumpActive = false;
        falling = false;
        completed = false;
        startTime = System.currentTimeMillis();
    }

    public void update() {
        if (deathEffectActive) {
            // End death effect after the duration and start jump
            if (System.currentTimeMillis() - startTime >= deathEffectDuration) {
                deathEffectActive = false;
                jumpActive = true;
            }
        }

        if (jumpActive) {
            if (!falling) {
                y -= jumpSpeed;
                if (originalY - y >= jumpHeight) {
                    falling = true; // Start falling after reaching the peak
                }
            } else {
                y += fallSpeed;
                if (y > gp.screenHeight) {
                    jumpActive = false; // Stop when off-screen
                    completed = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        int drawX, drawY;

        if (entity instanceof Dummy) {
            Dummy dummy = (Dummy)entity;
            drawX = dummy.worldX;
            drawY = dummy.worldY;
        } else if (entity instanceof Player) {
            Player player = (Player)entity;
            drawX = player.worldX;
            drawY = player.worldY;
        } else {
            drawX = x;
            drawY = y;
        }

        if (deathEffectActive) {
            // Draw the entity sprite and overlay the death effect
            g2.drawImage(entityImage, drawX, drawY, gp.tileSize * 2, gp.tileSize * 2, null);
            g2.drawImage(deathImage, drawX - 20, drawY - 20, gp.tileSize * 3, gp.tileSize * 3, null);
        } else if (jumpActive) {
            // Draw the entity sprite jumping/falling
            g2.drawImage(entityImage, drawX, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // Small sparkle effect trailing the entity
            g2.setColor(new Color(255, 255, 255, 128));
            for (int i = 0; i < 5; i++) {
                int sparkX = drawX + gp.tileSize + (int)(Math.random() * 20) - 10;
                int sparkY = y + gp.tileSize + (int)(Math.random() * 20) - 10;
                int sparkSize = (int)(Math.random() * 8) + 4;
                g2.fillOval(sparkX, sparkY, sparkSize, sparkSize);
            }
        }
    }

    // Check if death animation is complete (entity has fallen off screen)
    public boolean isComplete() {
        return completed;
    }
}