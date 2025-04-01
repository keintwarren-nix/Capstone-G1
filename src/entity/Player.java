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

    public DeathEffect deathEffect;
    public boolean isDead = false;

    int attackCount = 0; // Track consecutive attacks
    int maxAttackCount = 3; // Limit 3 attacks
    int attackRange = 50; // Player must be within 50 pixels to deal damage

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(8, 16, 32, 16);
        setDefaultValues();
        getPlayerImage();
        loadHealthImages();
        healthIcon = new Health();
        deathEffect = new DeathEffect(gp, this);
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
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResource("/res/player/jumpleft_moana.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player/jumpright_moana.png"));
            left1 = ImageIO.read(getClass().getResource("/res/player/leftwalk1_moana.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player/leftwalk2_moana.png"));
            right1 = ImageIO.read(getClass().getResource("/res/player/rightwalk1_moana.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player/rightwalk2_moana.png"));
            leftidle = ImageIO.read(getClass().getResource("/res/player/idle_moana.png"));
            rightidle = ImageIO.read(getClass().getResource("/res/player/idle_moana.png"));
            punch = ImageIO.read(getClass().getResource("/res/player/punchright_moana.png"));
            kick = ImageIO.read(getClass().getResource("/res/player/punchright_moana.png"));
            sp = ImageIO.read(getClass().getResource("/res/player/special_moana.png"));
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
        if (health == 100) {
            healthIcon.image = healthImages[4];
        } else if (health <= 75 && health > 50) {
            healthIcon.image = healthImages[3];
        } else if (health <= 50 && health > 25) {
            healthIcon.image = healthImages[2];
        } else if (health <= 25 && health > 0) {
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
            dealDamage(); // Apply damage only if within range
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
            gp.dummy.health -= 9;
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
        }else{
            g2.drawImage(image, worldX, worldY, gp.tileSize * 2, gp.tileSize * 2, null);
        }




        if (healthIcon != null && healthIcon.image != null) {
            g2.drawImage(healthIcon.image, 30, -150, gp.tileSize * 8, gp.tileSize * 8, null);
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            // Handle object pickup
        }
    }
}