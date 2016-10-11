package window;

import body.Food;
import body.Snack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import static com.sun.webkit.event.WCKeyEvent.*;

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
    private boolean right=false,left=false,up=false,down=false;
    private String status=null;
    private int count=0;
    //set object item
    private Food item;
    private ArrayList<Food> itemArr;
    private int ix,iy;

    Sub_Frame()
    {
        itemArr=new ArrayList<>();
        sarr=new ArrayList<>();
        setPreferredSize(new Dimension(WIDTH,HIGHT));
        th=new Thread(this);//use to make graphic in the future
        th.start();
        addKeyListener(new KeyInner());
        setFocusable(true);
    }

    @Override
    public void run()
    {
        run=true;
        while(run)//same forever in go go bot
        {
            tick();//control object and main compute
            repaint();//calling the meteor paint
        }
    }

    private void tick()//control object in screen or set property
    {
        if(this.sarr.size()==0)//create the snack
        {
            s=new Snack(sx,sy,10);
            sarr.add(s);
        }
        if(this.itemArr.size()==0)
        {
            ix=new Random().nextInt(80);
            iy=new Random().nextInt(50);
            item=new Food(ix,iy,10);
            itemArr.add(item);
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
                sy--;
            }
            if(down)
            {
                sy++;
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
        if(this.sx==itemArr.get(0).getx()&&this.sy==itemArr.get(0).gety())
        {
            itemArr.remove(0);
            ssize++;
        }
    }
    //control print graphic
    @Override
    public void paint(Graphics g)
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
        //draw item
        itemArr.get(0).draw(g);
    }
    //input key board to move snack
    class KeyInner implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            if(key==VK_RIGHT&&status!="LEFT")
            {
                right=true;
                left=false;
                up=false;
                down=false;
                status="RIGHT";
            }
            if(key==VK_LEFT&&status!="RIGHT")
            {
                right=false;
                left=true;
                up=false;
                down=false;
                status="LEFT";
            }
            if(key==VK_UP&&status!="DOWN")
            {
                right=false;
                left=false;
                up=true;
                down=false;
                status="UP";
            }
            if(key==VK_DOWN&&status!="UP")
            {
                right=false;
                left=false;
                up=false;
                down=true;
                status="DOWN";
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
