package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Icon extends SuperObject{
    public Icon(){

        name = "snowWhite";
        try{
            image = ImageIO.read(getClass().getResource("/res/objects/snowwhite.jpg"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}