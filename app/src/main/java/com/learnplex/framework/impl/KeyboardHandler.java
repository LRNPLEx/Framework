package com.learnplex.framework.impl;


import android.view.KeyEvent;
import android.view.View;

import com.learnplex.framework.Input;
import com.learnplex.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 7/2/2016.
 */
public class KeyboardHandler implements View.OnKeyListener {
    boolean[] pressedKeys = new boolean[128];
    Pool<Input.KeyEvent>keyEventPool;
    List<Input.KeyEvent>keyEventsBuffer = new ArrayList<Input.KeyEvent>();
    List<Input.KeyEvent>keyEvents = new ArrayList<Input.KeyEvent>();

    public KeyboardHandler(View view){
        Pool.PoolObjectFactory<Input.KeyEvent>factory = new Pool.PoolObjectFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };
    keyEventPool = new Pool<Input.KeyEvent>(factory, 100);
    view.setOnKeyListener(this);
    view.setFocusableInTouchMode(true);
    view.requestFocus();
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if(event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;

        synchronized (this){
            Input.KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = event.getUnicodeChar();
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                keyEvent.type = Input.KeyEvent.KEY_DOWN;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] = true;
            }
            if(event.getAction() == KeyEvent.ACTION_UP){
                keyEvent.type = Input.KeyEvent.KEY_UP;
                if(keyCode > 0 && keyCode < 127)
                    pressedKeys[keyCode] = false;
            }
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }
}