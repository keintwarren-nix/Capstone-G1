package entity;

import main.GamePanel;
import object.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Dummy extends Entity implements Character {

    GamePanel gp;
    BufferedImage image;
    public Health healthIcon;
    public BufferedImage[] healthImages;
    public int health = 100;
    int moveSpeed = 2;
    int chaseRange = 200;
    int attackRange = 50;
    int attackDamage = 25;

    public int effectType = 0;
    public int effectDuration = 0;
    public int originalMoveSpeed;
    public boolean confused = false;


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
        originalMoveSpeed = moveSpeed;

        int randomChoice = getRandomCharacterChoice();
        getDummy(randomChoice);

        loadHealthImages();
        healthIcon = new Health();
        updateHealthIcon();
        deathEffect = new DeathEffect(gp, this);
    }

    public void applyEffect(int effectType, int duration) {
        this.effectType = effectType;
        this.effectDuration = duration * 60; // Convert to frames (assuming 60 fps)

        // Apply immediate effect changes
        switch(effectType) {
            case Player.FREEZE_EFFECT:
                stopMoving = true;
                break;
            case Player.STUN_EFFECT:
                stopMoving = true;
                break;
            case Player.SLOW_EFFECT:
                moveSpeed = originalMoveSpeed / 2;
                break;
            case Player.CONFUSION_EFFECT:
                confused = true;
                break;
        }
    }

    public int getRandomCharacterChoice() {
        int[] possibleChoices = {1, 2, 3, 4, 5, 6, 7, 8};
        Random random = new Random();
        int randomIndex = random.nextInt(possibleChoices.length); // Generate a random index
        return possibleChoices[randomIndex]; // Return a random choice from the array
    }

    @Override
    public void getDummy(int choice) {
        try {
            // If choice is 0 (default), use a fallback choice
            if (choice == 0) {
                choice = 1; // Default to character 1 if no choice was made
            }

            // Use the choice parameter directly instead of calling getRandomCharacterChoice() again
            switch (choice) {
                case 1:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch1_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch1_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch1_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch1_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch1_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch1_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch1_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch1_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch1_lpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch1_lkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch1_sp.png"));
                    break;
                case 2:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch2_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch2_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch2_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch2_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch2_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch2_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch2_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch2_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch2_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch2_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch2_sp.png"));
                    break;
                case 3:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch3_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch3_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch3_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch3_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch3_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch3_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch3_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch3_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch3_lpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch3_lkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch3_sp.png"));
                    break;
                case 4:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch4_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch4_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch4_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch4_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch4_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch4_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch4_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch4_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch4_lpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch4_lkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch4_sp.png"));
                    break;
                case 5:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch5_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch5_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch5_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch5_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch5_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch5_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch5_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch5_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch5_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch5_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch5_sp.png"));
                    break;
                case 6:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch6_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch6_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch6_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch6_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch6_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch6_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch6_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch6_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch6_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch6_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch6_sp.png"));
                    break;
                case 7:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch7_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch7_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch7_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch7_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch7_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch7_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch7_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch7_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch7_lpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch7_lkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch7_sp.png"));
                    break;
                case 8:
                    up1 = ImageIO.read(getClass().getResource("/res/npc/ch8_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/npc/ch8_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/npc/ch8_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/npc/ch8_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/npc/ch8_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/npc/ch8_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/npc/ch8_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/npc/ch8_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/npc/ch8_lpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/npc/ch8_lkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/npc/ch8_sp.png"));
                    break;
                default:
                    // Fallback to character 1 if an unsupported choice is made
                    System.out.println("Invalid NPC choice: " + choice + ", defaulting to character 1");
                    getDummy(1);
                    break;
            }
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
        } else if (health <= 60 && health > 40) {
            healthIcon.image = healthImages[2];
        } else if (health <= 40 && health > 20) {
            healthIcon.image = healthImages[3];
        } else if (health <= 20 && health > 0) {
            healthIcon.image = healthImages[4];
        } else {
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
                // Move opposite direction if confused
                if (confused) {
                    worldX += moveSpeed;
                    direction = "right";
                } else {
                    worldX -= moveSpeed;
                    direction = "left";
                }
            } else {
                // Move opposite direction if confused
                if (confused) {
                    worldX -= moveSpeed;
                    direction = "left";
                } else {
                    worldX += moveSpeed;
                    direction = "right";
                }
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

    private void resetEffects() {
        stopMoving = false;
        moveSpeed = originalMoveSpeed;
        confused = false;
        effectType = 0;
    }

    private void updateEffects() {
        if (effectDuration > 0) {
            effectDuration--;

            // Apply continuous effects
            switch(effectType) {
                case Player.BURN_EFFECT:
                    if (effectDuration % 30 == 0) { // Apply burn damage every half second
                        health -= 1;
                        updateHealthIcon();
                    }
                    break;
                case Player.POISON_EFFECT:
                    if (effectDuration % 60 == 0) { // Apply poison damage every second
                        health -= 2;
                        updateHealthIcon();
                    }
                    break;
            }

            // Effect has ended
            if (effectDuration <= 0) {
                resetEffects();
            }
        }
    }

    public void update() {
        if (health <= 0) {
            if (!isDead) {
                isDead = true;
                deathEffect.startEffect(worldX, worldY);
            }
            deathEffect.update();
            return;
        }

        updateEffects();

        moveToPlayer();
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
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

        // Draw effect indicator if an effect is active
        if (effectDuration > 0) {
            // Different color based on effect type
            switch(effectType) {
                case Player.BURN_EFFECT:
                    g2.setColor(new Color(255, 50, 0, 150)); // Red for burn
                    break;
                case Player.FREEZE_EFFECT:
                    g2.setColor(new Color(0, 200, 255, 150)); // Blue for freeze
                    break;
                case Player.STUN_EFFECT:
                    g2.setColor(new Color(255, 255, 0, 150)); // Yellow for stun
                    break;
                case Player.POISON_EFFECT:
                    g2.setColor(new Color(0, 180, 0, 150)); // Green for poison
                    break;
                case Player.SLOW_EFFECT:
                    g2.setColor(new Color(150, 150, 150, 150)); // Gray for slow
                    break;
                case Player.CONFUSION_EFFECT:
                    g2.setColor(new Color(200, 0, 200, 150)); // Purple for confusion
                    break;
                default:
                    g2.setColor(new Color(255, 255, 255, 150)); // White for default
            }

            // Draw effect circle around character
            g2.fillOval(worldX - 5, worldY - 5, gp.tileSize * 2 + 10, gp.tileSize * 2 + 10);
        }

        if (healthIcon != null && healthIcon.image != null) {
            int healthBarX = worldX + (gp.tileSize - gp.tileSize) / 2;
            int healthBarY = worldY - (gp.tileSize / 2) - 20;
            g2.drawImage(healthIcon.image, 425, -150, gp.tileSize * 8, gp.tileSize * 8, null);
        }
    }
}