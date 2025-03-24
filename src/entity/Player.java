package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    int i = 1;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(8, 16, 32, 16);
        setDefaultValues();
        getPlayerImage();
        loadHealthImages();
        healthIcon = new Health();
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 420;
        speed = 4;
        direction = "rightidle";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResource("/res/player/snowwhite_jump.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player/snowwhite_jump.png"));
            left1 = ImageIO.read(getClass().getResource("/res/player/snowwhite_leftwalk_1.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player/snowwhite_leftwalk_2.png"));
            right1 = ImageIO.read(getClass().getResource("/res/player/snowwhite_rightwalk_1.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player/snowwhite_rightwalk_2.png"));
            leftidle = ImageIO.read(getClass().getResource("/res/player/snowwhite_idle_left.png"));
            rightidle = ImageIO.read(getClass().getResource("/res/player/snowwhite_idle_right.png"));
            punch = ImageIO.read(getClass().getResource("/res/player/snowwhite_punch.png"));
            kick = ImageIO.read(getClass().getResource("/res/player/snowwhite_kick.png"));
            sp = ImageIO.read(getClass().getResource("/res/player/cinderella_idle_right.png"));
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

    private boolean isJumping = false;
    private int jumpHeight = 100;
    private int jumpStartY;

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
        }
    }

    public void update() {
        boolean keyPressed = keyH.up || keyH.right || keyH.left || keyH.punch || keyH.kick || keyH.sp;
        updateHealthIcon();
        if (keyPressed) {
            String prevDirection = direction;

            if (keyH.up && worldY == 420 && !isJumping) {
                direction = "up";
                isJumping = true;
                jumpStartY = worldY;
            } else if (keyH.right) {
                direction = "right";
            } else if (keyH.left) {
                direction = "left";
            } else if (keyH.punch) {
                direction = "punch";
                gp.sound.playSE(3);
            } else if (keyH.kick) {
                direction = "kick";
                gp.sound.playSE(4);
            } else if (keyH.sp) {
                direction = "sp";
                gp.sound.playSE(5);
            }

            collisionOn = false;

            gp.cChecker.checkTile(this);
            if(gp.dummy.health > 0) {
                gp.cChecker.checkEntity(this, gp.dummy);
            }

            if (!collisionOn) {
                switch (direction) {
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            } else if (collisionOn) {
                if (prevDirection.equals("right")) {
                    worldX -= speed;
                    direction = "left";
                } else if (prevDirection.equals("left")) {
                    worldX += speed;
                    direction = "right";
                }
                collisionOn = false;
            }
        } else {
            if (!isJumping && worldY == 420) {
                direction = "rightidle";
            }
        }

        if (isJumping) {
            worldY -= speed * 4;
            if (worldY <= jumpStartY - jumpHeight) {
                isJumping = false;
            }
        } else if (worldY < 420) {
            worldY += speed * 2;
            if (worldY > 420) {
                worldY = 420;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = rightidle;

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "punch":
                image = punch;
                break;
            case "kick":
                image = kick;
                break;
            case "sp":
                image = sp;
                break;
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);

        if (healthIcon != null && healthIcon.image != null) {
            g2.drawImage(healthIcon.image, 30, -150, gp.tileSize*8, gp.tileSize*8, null);
        }
    }
}
