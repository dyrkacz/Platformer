package inputs;

import main.GameScreen;
import static utils.Constants.Directions.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardsInputs implements KeyListener {
    GameScreen gameScreen;
    public KeyboardsInputs(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                gameScreen.setDirection(UP);
                break;
            case KeyEvent.VK_LEFT:
                gameScreen.setDirection(LEFT);
                break;
            case KeyEvent.VK_DOWN:
                gameScreen.setDirection(DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                gameScreen.setDirection(RIGHT);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_RIGHT:
                gameScreen.setMoving(false);
                break;
        }
    }
}
