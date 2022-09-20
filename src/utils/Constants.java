package utils;

public class Constants {
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;

        public static int GetSpriteAmount(int playerAction){
            switch(playerAction){
                case IDLE:
                    return 8;
                case RUNNING:
                    return 5;
                default:
                    return 1;
            }
        }
    }
}
