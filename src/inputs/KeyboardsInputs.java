package inputs;

import main.GameScreen;

import static utils.Constants.Directions.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardsInputs implements KeyListener {
    GameScreen gameScreen;

    public KeyboardsInputs(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP:
//                gameScreen.getGame().getPlayer().setUp(true);
//                break;
            case KeyEvent.VK_LEFT:
                gameScreen.getGame().getPlayer().setLeft(true);
                break;
//            case KeyEvent.VK_DOWN:
//                gameScreen.getGame().getPlayer().setDown(true);
//                break;
            case KeyEvent.VK_RIGHT:
                gameScreen.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gameScreen.getGame().getPlayer().setJump(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            gameScreen.getGame().getPlayer().setHighFiving(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP:
//                gameScreen.getGame().getPlayer().setUp(false);
//                break;
            case KeyEvent.VK_LEFT:
                gameScreen.getGame().getPlayer().setLeft(false);
                break;
//            case KeyEvent.VK_DOWN:
//                gameScreen.getGame().getPlayer().setDown(false);
//                break;
            case KeyEvent.VK_RIGHT:
                gameScreen.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gameScreen.getGame().getPlayer().setJump(false);
                break;
        }
    }
}
