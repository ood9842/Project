package com.company.sprite;

import java.awt.image.BufferedImage;

/**
 * Created by Chetsada Chaiprasop on 10/22/2016.
 */
public class SplitImage {
    public BufferedImage spriteSheet;

    public SplitImage(BufferedImage ss)
    {
        spriteSheet = ss;
    }
    public BufferedImage cropImage(int x,int y,int width,int height)
    {
        BufferedImage temp = spriteSheet.getSubimage(x,y,width,height);
        return temp;
    }
}
