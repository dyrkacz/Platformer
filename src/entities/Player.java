package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Helper.canMoveHere;

import static utils.Constants.PlayerConstants.*;
import static utils.Helper.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, highFiving = false;
    private boolean left, right, up, down, jump;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 40 * Game.SCALE;
    private float yDrawOffset = 18 * Game.SCALE;
    public static final int CAT_ATLAS_COLUMNS = 9;
    public static final int CAT_ATLAS_ROWS = 5;
    public static final int RABBIT_ATLAS_COLUMNS = 9;
    public static final int RABBIT_ATLAS_ROWS = 2;

    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -3.5f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x,y,56*Game.SCALE, 50 *Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        drawHitbox(g);
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.CAT_ATLAS);
//        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.RABBIT_ATLAS);
        animations = new BufferedImage[CAT_ATLAS_ROWS][CAT_ATLAS_COLUMNS];
//        animations = new BufferedImage[RABBIT_ATLAS_ROWS][RABBIT_ATLAS_COLUMNS];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 128, i * 80, 128, 80);
            }
        }
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!isEntityOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
    }


    public void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                highFiving = false;
            }
        }
    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if (highFiving)
            playerAction = HIGHFIVING;
        if (inAir){
             if(airSpeed < 0){
                 playerAction = JUMPING;
             } else {
                 playerAction = FALLING;
             }
        }
        if(highFiving){
            playerAction = HIGHFIVING;
        }
        if (startAni != playerAction) {
            resetAnimationTick();
        }
    }

    private void updatePos() {
        moving = false;

        if(jump){
            jump();
        }
        if (!left && !right && !inAir) {
            return;
        }

        float xSpeed = 0;

        if(left) {
            xSpeed -= playerSpeed;
        }
        if(right) {
            xSpeed += playerSpeed;
        }
        if (!inAir) {
            if(!isEntityOnFloor(hitbox,lvlData)){
                inAir = true;
            }
        }
        if(inAir){
            if(canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if(airSpeed > 0){
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                    updateXPos(xSpeed);
                }
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
//        		if (canMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
//
//        if (canMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed,(int) hitbox.width,(int) hitbox.height, lvlData)) {
//            hitbox.x += xSpeed;
//            hitbox.y += ySpeed;
//            moving = true;
//        }
    }

    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    private void updateXPos(float xSpeed){
            if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
                hitbox.x += xSpeed;
            } else {
                hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
            }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setHighFiving(boolean highFiving) {
        this.highFiving = highFiving;
    }

    public void setJump(boolean jump){
        this.jump = jump;
    }
}
