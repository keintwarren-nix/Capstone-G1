package entity;
import main.GamePanel;
import main.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public  class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 420;
        speed = 4;
        direction = "rightidle";
    }

    public void getPlayerImage(){

        try{

            up1 = ImageIO.read(getClass().getResource("/res/player/snowwhite_jump.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player/snowwhite_jump.png"));
            left1 = ImageIO.read(getClass().getResource("/res/player/snowwhite_leftwalk_1.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player/snowwhite_leftwalk_2.png"));
            right1 = ImageIO.read(getClass().getResource("/res/player/snowwhite_rightwalk_1.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player/snowwhite_rightwalk_2.png"));
            leftidle = ImageIO.read(getClass().getResource("/res/player/snowwhite_idle_left.png"));
            rightidle = ImageIO.read(getClass().getResource("/res/player/snowwhite_idle_right.png"));


        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void update(){

        if(keyH.up ||  keyH.right || keyH.left){
            if(keyH.up) {
                direction = "up";
                y -= (speed*2);
            } else if (keyH.right) {
                direction = "right";
                x += speed;
            } else if (keyH.left) {
                direction = "left";
                x -= speed;
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if ( y != 420) {
            y += speed;
        }

    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.black);
//        g2.fillRect(x,y, gp.tileSize, gp.tileSize);
        BufferedImage image = rightidle;

        switch (direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize*2, gp.tileSize*2, null);
    }
}
