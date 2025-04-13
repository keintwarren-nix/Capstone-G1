package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends SuperObject {
    public BufferedImage[] projectileImages = new BufferedImage[5];

    public Projectile() {
        name = "projectile";
        loadProjectileImages();
    }

    public void loadProjectileImages() {
        try {
        projectileImages = new BufferedImage[4];
        projectileImages[0] = ImageIO.read(getClass().getResource("/res/projectile/lightrayy_left1.png"));
        projectileImages[1] = ImageIO.read(getClass().getResource("/res/projectile/lightrayy_left2.png"));
        projectileImages[2] = ImageIO.read(getClass().getResource("/res/projectile/lightrayy_right1.png"));
        projectileImages[3] = ImageIO.read(getClass().getResource("/res/projectile/lightrayy_right2.png"));
    } catch (IOException e) {
        e.printStackTrace();
     }

    }
}

