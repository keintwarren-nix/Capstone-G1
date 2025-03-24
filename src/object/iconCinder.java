package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class iconCinder extends SuperObject{
    public iconCinder(GamePanel gp){

        name = "iconCinderella";
        try{
            image = ImageIO.read(getClass().getResource("/objects/cinder.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}