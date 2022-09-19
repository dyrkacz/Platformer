package main;

import inputs.KeyboardsInputs;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private int xDelta = 0;
    private int yDelta = 0;
    public GameScreen(){
            addKeyListener(new KeyboardsInputs(this));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(50 + xDelta,50 + yDelta, 100, 100);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }
}
