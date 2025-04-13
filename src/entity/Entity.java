package entity;

import main.GamePanel;
import object.Health;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY, speed;
    public BufferedImage up1, up2, left1, left2, right1, right2, leftidle, rightidle, punch, kick, sp, hurt;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int spriteCounter = 0, spriteNum = 1;


    public int maxHealth = 100;
    public int health = maxHealth;
    public Health healthIcon;
    public BufferedImage[] healthImages;
    public Graphics2D g2;

    public Entity(GamePanel gp) {}

    public void updateHealthIcon() {
        if (healthIcon != null && healthImages != null) {
            int index = Math.max(0, Math.min(health, healthImages.length - 1));
            healthIcon.image = healthImages[index];
        }
    }
}
