package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;

public class LevelManager {

    private Game game;
    private Level levelOne;

    public LevelManager(Game game){
        this.game = game;
        levelOne = new Level(LoadSave.getLevelData());
    }

    public void draw(Graphics g){
        g.drawImage(levelOne.getLevel(), 0, 0, (int) (Game.TILES_IN_WIDTH*Game.TILES_DEFAULT_SIZE*Game.SCALE),(int) (Game.TILES_IN_HEIGHT*Game.TILES_DEFAULT_SIZE* Game.SCALE), null);
    }


}
