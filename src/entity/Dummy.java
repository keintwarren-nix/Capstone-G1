package entity;

import main.GamePanel;
import main.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public  class Dummy extends Entity{


    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;
    BufferedImage image;

    public Dummy(GamePanel gp) {
        super(gp);
        this.gp = gp;

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

        getDummy();
    }

    public void getDummy(){
        try{
            stand = ImageIO.read(getClass().getResource("/res/objects/dummy.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        direction = "stand";
        collisionOn = false;
    }

    public void draw(Graphics2D g2){
        image = stand;
        g2.drawImage(stand, 520, 424, gp.tileSize+32, gp.tileSize+32,null);
    }
}
