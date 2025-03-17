package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y,speed;

    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;
    public BufferedImage leftidle;
    public BufferedImage rightidle;
    public String direction;

    public int spriteCounter = 0, spriteNum = 1;

    public Entity(GamePanel gp) {
    }
}
