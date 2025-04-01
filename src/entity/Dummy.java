package entity;

import main.GamePanel;
import object.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Dummy extends Entity implements Character{

    GamePanel gp;
    BufferedImage image;
    public Health healthIcon;
    public BufferedImage[] healthImages;
    public int health = 100;
    int moveSpeed = 2;
    int chaseRange = 200;
    int attackRange = 50;
    int attackDamage = 25;

    int attackCooldown = 1500;
    long lastAttackTime = System.currentTimeMillis();
    Random random = new Random();

    boolean isAttacking = false;
    long attackAnimationTime = 300;
    long attackAnimationStartTime = 0;
    boolean stopMoving = false;
    boolean isDead = false;

    public DeathEffect deathEffect;

    public Dummy(GamePanel gp) {
        super(gp);
        this.gp = gp;
        worldX = 550;
        worldY = 420;
        solidArea = new Rectangle(8, 16, 32, 16);
        getDummy();
        loadHealthImages();
        healthIcon = new Health();
        updateHealthIcon();
        deathEffect = new DeathEffect(gp, this);
    }

    @Override
    public void getDummy() {
        try {
            left1 = ImageIO.read(getClass().getResource("/res/player/cinderella_leftwalk_1.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player/cinderella_leftwalk_2.png"));
            right1 = ImageIO.read(getClass().getResource("/res/player/cinderella_rightwalk_1.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player/cinderella_rightwalk_2.png"));
            leftidle = ImageIO.read(getClass().getResource("/res/player/cinderella_idle_left.png"));
            rightidle = ImageIO.read(getClass().getResource("/res/player/cinderella_idle_right.png"));
            punch = ImageIO.read(getClass().getResource("/res/player/cinder_punch_left.png"));
            kick = ImageIO.read(getClass().getResource("/res/player/snowwhite_kick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHealthImages() {
        try {
            healthImages = new BufferedImage[6];

            healthImages[5] = ImageIO.read(getClass().getResource("/res/objects/66.png"));
            healthImages[4] = ImageIO.read(getClass().getResource("/res/objects/55.png"));
            healthImages[3] = ImageIO.read(getClass().getResource("/res/objects/44.png"));
            healthImages[2] = ImageIO.read(getClass().getResource("/res/objects/33.png"));
            healthImages[1] = ImageIO.read(getClass().getResource("/res/objects/22.png"));
            healthImages[0] = ImageIO.read(getClass().getResource("/res/objects/11.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHealthIcon() {

        if (health == 100) {
            healthIcon.image = healthImages[0];
        } else if (health <= 80 && health > 60) {
            healthIcon.image = healthImages[1];
        }else if (health <= 60 && health > 40) {
            healthIcon.image = healthImages[2];
        }else if (health <= 40 && health > 20) {
            healthIcon.image = healthImages[3];
        }else if (health <= 20 && health > 0) {
            healthIcon.image = healthImages[4];
        }else{
            healthIcon.image = healthImages[5];
            collisionOn = false;
        }
    }

    public void moveToPlayer() {
        double distanceToPlayer = Math.sqrt(Math.pow(gp.player.worldX - worldX, 2) + Math.pow(gp.player.worldY - worldY, 2));

        if (isAttacking) {
            if (System.currentTimeMillis() - attackAnimationStartTime >= attackAnimationTime) {
                isAttacking = false;
                if (gp.player.worldX < worldX) {
                    direction = "leftidle";
                } else {
                    direction = "rightidle";
                }
                stopMoving = false;
            }
        } else if (!stopMoving) {
            if (distanceToPlayer < chaseRange) {
                if (distanceToPlayer <= 40) {
                    stopMoving = true;
                    moveAwayFromPlayer();
                } else {
                    chasePlayer();
                }

                if (distanceToPlayer < attackRange && System.currentTimeMillis() - lastAttackTime >= attackCooldown) {
                    attackPlayer();
                    lastAttackTime = System.currentTimeMillis();
                }
            } else {
                direction = "rightidle";
            }
        }
    }

    public void moveAwayFromPlayer() {
        if (gp.player.worldX < worldX) {
            worldX += moveSpeed;
        } else {
            worldX -= moveSpeed;
        }
        stopMoving = false;
    }

    public void chasePlayer() {
        if (!stopMoving) {
            if (gp.player.worldX < worldX) {
                worldX -= moveSpeed;
                direction = "left";
            } else {
                worldX += moveSpeed;
                direction = "right";
            }
            worldY = gp.player.worldY;
        }
    }

    public void attackPlayer() {
        if (gp.player.health <= 0) return;

        int randomAttack = random.nextInt(3);
        switch (randomAttack) {
            case 0:
                direction = "punch";
                break;
            case 1:
                direction = "kick";
                break;
            case 2:
                direction = "sp";
                break;
        }

        isAttacking = true;
        attackAnimationStartTime = System.currentTimeMillis();
        gp.player.health -= attackDamage;

        if (gp.player.worldX < worldX) {
            gp.player.worldX -= 10;
            worldX += 10;
        } else {
            gp.player.worldX += 10;
            worldX -= 10;
        }

        gp.player.updateHealthIcon();
    }

    public void update() {
        if (health <= 0) {
            if (!isDead) {
                isDead = true;
                deathEffect.startEffect(worldX, worldY);
            }
            deathEffect.update();
            return; // Skip dummy update after death
        }

        moveToPlayer();
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        if (health <= 0) {
            deathEffect.draw(g2);
            return;
        }

        BufferedImage image = rightidle;
        if (direction != null) {
            switch (direction) {
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
                case "leftidle":
                    image = leftidle;
                    break;
                case "rightidle":
                    image = rightidle;
                    break;
            }
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);

        if (healthIcon != null && healthIcon.image != null) {
            int healthBarX = worldX + (gp.tileSize - gp.tileSize) / 2;
            int healthBarY = worldY - (gp.tileSize / 2) - 20;
            g2.drawImage(healthIcon.image, 370, -150, gp.tileSize * 8, gp.tileSize * 8, null);        }
    }
}
