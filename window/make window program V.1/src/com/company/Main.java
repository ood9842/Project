package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame win=new JFrame("Make window V.1");//set header name
        win.setSize(640,450);//set size window
        win.setResizable(false);//set to done modify size
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set return memory to hard when close program
        win.setVisible(true);//set show window
        win.setLocationRelativeTo(null);//set location to center screen
    }
}
