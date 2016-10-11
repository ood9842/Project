package body;

import java.awt.*;

/**
 * Created by Chetsada Chaiprasop on 10/10/2016.
 */
public class Snack {
    private int x,y,swidth,shight;

    public Snack(int x,int y,int size)
    {
        this.x=x;
        this.y=y;
        this.shight=size;
        this.swidth=size;
    }
    //set box Snack
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(x*swidth,y*shight,swidth,shight);//create dense square
        g.setColor(Color.WHITE);
        g.fillRect(x*swidth+2,y*shight+2,swidth-4,shight-4);//position x y,size width high
    }
}
