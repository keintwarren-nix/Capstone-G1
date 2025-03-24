package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public BufferedImage healthImage;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int health = 4;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX, screenY;


        screenX = 10;
        screenY = 10;

        int diameter = gp.tileSize * 2;


        g2.setClip(new java.awt.geom.Ellipse2D.Float(screenX, screenY, diameter, diameter));
        g2.drawImage(image, screenX, screenY, diameter, diameter, null);
        g2.setClip(null);



        int healthBarX = screenX + diameter + 10;
        int healthBarY = screenY + (diameter / 4);

        if (healthImage != null) {
            int healthWidth = gp.tileSize;
            int healthHeight = gp.tileSize / 2;
            g2.drawImage(healthImage, healthBarX, healthBarY, healthWidth, healthHeight, null);
        }
    }

    public void updateHealth(BufferedImage[] healthImages, int currentHealth) {
        if (currentHealth >= 0 && currentHealth < healthImages.length) {
            healthImage = healthImages[currentHealth];
        }
    }
}
