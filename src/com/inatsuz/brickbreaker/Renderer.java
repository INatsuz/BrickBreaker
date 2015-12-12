package com.inatsuz.brickbreaker;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Renderer extends JPanel{
    
    public void paint(Graphics g){
        super.paintComponent(g);
        
        BrickBreaker.brickBreaker.render((Graphics2D) g);
    }
    
}
