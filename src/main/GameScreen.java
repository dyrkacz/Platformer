package main;

import inputs.KeyboardsInputs;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;


    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardsInputs(this));
    }



    public Game getGame(){
        return game;

    }

    public void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }
}

