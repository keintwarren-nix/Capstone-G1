package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Health extends SuperObject {
    public BufferedImage[] healthImages = new BufferedImage[5];

    public Health() {
        name = "hp";
        loadHealthImages();
    }

    public void loadHealthImages() {
        try {
            healthImages[4] = ImageIO.read(getClass().getResource("/res/objects/full.png"));
            healthImages[3] = ImageIO.read(getClass().getResource("/res/objects/thirdfourth.png"));
            healthImages[2] = ImageIO.read(getClass().getResource("/res/objects/half.png"));
            healthImages[1] = ImageIO.read(getClass().getResource("/res/objects/onefourth.png"));
            healthImages[0] = ImageIO.read(getClass().getResource("/res/objects/dead.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
