package entity;

import main.GamePanel;
import main.KeyHandler;
import main.KeyHandler2;
import object.Health;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity implements Character{
    GamePanel gp;
    KeyHandler keyH;
    int attackCooldown = 1000; // 1 second cooldown
    public boolean isJumping = false;
    int jumpHeight = 100;
    int jumpStartY;
    public boolean canAttack = true;
    public long lastAttackTime = 0;
    KeyHandler2 keyH2;
    public DeathEffect deathEffect;
    public boolean isDead = false;

    int attackCount = 0; // Track consecutive attacks
    int maxAttackCount = 3; // Limit 3 attacks
    int attackRange = 50; // Player must be within 50 pixels to deal damage
    public int playerNum;
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
        this.keyH2 = null;
        solidArea = new Rectangle(8, 16, 32, 16);
        setDefaultValues();
        // Get player image using GamePanel's character choice
        getPlayerImage(gp.cchoice);

        getPlayerImagePVP(gp.cchoice);
        // Store the character choice for special effect reference
        this.characterChoice = gp.cchoice;
        loadHealthImages();
        healthIcon = new Health();
        updateHealthIcon();
        deathEffect = new DeathEffect(gp, this);
    }
    public Player(GamePanel gp, KeyHandler2 keyH2) {
        super(gp);
        this.gp = gp;
        this.keyH = null; // Player 2 doesn't use KeyHandler
        this.keyH2 = keyH2;
        solidArea = new Rectangle(8, 16, 32, 16);
        setDefaultValues();
        getPlayerImage(gp.cchoice); // Assuming both players can choose the same characters for now
        this.characterChoice = gp.cchoice;
        loadHealthImages();
        healthIcon = new Health();
        updateHealthIcon();
        deathEffect = new DeathEffect(gp, this);
    }
    public boolean isTargetInRange(Entity target) {
        boolean inRange = false;

        // Implement your range checking logic here
        if (target != null) {
            int distanceX = Math.abs(worldX - target.worldX);
            int distanceY = Math.abs(worldY - target.worldY);

            // Example: Check if the target is within 2 tiles in both X and Y directions
            int attackRangeTile = 2;
            int attackRangePixel = attackRangeTile * gp.tileSize; // Assuming gp.tileSize is accessible

            if (distanceX <= attackRangePixel && distanceY <= attackRangePixel) {
                inRange = true;
            }
        }

        return inRange;
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
    public void applySpecialEffect(Entity target) {
        if (target instanceof Dummy) {
            Dummy dummyTarget = (Dummy) target;
            // ... special effect logic for Dummy ...
        } else if (target instanceof Player) {
            Player playerTarget = (Player) target;
            // ... special effect logic for Player ...
        }
    }
    public void setDefaultValues() {
        worldX = 100;
        worldY = 420;
        speed = 4;
        direction = "rightidle";
        health = 100;
        isDead = false;
    }
    public void setDefaultValues2() {
        worldX = 550;
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
                case 1:
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
                case 2:
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
                case 3:
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
                case 4:
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
                case 5:
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
                case 6:
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
                case 7:
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
                case 8:
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
    public void getPlayerImagePVP(int choice) {
        try {

        if (playerNum == 2) {
            if (choice == 0) {
                choice = 1; // Default to character 1 if no choice was made
            }
                   switch (choice) {
                       case 1:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch1_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch1_lkick.png"));
                           break;
                       case 2:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch2_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch2_lkick.png"));
                           break;
                       case 3:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch3_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch3_lkick.png"));
                           break;
                       case 4:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch4_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch4_lkick.png"));
                           break;
                       case 5:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch5_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch5_lkick.png"));
                           break;
                       case 6:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch6_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch6_lkick.png"));
                           break;
                       case 7:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch7_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch7_lkick.png"));
                           break;
                       case 8:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch8_lpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch8_lkick.png"));
                           break;
                       default:
                           System.out.println("Invalid player choice: " + choice);
                           break;
                   }

        }
        else if
        (playerNum == 1) {
                   switch (choice) {
                       case 1:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch1_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch1_rkick.png"));
                           break;
                       case 2:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch2_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch2_rkick.png"));
                           break;
                       case 3:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch3_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch3_rkick.png"));
                           break;
                       case 4:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch4_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch4_rkick.png"));
                           break;
                       case 5:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch5_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch5_rkick.png"));
                           break;
                       case 6:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch6_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch6_rkick.png"));
                           break;
                       case 7:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch7_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch7_rkick.png"));
                           break;
                       case 8:
                           punch = ImageIO.read(getClass().getResource("/res/player/ch8_rpunch.png"));
                           kick = ImageIO.read(getClass().getResource("/res/player/ch8_rkick.png"));
                           break;
                       default:
                           System.out.println("Invalid player choice: " + choice);
                           break;
                   }
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
        }else if (health <= 60 && health > 40) {
            healthIcon.image = healthImages[3];
        }else if (health <= 40 && health > 20) {
            healthIcon.image = healthImages[2];
        }else if (health <= 20 && health > 0) {
            healthIcon.image = healthImages[1];
        }else{
            healthIcon.image = healthImages[0];
            // Check if we need to start death effect
            if (!isDead && health <= 0) {
                isDead = true;
                deathEffect.startEffect(worldX, worldY);
            }
        }
    }

    public void update() throws IOException {
        // Check if player is dead
        if (health <= 0) {
            isDead = true;
            deathEffect.update();
            return; // Skip player controls if dead
        }

        updateHealthIcon();

        if (!canAttack) {
//            System.out.println("canAttack is false. Checking cooldown...");
            if (System.currentTimeMillis() - lastAttackTime >= attackCooldown) {
                System.out.println("Cooldown finished. Resetting canAttack and attackCount.");
                canAttack = true;
                attackCount = 0;
            } else {
                System.out.println("Cooldown still active: " + (System.currentTimeMillis() - lastAttackTime) + " / " + attackCooldown);
            }
        } else {
//            System.out.println("canAttack is true.");
        }
        if (playerNum == 1) {
            if (keyH.up || keyH.right || keyH.left || keyH.punch || keyH.kick || keyH.sp) {
                handleKeyInput(keyH, (gp.player2 != null && gp.gameState == gp.playerVsPlayerPlayState) ? gp.player2 : gp.dummy);
            }
        } else if (playerNum == 2) {

            if (keyH2 != null && (keyH2.p2UpPressed || keyH2.p2RightPressed || keyH2.p2LeftPressed || keyH2.p2DownPressed || keyH2.p2PunchPressed || keyH2.p2KickPressed || keyH2.p2SpecialPressed)) {
                handleKeyInput2(keyH2, gp.player);
            }
        } else if (!isJumping && worldY == 420) {
            direction = direction.replace("left", "leftidle").replace("right", "rightidle");
        }

        handleJump();
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void handleKeyInput(KeyHandler kh, Entity target) {
        String prevDirection = direction;
        boolean moving = false;

        if (keyH.up && worldY == 420 && !isJumping) {
            direction = "up";
            isJumping = true;
            jumpStartY = worldY;
            moving = true;
        } else if (keyH.right) {
            direction = "right";
            moving = true;
        } else if (keyH.left) {
            direction = "left";
            moving = true;
        } else if (canAttack && attackCount < maxAttackCount) {
            if (keyH.punch) {
                performAttack("punch", 3,target);
            } else if (keyH.kick) {
                performAttack("kick", 4,target);
            } else if (keyH.sp) {
                performAttack("sp", 5,target);
            }
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        if (gp.player2 != null && gp.player2.health > 0) {
            gp.cChecker.checkEntity(this, gp.player2);
        } else if (gp.dummy != null && gp.dummy.health > 0) {
            gp.cChecker.checkEntity(this, gp.dummy);
        }

        if (!collisionOn && moving) {
            movePlayer();
        } else if (!moving && !isJumping && (direction.equals("left") || direction.equals("right"))) {
            direction = direction.replace("left", "leftidle").replace("right", "rightidle");
        } else if (!collisionOn && isJumping) {
            movePlayer(); // Still allow movement while jumping if keys are held
        } else if (!moving && !isJumping && direction.equals("up")) {
            direction = direction.replace("up", (prevDirection.contains("left")) ? "leftidle" : "rightidle");
        }


        gp.cChecker.checkScreenBoundaries(this);
    }
    public void handleKeyInput2(KeyHandler2 kh, Entity target) {
        String prevDirection = direction;
        boolean moving = false;

        if (kh.p2UpPressed&& worldY == 420 && !isJumping) {
            direction = "up";
            isJumping = true;
            jumpStartY = worldY;
            moving = true;
        } else if (kh.p2RightPressed) {
            direction = "right";
            moving = true;
        } else if (kh.p2LeftPressed) {
            direction = "left";
            moving = true;
        } else if (kh.p2DownPressed) { // Example of another action for Player 2
            speed = 8; // Temporary speed boost
        } else if (canAttack && attackCount < maxAttackCount) {
            if (kh.p2PunchPressed) {
                performAttack("punch", 3, target);
            } else if (kh.p2KickPressed) {
                performAttack("kick", 4, target);
            } else if (kh.p2SpecialPressed) {
                performAttack("sp", 5, target);
            }
        } else {
            speed = 4; // Reset speed
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        if (gp.player != null && gp.player.health > 0) {
            gp.cChecker.checkEntity(this, gp.player);
        }


        if (!collisionOn && moving) {
            movePlayer();
        } else if (!moving && !isJumping && (direction.equals("left") || direction.equals("right"))) {
            direction = direction.replace("left", "leftidle").replace("right", "rightidle");
        } else if (!collisionOn && isJumping) {
            movePlayer(); // Still allow movement while jumping if keys are held
        } else if (!moving && !isJumping && direction.equals("up")) {
            direction = direction.replace("up", (prevDirection.contains("left")) ? "leftidle" : "rightidle");
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

    public void performAttack(String attackType, int soundIndex, Entity target) {
        direction = attackType;
        gp.sound.playSE(soundIndex);
        if (isTargetInRange(target)) {
            if (attackType.equals("sp")) {
                if (target instanceof Dummy) {
                    applySpecialEffect((Dummy) target);
                } else if (target instanceof Player) {
                    applySpecialEffect((Player) target);
                }
            } else {
                dealDamage(target); // Regular damage for normal attacks
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

    public void dealDamage(Entity target) {
        if (gp.dummy.health > 0) {
            gp.dummy.health -= 9;
            gp.dummy.updateHealthIcon();
        }
    }
    public void updateSprite() {
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
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
        }else{
            g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);
        }

        if (healthIcon != null && healthIcon.image != null) {
            if (this == gp.player) {
                g2.drawImage(healthIcon.image, -40, -150, gp.tileSize * 8, gp.tileSize * 8, null); // Player 1's health
            } else if (this == gp.player2) {
                g2.drawImage(healthIcon.image, gp.screenWidth - (gp.tileSize * 8) + 40, -150, gp.tileSize * 8, gp.tileSize * 8, null); // Player 2's health
            }
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            // Handle object pickup
        }
    }
}