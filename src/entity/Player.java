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
    int damage = 5;
    int attackCooldown = 1000; // 1 second cooldown
    boolean isJumping = false;
    int jumpHeight = 100;
    int jumpStartY;
    public boolean canAttack = true;
    public long lastAttackTime = 0;

    public DeathEffect deathEffect;
    public Effects specialEffect;
    public boolean isDead = false;

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

    // Special ability properties
    public static final long SPECIAL_COOLDOWN = 15000; // 15 seconds in milliseconds
    public static long lastSpecialTime = 0;
    public static boolean specialReady = true;
    private boolean specialActive = false;
    private long specialActiveStartTime = 0;
    private int specialActiveDuration = 1000; // 1 second for visual effect

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
        specialEffect = new Effects(gp, this);
    }

    // New methods for special ability management
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

    public void useSpecialAbility() {
        if (canUseSpecial()) {
            direction = "sp";
            specialActive = true;
            specialActiveStartTime = System.currentTimeMillis();

            // Apply special effect based on character
            if (isDummyInRange()) {
                applySpecialEffect(gp.dummy);
            }

            // Start visual effect
            specialEffect.startEffect(worldX, worldY);

            // Trigger cooldown
            triggerSpecialCooldown();

            // Play sound effect
            gp.sound.playSE(5);
        }
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
    }

    @Override
    public void getPlayerImage(int choice) {
        try {
            if (choice == 0) {
                choice = 1; // Default to character 1 if no choice was made
            }

            switch (choice) {
                case 1://ARIEL
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

                    this.health = 120;
                    this.damage = 7;
                    this.speed = 1;

                    break;

                case 2://CINDERELLA
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
                    this.health = 85;
                    this.damage = 10;
                    this.speed = 5;

                    break;

                case 3://ELSA
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

                    this.health = 75;
                    this.damage = 12;
                    this.speed = 4;

                    break;
                case 4://MOANA
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

                    this.health = 150;
                    this.damage = 5;
                    this.speed = 4;

                    break;
                case 5://MULAN
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

                    this.health = 60;
                    this.damage = 15;
                    this.speed = 6;

                    break;
                case 6://SNOW WHITE
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
                    this.health = 145;
                    this.damage = 9;
                    this.speed = 3;
                    break;
                case 7://TIANA
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
                    this.health = 160;
                    this.damage = 5;
                    this.speed = 3;
                    break;
                case 8://RAPUNZEL
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
                    this.health = 100;
                    this.damage = 9;
                    this.speed = 5;
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

        float healthPercent = (float) health / maxHealth;

        if (healthPercent == 1.0f) {
            healthIcon.image = healthImages[5];
        } else if (healthPercent > 0.8f) {
            healthIcon.image = healthImages[4];
        } else if (healthPercent > 0.6f) {
            healthIcon.image = healthImages[3];
        } else if (healthPercent > 0.4f) {
            healthIcon.image = healthImages[2];
        } else if (healthPercent > 0.2f) {
            healthIcon.image = healthImages[1];
        } else {
            healthIcon.image = healthImages[0];

            // Check if we need to start death effect
            if (!isDead && health <= 0) {
                isDead = true;
                deathEffect.startEffect(worldX, worldY);
            }
        }
    }


    public void update() {
        // Check if player is dead
        if (health <= 0) {
            isDead = true;
            deathEffect.update();
            return; // Skip player controls if dead
        }

        // Update special effect
        specialEffect.update();

        // Check if special ability is active
        if (specialActive) {
            if (System.currentTimeMillis() - specialActiveStartTime >= specialActiveDuration) {
                specialActive = false;
                // Return to idle state after special
                direction = "rightidle";
            }
        }

        boolean keyPressed = keyH.up || keyH.right || keyH.left || keyH.punch || keyH.kick || keyH.sp;
        updateHealthIcon();

        // Handle cooldown after 3 attacks
        if (!canAttack && System.currentTimeMillis() - lastAttackTime >= attackCooldown) {
            canAttack = true;
            attackCount = 0; // Reset attack count after cooldown
        }

        if (keyPressed && !specialActive) {
            handleKeyInput();
        } else if (!specialActive && !isJumping && worldY == 420) {
            direction = "rightidle";
        }

        handleJump();
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

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
        } else if (keyH.sp && canUseSpecial()) {
            // Use special ability
            useSpecialAbility();
        } else if (canAttack && attackCount < maxAttackCount) {
            if (keyH.punch) {
                performAttack("punch", 3);
            } else if (keyH.kick) {
                performAttack("kick", 4);
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
            dealDamage(); // Regular damage for normal attacks
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
//            gp.sound.playSE(2);
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
        // Draw death effect if player is dead
        if (health <= 0) {
            deathEffect.draw(g2);
            return;
        }

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
            g2.drawImage(image, worldX, worldY, gp.tileSize * 3, gp.tileSize * 2, null);
            specialEffect.draw(g2); // Draw special effect
        } else {
            g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        // Draw special ability cooldown indicator
        // Draw special ability cooldown indicator
        if (!canUseSpecial()) {
            // Modified cooldown bar dimensions and position
            int cooldownBarWidth = 120; // Increased width
            int cooldownBarHeight = 10; // Increased height
            int cooldownBarX = 50; // Position horizontally aligned with health bar
            int cooldownBarY = 30; // Position below health bar

            // Draw background bar
            g2.setColor(new Color(100, 100, 100, 180)); // Darker background
            g2.fillRect(cooldownBarX, cooldownBarY, cooldownBarWidth, cooldownBarHeight);

            // Calculate and draw remaining cooldown
            double cooldownPercentage = (double)getRemainingCooldown() / SPECIAL_COOLDOWN;
            int remainingWidth = (int)(cooldownBarWidth * cooldownPercentage);
            g2.setColor(new Color(0, 255, 125, 220)); // Brighter blue
            g2.fillRect(cooldownBarX + 20, cooldownBarY + 55, remainingWidth + cooldownBarWidth/2, cooldownBarHeight*2);

            // Add border
            g2.setColor(new Color(0, 0, 0, 200));
            g2.drawRect(cooldownBarX + 20, cooldownBarY + 55, cooldownBarWidth + cooldownBarWidth/2, cooldownBarHeight*2);
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
}