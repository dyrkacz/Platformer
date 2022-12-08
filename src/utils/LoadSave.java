package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String CAT_ATLAS = "cat.png";
    public static final String RABBIT_ATLAS = "rabbit.png";
    public static final String LEVEL_ONE_DATA = "level1.png";

    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static BufferedImage getLevelImage(){
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        return img;
    }

    public static int[][] getLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas("level1Data.png");

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j,i));
                int value = color.getRed();
//                System.out.println(value);
//                if( value >= 48){
//                    value = 0;
                    lvlData[i][j] = value;
//                }
                System.out.print(value);
            }
            System.out.println("");
        }
        return lvlData;
    }




}
