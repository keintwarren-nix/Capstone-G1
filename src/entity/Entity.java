package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x,y,speed;

    public int worldX, worldY;
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;
    public BufferedImage leftidle;
    public BufferedImage rightidle;
    public BufferedImage punch;
    public BufferedImage kick;
    public BufferedImage sp;
    public BufferedImage stand;

    public String direction;

    public Rectangle solidArea;
    public boolean collisionOn = false;


    public int spriteCounter = 0, spriteNum = 1;

    public Entity(GamePanel gp) {
    }
}
