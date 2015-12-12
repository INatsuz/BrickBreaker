package com.inatsuz.brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class BrickBreaker implements KeyListener, ActionListener {

    public static BrickBreaker brickBreaker;

    public int width = 700;
    public int height = 700;

    private int gameState; // 0 ---> Menu | 1 ---> Playing //

    private JFrame jFrame;
    private Renderer renderer;
    private Timer timer;

    private Paddle paddle;
    private Ball ball;

    private boolean left = false, right = false;
    private boolean running = false;
    private boolean ballMoving;

    public BrickBreaker() {
        jFrame = new JFrame("BrickBreaker");
        renderer = new Renderer();
        timer = new Timer(20, this);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.add(renderer);
        jFrame.setLocationRelativeTo(null);
        jFrame.addKeyListener(this);

        paddle = new Paddle(5, 100, 20, this);
        ball = new Ball(6, 20, this);

        timer.start();
        running = true;
        gameState = 0;
        ballMoving = false;
    }

    public static void main(String[] args) {
        brickBreaker = new BrickBreaker();
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 700);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (gameState == 0) {
            g.setFont(new Font("Arial", 1, 50));
            g.setColor(Color.WHITE);
            g.drawString("Brickbreaker", width / 2 - 150, height / 2 - 100);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Press Space To Start", width / 2 - 150,  height / 2 - 10);
        } else if (gameState == 1) {
            paddle.render(g);
            ball.render(g);
        }
    }

    private void update() {
        if (gameState == 1 && ballMoving) {
            if (right && !left) {
                paddle.move(true);
            } else if (left && !right) {
                paddle.move(false);
            }
            ball.update(paddle);
        }
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            if(gameState == 0)
                gameState = 1;
            else if(gameState == 1)
                ballMoving = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            update();
        }
        renderer.repaint();
    }

}
