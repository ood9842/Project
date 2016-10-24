package com.company.window;

import com.company.sprite.DrawAngel;
import com.company.sprite.ImageLoader;
import com.company.sprite.SplitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Chetsada Chaiprasop on 10/22/2016.
 */
public class Sub_Frame extends JPanel implements Runnable{
    private int width=500,height=500;
    private Thread th ;
    public Sub_Frame ()
    {
        setPreferredSize(new Dimension(width,height));
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        repaint();
    }

    public void paint (Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillRect(0,0,width,height);
        DrawAngel an =new DrawAngel();
        an.draw(g);
    }
}
