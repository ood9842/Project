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
    //set value condition to end game
    private int ready=0;
    private boolean go=false;

    Sub_Frame()
    {//create all constructor
        itemArr=new ArrayList<>();
        sarr=new ArrayList<>();
        setPreferredSize(new Dimension(WIDTH,HIGHT));
        addKeyListener(new KeyInner());
        setFocusable(true);
    }

    private void start() {
        run=true;
        th=new Thread(this);//use to make graphic in the future
        th.start();
    }

    @Override
    public void run()
    {
        while(run)//same forever in go go bot
        {
            tick();//control object and main compute
            repaint();//calling the meteor paint
        }
    }

    private void tick()//control object in screen or set property
    {
        if(this.sarr.size()==0)//create the snake
        {
            s=new Snack(sx,sy,10);
            sarr.add(s);
        }
        if(this.itemArr.size()==0)//create the food
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
            ready++;
        }
        //set to add size snake
        if(this.sx==itemArr.get(0).getIx()&&this.sy==itemArr.get(0).getIy())
        {
            itemArr.remove(0);
            ssize++;
        }
        //set condition to end game
        for(int i=0;i<sarr.size()-1;i++)//case 1 eat you self
        {
            if(ready>4)
            {
                go=true;
                ready--;
            }
            if (this.sx==sarr.get(i).getSx()&&this.sy==sarr.get(i).getSy())
            {
                if(go)
                stop();
            }
        }
        if(sx<0||sx>WIDTH/10||sy<0||sy>HIGHT/10)//over edge
        {
            stop();
        }
    }
    //stop game
    private void stop() {
        this.run=false;
        sx=10;
        sy=10;
        ssize=4;
        right=false;
        left=false;
        up=false;
        down=false;
        status=null;
        ready=0;
        go=false;
        sarr.clear();
        itemArr.clear();
        try{
            th.join();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
     }
    //control print graphic
    @Override
    public void paint(Graphics g)
    {
        g.clearRect(0, 0, WIDTH, HIGHT);//clear and update to current screen
        if(!this.run)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tahoma",Font.BOLD,40));
            g.drawString("Snake Game",WIDTH/2-120,HIGHT/2);
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma",Font.BOLD,30));
            g.drawString("Enter SpaceBar to start",WIDTH/2-160,HIGHT/2+50);
        }
        else {
            g.clearRect(0, 0, WIDTH, HIGHT);//clear and update to current screen
            //simple grid layer
            g.setColor(Color.BLUE);
            for (int i = 0; i < WIDTH / 10; i++) {
                g.drawLine(i * 10, 0, i * 10, HIGHT);//start x,start y,end x,end y
            }
            for (int i = 0; i < HIGHT / 10; i++) {
                g.drawLine(0, i * 10, WIDTH, i * 10);//start x,start y,end x,end y
            }
            //draw snack
            for (int i = 0; i < sarr.size(); i++) {
                sarr.get(i).draw(g);
            }
            //draw item
            itemArr.get(0).draw(g);
        }
    }
    //show score
    private void endgame(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma",Font.BOLD,40));
        g.drawString("Game Over",WIDTH/2-120,HIGHT/2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma",Font.BOLD,30));
        g.drawString("You score %d",WIDTH/2-160,HIGHT/2+50);
        g.drawString("Enter SpaceBar to restart",WIDTH/2-160,HIGHT/2+50);

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
            if(key==KeyEvent.VK_RIGHT&&status!="LEFT")
            {
                right=true;
                left=false;
                up=false;
                down=false;
                status="RIGHT";
            }
            if(key==KeyEvent.VK_LEFT&&status!="RIGHT")
            {
                right=false;
                left=true;
                up=false;
                down=false;
                status="LEFT";
            }
            if(key==KeyEvent.VK_UP&&status!="DOWN")
            {
                right=false;
                left=false;
                up=true;
                down=false;
                status="UP";
            }
            if(key==KeyEvent.VK_DOWN&&status!="UP")
            {
                right=false;
                left=false;
                up=false;
                down=true;
                status="DOWN";
            }
            if(key==KeyEvent.VK_SPACE)
            {
                start();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
