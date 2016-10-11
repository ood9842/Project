package window;

import body.Snack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chetsada Chaiprasop on 10/10/2016.
 */
public class Sub_Frame extends JPanel implements Runnable{
    //set size of sub window
    private int WIDTH=900;
    private int HIGHT=600;
    //set graphic and run loop
    private Thread th;//oop to radder graphic
    private boolean run=false;//status run forever loop
    //set object player
    private Snack s;//body 1 box
    private ArrayList<Snack> sarr;//
    private int sx=10,sy=10,ssize=4;//set position start and size of snack
    //set property movement object player
    private boolean right=true,left=false,up=false,down=false;
    private int count=0;

    Sub_Frame()
    {
        sarr=new ArrayList<>();
        setPreferredSize(new Dimension(WIDTH,HIGHT));
        th=new Thread(this);//use to make graphic in the future
        th.start();
    }

    @Override
    public void run()
    {
        run=true;
        while(run)//same forever in go go bot
        {
            tick();
            repaint();//draw and clear screen same c++
        }
    }

    private void tick()//control object in screen or set property
    {
        if(this.sarr.size()==0)//create the snack
        {
            s=new Snack(sx,sy,10);
            sarr.add(s);
        }
        //count time to move box
        count++;
        if(count>2500000)
        {
            if(right)
            {
                sx++;
            }
            if(left)
            {
                sx--;
            }
            if(up)
            {
                sy++;
            }
            if(down)
            {
                sy--;
            }
            count=0;
            s=new Snack(sx,sy,10);
            sarr.add(s);
            //delete tail
            if(sarr.size()>ssize)
            {
                sarr.remove(0);
            }
        }
    }

    @Override
    public void paint(Graphics g)//control compute graphic
    {
        g.clearRect(0,0,WIDTH,HIGHT);//clear and update to current screen
        //simple grid layer
        g.setColor(Color.BLUE);
        for (int i=0;i<WIDTH/10;i++)
        {
            g.drawLine(i*10,0,i*10,HIGHT);//start x,start y,end x,end y
        }
        for (int i=0;i<HIGHT/10;i++)
        {
            g.drawLine(0,i*10,WIDTH,i*10);//start x,start y,end x,end y
        }
        //draw snack
        for(int i=0;i<sarr.size();i++)
        {
            sarr.get(i).draw(g);
        }
    }
}
