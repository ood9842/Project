package com.company.sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Chetsada Chaiprasop on 10/22/2016.
 */
public class DrawAngel {
    private BufferedImage spriteSheet = null;
    private  SplitImage ss ;
    public DrawAngel()
    {
        ImageLoader load = new ImageLoader();
        try {
            spriteSheet = load.loadeImage("angel.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ss = new SplitImage(spriteSheet);
    }

    public void draw(Graphics g)
    {
        g.drawImage(ss.cropImage(0,0,80,65),123,321,null);
    }
}
