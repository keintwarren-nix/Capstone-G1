package main;

import java.awt.*;

public class Ui {

    GamePanel gp;
    Font arial_40;

    public Ui(GamePanel gp){
        this.gp = gp;

        arial_40 =  new Font("Arial", Font.PLAIN, 40);

    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Key = " + gp.player.hasKey,50 ,50);
    }
}
