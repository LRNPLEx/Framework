package com.learnplex.framework;

/**
 * Created by David on 6/23/2016.
 */

import com.learnplex.framework.Graphics.PixmapFormat;
public interface Pixmap {
    int getWidth();

    int getHeight();

    PixmapFormat getFormat();

    void dispose();
}
