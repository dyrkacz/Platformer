package levels;

import java.awt.image.BufferedImage;

public class Level {
    private BufferedImage img;

    public Level(BufferedImage img){
        this.img = img;
    }
    public BufferedImage getLevel(){
        return this.img;
    }
}
