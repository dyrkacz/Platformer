package main;

import entities.Player;
import levels.LevelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1f;
    public final static int TILES_IN_WIDTH = 40;
    public final static int TILES_IN_HEIGHT = 25;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    public Game() {
        initClasses();
        createScreen();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void initClasses(){
        levelManager = new LevelManager(this);
        player = new Player(200,200, (int) (128 * SCALE) ,(int) (80 * SCALE) );
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    private void createScreen() {
        gameScreen = new GameScreen(this);
        add(gameScreen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                windowFocusLost();
            }
        });
        gameScreen.setFocusable(true);
        gameScreen.requestFocus();
    }

    public void render(Graphics g){
        levelManager.draw(g);
        player.render(g);
    }
    public void update() {
        player.update();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        double deltaF = 0;
        double deltaU = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if (deltaF >= 1) {
                gameScreen.repaint();
                frames++;
                deltaF--;
            }
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    private void windowFocusLost(){
        player.resetDirectionBooleans();
    }
    public Player getPlayer(){
        return player;
    }
}
