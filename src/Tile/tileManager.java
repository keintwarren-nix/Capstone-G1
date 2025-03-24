package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class tileManager {

    GamePanel gp;
    Tile[] tile;
    public BufferedImage imgDummy;
    public boolean dummycol = false;

    public tileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/res/tiles/tile.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g2) throws IOException {
    int col = 0, row = 0,x = 0,y = 500;


    while (col < gp.maxScreenCol && row < gp.maxScreenRow){

        g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
        col++;
        x += gp.tileSize;

        if(col == gp.maxScreenCol){
            col = 0;
            x=0;
            row++;
            y+= gp.tileSize;
        }

        }

        imgDummy = ImageIO.read(getClass().getResource("/res/objects/dummy.png"));
        g2.drawImage(imgDummy, 520, 424, gp.tileSize+32, gp.tileSize+32,null);


    }
}
