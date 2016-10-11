package body;

import java.awt.*;

/**
 * Created by Chetsada Chaiprasop on 10/11/2016.
 */
public class Food {
    private int x,y;
    private int widht,hight;

    public Food(int x,int y,int size)
    {
        this.x=x;
        this.y=y;
        this.widht=size;
        this.hight=size;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(x*widht,y*hight,widht,hight);
    }

    public int getIx()
    {
        return this.x;
    }

    public int getIy()
    {
        return this.y;
    }
}
