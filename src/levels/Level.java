package levels;

import java.awt.image.BufferedImage;

public class Level {
    private BufferedImage img;
    private int[][] lvlData;

    public Level(BufferedImage img, int[][] lvlData){
        this.img = img;
        this.lvlData = lvlData;
    }

    public BufferedImage getLevel(){
        return this.img;
    }
    public int getSpriteIndex(int x, int y){
        return lvlData[y][x];
    }
    public int[][] getLvlData(){
        return lvlData;
    }
}
