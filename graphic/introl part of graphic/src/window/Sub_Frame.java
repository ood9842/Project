package window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chetsada Chaiprasop on 10/10/2016.
 */
public class Sub_Frame extends JPanel implements Runnable{
    private int WIDTH=900;
    private int HIGHT=600;
    private Thread th;//oop to rander graphic
    private boolean run=false;//status run forever loop

    Sub_Frame()
    {
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
    }
}
