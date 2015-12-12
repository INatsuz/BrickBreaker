package com.inatsuz.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {

    private BrickBreaker brickBreaker;
    private int speed;
    private int width, height;
    private int x, y;

    public Paddle(int speed, int width, int height, BrickBreaker brickBreaker) {
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.brickBreaker = brickBreaker;
        x = brickBreaker.width / 2 - 50;
        y = brickBreaker.height - 50;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move(boolean right) {
        if (right) {
            if (x + speed + width <= brickBreaker.width) {
                x += speed;
            } else {
                x = brickBreaker.width - width;
            }
        } else if (!right) {
            if (x - speed >= 0) {
                x -= speed;
            }else {
                x = 0;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

}
