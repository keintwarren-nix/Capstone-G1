package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity implements Character{
    GamePanel gp;
    KeyHandler keyH;
    int attackCooldown = 1000; // 1 second cooldown
    boolean isJumping = false;
    int jumpHeight = 100;
    int jumpStartY;
    public boolean canAttack = true;
    public long lastAttackTime = 0;
    int damage = 5;

    public DeathEffect deathEffect;
    public boolean isDead = false;
    private boolean deathEffectStarted = false; // Track if the death effect has been started

    int attackCount = 0; // Track consecutive attacks
    int maxAttackCount = 3; // Limit 3 attacks
    int attackRange = 50; // Player must be within 50 pixels to deal damage

    private int characterChoice;
    public static final int BURN_EFFECT = 1;
    public static final int FREEZE_EFFECT = 2;
    public static final int STUN_EFFECT = 3;
    public static final int POISON_EFFECT = 4;
    public static final int DRAIN_EFFECT = 5;
    public static final int KNOCKBACK_EFFECT = 6;
    public static final int SLOW_EFFECT = 7;
    public static final int CONFUSION_EFFECT = 8;

    // Keep track of the special effect duration
    public int specialEffectDuration = 0;
    public int specialEffectType = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(8, 16, 32, 16);
        setDefaultValues();
        // Get player image using GamePanel's character choice
        getPlayerImage(gp.cchoice);
        // Store the character choice for special effect reference
        this.characterChoice = gp.cchoice;
        loadHealthImages();
        healthIcon = new Health();
        updateHealthIcon();
        deathEffect = new DeathEffect(gp, this);
    }

    public void applySpecialEffect(Dummy target) {
        // Apply different effects based on character selection
        switch(characterChoice) {
            case 1: // Fire effect - continuous damage over time
                target.health -= 3;
                target.applyEffect(BURN_EFFECT, 5);
                break;
            case 2: // Ice effect - freeze in place
                target.applyEffect(FREEZE_EFFECT, 3);
                break;
            case 3: // Lightning effect - stun
                target.applyEffect(STUN_EFFECT, 2);
                break;
            case 4: // Poison effect - slow damage over time
                target.applyEffect(POISON_EFFECT, 4);
                break;
            case 5: // Drain effect - heals player
                target.health -= 5;
                if(this.health < 100) {
                    this.health += 5;
                    if(this.health > 100) this.health = 100;
                }
                updateHealthIcon();
                break;
            case 6: // Strong knockback
                target.applyEffect(KNOCKBACK_EFFECT, 1);
                int pushDirection = (target.worldX > this.worldX) ? 1 : -1;
                target.worldX += pushDirection * 30;
                break;
            case 7: // Slow effect
                target.applyEffect(SLOW_EFFECT, 3);
                break;
            case 8: // Confusion effect - reverse controls
                target.applyEffect(CONFUSION_EFFECT, 4);
                break;
            default:
                target.health -= 10; // Default damage if no character selected
                break;
        }
        target.updateHealthIcon();
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 420;
        speed = 4;
        direction = "rightidle";
        health = 100;
        isDead = false;
        deathEffectStarted = false; // Reset death effect flag
    }

    @Override
    public void getPlayerImage(int choice) {
        // Your existing getPlayerImage method - no changes needed
        try {
            if (choice == 0) {
                choice = 1; // Default to character 1 if no choice was made
            }

            switch (choice) {
                case 1://Ariel
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch1_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch1_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch1_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch1_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch1_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch1_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch1_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch1_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch1_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch1_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch1_sp.png"));
                    break;
                // Cases 2-8 unchanged
                case 2://Cinderella
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch2_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch2_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch2_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch2_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch2_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch2_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch2_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch2_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch2_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch2_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch2_sp.png"));
                    break;
                case 3://Elsa
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch3_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch3_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch3_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch3_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch3_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch3_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch3_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch3_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch3_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch3_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch3_sp.png"));
                    break;
                case 4://Moana
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch4_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch4_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch4_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch4_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch4_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch4_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch4_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch4_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch4_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch4_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch4_sp.png"));
                    break;
                case 5://Mulan
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch5_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch5_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch5_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch5_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch5_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch5_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch5_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch5_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch5_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch5_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch5_sp.png"));
                    break;
                case 6://SnowWhite
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch6_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch6_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch6_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch6_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch6_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch6_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch6_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch6_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch6_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch6_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch6_sp.png"));
                    break;
                case 7://Tiana
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch7_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch7_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch7_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch7_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch7_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch7_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch7_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch7_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch7_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch7_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch7_sp.png"));
                    break;
                case 8://Rapunzel
                    up1 = ImageIO.read(getClass().getResource("/res/player/ch8_jleft.png"));
                    up2 = ImageIO.read(getClass().getResource("/res/player/ch8_jright.png"));
                    left1 = ImageIO.read(getClass().getResource("/res/player/ch8_lwalk1.png"));
                    left2 = ImageIO.read(getClass().getResource("/res/player/ch8_lwalk2.png"));
                    right1 = ImageIO.read(getClass().getResource("/res/player/ch8_rwalk1.png"));
                    right2 = ImageIO.read(getClass().getResource("/res/player/ch8_rwalk2.png"));
                    leftidle = ImageIO.read(getClass().getResource("/res/player/ch8_idle.png"));
                    rightidle = ImageIO.read(getClass().getResource("/res/player/ch8_idle.png"));
                    punch = ImageIO.read(getClass().getResource("/res/player/ch8_rpunch.png"));
                    kick = ImageIO.read(getClass().getResource("/res/player/ch8_rkick.png"));
                    sp = ImageIO.read(getClass().getResource("/res/player/ch8_sp.png"));
                    break;
                default:
                    System.out.println("Invalid player choice: " + choice);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHealthImages() {
        try {
            healthImages = new BufferedImage[6];
            healthImages[5] = ImageIO.read(getClass().getResource("/res/objects/1.png"));
            healthImages[4] = ImageIO.read(getClass().getResource("/res/objects/2.png"));
            healthImages[3] = ImageIO.read(getClass().getResource("/res/objects/3.png"));
            healthImages[2] = ImageIO.read(getClass().getResource("/res/objects/4.png"));
            healthImages[1] = ImageIO.read(getClass().getResource("/res/objects/5.png"));
            healthImages[0] = ImageIO.read(getClass().getResource("/res/objects/6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHealthIcon() {
        if (health == 100) {
            healthIcon.image = healthImages[5];
        } else if (health <= 80 && health > 60) {
            healthIcon.image = healthImages[4];
        } else if (health <= 60 && health > 40) {
            healthIcon.image = healthImages[3];
        } else if (health <= 40 && health > 20) {
            healthIcon.image = healthImages[2];
        } else if (health <= 20 && health > 0) {
            healthIcon.image = healthImages[1];
        } else {
            healthIcon.image = healthImages[0];
            // Check if we need to start death effect
            if (!deathEffectStarted && health <= 0) {
                isDead = true;
                deathEffectStarted = true;
                deathEffect.startEffect(worldX, worldY);
                System.out.println("Player death effect started at: " + worldX + ", " + worldY);
            }
        }
    }

    public void update() {
        // Check if player is dead
        if (health <= 0) {
            // Make sure death effect is started
            if (!deathEffectStarted) {
                isDead = true;
                deathEffectStarted = true;
                deathEffect.startEffect(worldX, worldY);
                System.out.println("Player death effect started in update at: " + worldX + ", " + worldY);
            }

            // Update the death effect animation
            deathEffect.update();
            return; // Skip player controls if dead
        }

        boolean keyPressed = keyH.up || keyH.right || keyH.left || keyH.punch || keyH.kick || keyH.sp;
        updateHealthIcon();

        // Handle cooldown after 3 attacks
        if (!canAttack && System.currentTimeMillis() - lastAttackTime >= attackCooldown) {
            canAttack = true;
            attackCount = 0; // Reset attack count after cooldown
        }

        if (keyPressed) {
            handleKeyInput();
        } else {
            if (!isJumping && worldY == 420) {
                direction = "rightidle";
            }
        }

        handleJump();
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    // Rest of the methods remain unchanged
    public void handleKeyInput() {
        String prevDirection = direction;

        if (keyH.up && worldY == 420 && !isJumping) {
            direction = "up";
            isJumping = true;
            jumpStartY = worldY;
        } else if (keyH.right) {
            direction = "right";
        } else if (keyH.left) {
            direction = "left";
        } else if (canAttack && attackCount < maxAttackCount) {
            if (keyH.punch) {
                performAttack("punch", 3);
            } else if (keyH.kick) {
                performAttack("kick", 4);
            } else if (keyH.sp) {
                performAttack("sp", 5);
            }
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        if (gp.dummy.health > 0) {
            gp.cChecker.checkEntity(this, gp.dummy);
        }

        if (!collisionOn) {
            movePlayer();
        } else {
            bounceBack(prevDirection);
        }

        gp.cChecker.checkScreenBoundaries(this);
    }

    public void movePlayer() {
        switch (direction) {
            case "right":
                worldX += speed;
                break;
            case "left":
                worldX -= speed;
                break;
        }
    }

    public void bounceBack(String prevDirection) {
        if (prevDirection.equals("right")) {
            worldX -= speed;
            direction = "left";
        } else if (prevDirection.equals("left")) {
            worldX += speed;
            direction = "right";
        }
        collisionOn = false;
    }

    public void performAttack(String attackType, int soundIndex) {
        direction = attackType;
        gp.sound.playSE(soundIndex);
        if (isDummyInRange()) {
            if(attackType.equals("sp")) {
                // Apply special effect based on character
                applySpecialEffect(gp.dummy);
            } else {
                dealDamage(); // Regular damage for normal attacks
            }
        }
        attackCount++;
        if (attackCount >= maxAttackCount) {
            canAttack = false;
            lastAttackTime = System.currentTimeMillis();
        }
    }

    public boolean isDummyInRange() {
        int dx = Math.abs(worldX - gp.dummy.worldX);
        int dy = Math.abs(worldY - gp.dummy.worldY);
        return dx < attackRange && dy < attackRange;
    }

    public void dealDamage() {
        if (gp.dummy.health > 0) {
            gp.dummy.health -= damage;
            gp.dummy.updateHealthIcon();
        }
    }

    public void handleJump() {
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
    }

    public void draw(Graphics2D g2) {
        // If player is dead, only draw the death effect and exit
        if (health <= 0) {
            deathEffect.draw(g2);
            return;
        }

        // Draw player normally if not dead
        BufferedImage image = rightidle;
        if (direction != null) {
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
        }

        if(direction == "sp"){
            g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
        else if(direction == "kick"){
            g2.drawImage(image, worldX, worldY, gp.tileSize * 3, gp.tileSize * 2, null);
        }
        else{
            g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        if (healthIcon != null && healthIcon.image != null) {
            g2.drawImage(healthIcon.image, -40, -150, gp.tileSize * 8, gp.tileSize * 8, null);
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            // Handle object pickup
        }
    }

    // Method to check if death animation is complete
    public boolean isDeathComplete() {
        return deathEffectStarted && deathEffect.isComplete();
    }
}