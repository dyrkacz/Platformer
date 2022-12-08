package utils;

public class Constants {
    public static final int CAT_IDLE_AMOUNT = 9;
    public static final int CAT_RUNNING_AMOUNT = 5;
    public static final int CAT_HIGHFIVING_AMOUNT = 7;
    public static final int CAT_JUMPING_AMOUNT = 3;
    public static final int CAT_FALLING_AMOUNT = 1;
    public static final int RABBIT_IDLE_AMOUNT = 9;
    public static final int RABBIT_RUNNING_AMOUNT = 5;
    public static final int RABBIT_HIGHFIVING_AMOUNT = 1;
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int HIGHFIVING = 2;
        public static final int JUMPING = 3;
        public static final int FALLING = 4;

        public static int GetSpriteAmount(int playerAction){
            switch(playerAction){
                case IDLE:
                    return CAT_IDLE_AMOUNT;
//                    return RABBIT_IDLE_AMOUNT;
                case RUNNING:
                    return CAT_RUNNING_AMOUNT;
//                    return RABBIT_RUNNING_AMOUNT;
                case HIGHFIVING:
                    return CAT_HIGHFIVING_AMOUNT;
//                    return RABBIT_HIGHFIVING_AMOUNT;
                case JUMPING:
                    return CAT_JUMPING_AMOUNT;
                case FALLING:
                    return CAT_FALLING_AMOUNT;
                default:
                    return 1;
            }
        }
    }
}
