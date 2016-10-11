package window;

import body.Snack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chetsada Chaiprasop on 10/10/2016.
 */
public class Sub_Frame extends JPanel implements Runnable{
    private int WIDTH=900;
    private int HIGHT=600;
    private Thread th;//oop to radder graphic
    private boolean run=false;//status run forever loop
    private Snack s;//body 1 box
    private ArrayList<Snack> sarr;//
    private int sx=10,sy=10,ssize=3;//set position start and size of snack

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

    }

    @Override
    public void paint(Graphics g)//control compute graphic
    {
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
