package inputs;

import main.GameScreen;

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

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                gameScreen.changeYDelta(-5);
                break;
            case KeyEvent.VK_LEFT:
                gameScreen.changeXDelta(-5);
                break;
            case KeyEvent.VK_DOWN:
                gameScreen.changeYDelta(5);
                break;
            case KeyEvent.VK_RIGHT:
                gameScreen.changeXDelta(5);
                break;
        }
    }
}
