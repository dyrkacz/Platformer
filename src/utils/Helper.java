package utils;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Helper {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!isSolid(x, y, lvlData)) {
            if (!isSolid(x + width, y + height, lvlData)) {
                if (!isSolid(x + width, y, lvlData)) {
                    if (!isSolid(x, y + height, lvlData)) {
                        if (!isSolid(x + (width / 2), y, lvlData)) {
                            if (!isSolid(x + (width / 2), y + height, lvlData)) {
                                if (!isSolid(x, y + (height / 2), lvlData)) {
                                    if (!isSolid(x + width, y + (height / 2), lvlData)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];
        if (value != 0) {
            return true;
        }
        return false;

    }
    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTileRight = (int) (hitbox.x / Game.TILES_SIZE) + 1;
        int currentTileLeft = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            //Right
            int tileXPos = currentTileRight * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            //Left
            return currentTileLeft * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTileRoof = (int) (hitbox.y / Game.TILES_SIZE);
        int currentTileFloor = (int) (hitbox.y / Game.TILES_SIZE) + 1;
        if (airSpeed > 0) {
            //Falling - touching floor
            int tileYPos = currentTileFloor * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            //jumping
            return currentTileRoof * Game.TILES_SIZE;
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        //Check the pixel below bottomleft and bottomright
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }
}
