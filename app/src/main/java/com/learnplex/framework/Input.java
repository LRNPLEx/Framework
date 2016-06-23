package com.learnplex.framework;

/**
 * Created by David on 6/23/2016.
 */
public interface Input {
    public static class KeyEvent{
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public int keyChar;
    }
}
