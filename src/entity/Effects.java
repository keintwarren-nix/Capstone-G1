package entity;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Effects {
    GamePanel gp;
    private Entity entity;

    int x, y;
    long startTime;
    int originalY;

    boolean SpecialEffectActive = false;
    int specialEffectDuration = 1000; // Show effect for 1 second
    boolean completed = false;

    Image specialEffect;

    // Track cooldown for special ability
    public static final long SPECIAL_COOLDOWN = 10000; // 10 seconds in milliseconds
    public static long lastSpecialTime = 0;
    public static boolean specialReady = true;


    public Effects(GamePanel gp, Entity entity) {
        this.gp = gp;
        this.entity = entity;
        loadEffect(5);
        }



    public void loadEffect(int choice) {
        try {
            switch(choice){
                case 1:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/siren.gif")).getImage();
                    break;
                case 2:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/healing.gif")).getImage();
                    break;
                case 3:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/frost.gif")).getImage();
                    break;
                case 4:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/shower.gif")).getImage();
                    break;
                case 5:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/swordDance.gif")).getImage();
                    break;
                case 6:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/protection.gif")).getImage();
                    break;
                case 7:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/cook.gif")).getImage();
                    break;
                case 8:
                    specialEffect = new ImageIcon(getClass().getResource("/res/objects/gold.gif")).getImage();
                    break;

            }
        } catch (Exception e) {
            System.out.println("Error loading effect resources");
            e.printStackTrace();
        }
    }

    public void startEffect(int x, int y) {
        this.x = x;
        this.y = y;
        originalY = y;
        SpecialEffectActive = true;
        completed = false;
        startTime = System.currentTimeMillis();
    }

    public static boolean canUseSpecial() {
        long currentTime = System.currentTimeMillis();
        if (!specialReady && currentTime - lastSpecialTime >= SPECIAL_COOLDOWN) {
            specialReady = true;
        }
        return specialReady;
    }

    public static void triggerSpecialCooldown() {
        lastSpecialTime = System.currentTimeMillis();
        specialReady = false;
    }

    public long getRemainingCooldown() {
        if (specialReady) return 0;

        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - lastSpecialTime;
        long remaining = SPECIAL_COOLDOWN - elapsed;
        return remaining > 0 ? remaining : 0;
    }

    public void update() {
        if (SpecialEffectActive) {
            if (System.currentTimeMillis() - startTime >= specialEffectDuration) {
                SpecialEffectActive = false;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int drawX, drawY;

        if (entity instanceof Dummy) {
            Dummy dummy = (Dummy)entity;
            drawX = dummy.worldX;
            drawY = dummy.worldY;
            if (SpecialEffectActive) {
                g2.drawImage(specialEffect, drawX+20, drawY - 50, gp.tileSize, gp.tileSize, null);
            }
        } else if (entity instanceof Player) {
            Player player = (Player)entity;
            drawX = player.worldX;
            drawY = player.worldY;
            if (SpecialEffectActive) {
                g2.drawImage(specialEffect, drawX+40, drawY - 50, gp.tileSize, gp.tileSize, null);
            }
        } else {
            drawX = x;
            drawY = y;
        }


    }


    public boolean isComplete() {
        completed = true;
        return completed;
    }

    }
