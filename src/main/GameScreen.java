package main;

import inputs.KeyboardsInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameScreen extends JPanel {
    private int xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    public GameScreen() {
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardsInputs(this));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(0*64, 0*40, 64,40);
        g.drawImage(subImg, xDelta, yDelta, 128,80, null);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/cat1.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}
