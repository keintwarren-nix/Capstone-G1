package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public interface Character{
    int cchoice = 0;

    default void getPlayerImage() {

    }

    default void getDummy() {

    }
//
//        switch (cchoice) {
//
//            case 0:
//
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 1:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 2:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 3:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 4:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 5:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 6:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            case 7:
//                try {
//                    up1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    up2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    left2 = ImageIO.read(getClass().getResource("/res/player/png"));
//                    right1 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    right2 = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    leftidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    rightidle = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    punch = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    kick = ImageIO.read(getClass().getResource("/res/player/.png"));
//                    sp = ImageIO.read(getClass().getResource("/res/player/.png"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                break;
//


}
