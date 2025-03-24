package entity;

import main.GamePanel;
import object.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Dummy extends Entity {

    GamePanel gp;
    BufferedImage image;
    public Health healthIcon;
    public BufferedImage[] healthImages;
    public int health = 100;
    int i = 1;

    public Dummy(GamePanel gp) {
        super(gp);
        this.gp = gp;
        worldX = 550;
        worldY = 420;
        solidArea = new Rectangle(8, 16, 32, 16);
        collisionOn = true;
        getDummy();
        loadHealthImages();
        healthIcon = new Health();
        updateHealthIcon();  // Set initial health icon
    }

    public void getDummy() {
        try {
            stand = ImageIO.read(getClass().getResource("/res/objects/dummy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHealthImages() {
        try {
            healthImages = new BufferedImage[5];
            healthImages[4] = ImageIO.read(getClass().getResource("/res/objects/full.png"));
            healthImages[3] = ImageIO.read(getClass().getResource("/res/objects/thirdfourth.png"));
            healthImages[2] = ImageIO.read(getClass().getResource("/res/objects/half.png"));
            healthImages[1] = ImageIO.read(getClass().getResource("/res/objects/onefourth.png"));
            healthImages[0] = ImageIO.read(getClass().getResource("/res/objects/dead.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHealthIcon() {

        if (health == 100){
            healthIcon.image = healthImages[4];
        } else if (health <= 75 && health > 50) {
            healthIcon.image = healthImages[3];
        } else if (health <= 50 && health > 25) {
            healthIcon.image = healthImages[2];
        } else if (health <= 25 && health > 0) {
            healthIcon.image = healthImages[1];
        } else if (health <= 0) {
            healthIcon.image = healthImages[0];
            collisionOn = false;
        }
    }

    public void update() {
        Rectangle dummySolidArea = new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
        Rectangle playerSolidArea = new Rectangle(gp.player.worldX + gp.player.solidArea.x, gp.player.worldY + gp.player.solidArea.y, gp.player.solidArea.width, gp.player.solidArea.height);

        if (dummySolidArea.intersects(playerSolidArea)) {
            gp.player.collisionOn = true;
        }

        if (gp.player.direction.equals("kick") && gp.player.collisionOn) {
            if (health > 0) {
                health -= i;
                updateHealthIcon();
            }
        } else if (gp.player.direction.equals("punch") && gp.player.collisionOn) {
            if (health > 0) {
                health -= i;
                updateHealthIcon();
            }
        } else if (gp.player.direction.equals("sp") && gp.player.collisionOn) {
            if (health > 0) {
                health -= i;
                updateHealthIcon();
            }
        }
    }

    public void draw(Graphics2D g2) {
        image = stand;
        g2.drawImage(image, worldX, worldY, gp.tileSize + 32, gp.tileSize + 32, null);

        if (healthIcon != null && healthIcon.image != null) {
            g2.drawImage(healthIcon.image, worldX-10, worldY-60, gp.tileSize*2, gp.tileSize*2, null);
        }
    }
}
