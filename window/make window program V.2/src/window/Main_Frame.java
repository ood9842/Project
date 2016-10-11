package window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chetsada Chaiprasop on 10/10/2016.
 */
public class Main_Frame extends JFrame{
    public Main_Frame()
    {
        setTitle("Make window V.2");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1,0,0));
        start();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void start() {
        Sub_Frame scene=new Sub_Frame();
        add(scene);
        pack();
    }

}
