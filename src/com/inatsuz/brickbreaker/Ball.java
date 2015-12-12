package com.inatsuz.brickbreaker;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    private BrickBreaker brickBreaker;

    private int speedX, speedY;
    private int radius;
    private int x, y;
    private int paddleCooldown;

    public Ball(int speed, int radius, BrickBreaker brickBreaker) {
        this.speedX = speedY = speed;
        this.radius = radius;
        this.brickBreaker = brickBreaker;
        speedY = -speedY;
        x = brickBreaker.width / 2 - radius / 2;
        y = brickBreaker.height - 50 - radius;
        paddleCooldown = 0;
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private int getRadius() {
        return radius;
    }

    private int getSpeedX() {
        return speedX;
    }

    private int getSpeedY() {
        return speedY;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }

    private void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    private void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void update(Paddle paddle) {
        moveX();
        moveY();
        checkWallCollision();
        if (paddleCooldown == 0) {
            checkPaddleCollision(paddle);
        } else if (paddleCooldown > 0) {
            paddleCooldown--;
        }
    }

    private void moveX() {
        if (x + speedX >= 0 && x + radius + speedX <= brickBreaker.width - 6) {
            x += speedX;
        } else if (x + speedX < 0) {
            x = 0;
        } else if (x + radius + speedX > brickBreaker.width - 7) {
            x = brickBreaker.width - radius - 7;
        }
    }

    private void moveY() {
        if (y + speedY >= 0 && y + radius + speedY <= brickBreaker.height - 30) {
            y += speedY;
        } else if (y + speedY < 0) {
            y = 0;
        } else if (y + radius + speedY > brickBreaker.height - 30) {
            y = brickBreaker.height - radius - 30;
        }
    }

    private void checkWallCollision() {
        if (y <= 0) {
            speedY = -speedY;
        }
        if (x <= 0 || x + radius >= brickBreaker.width - 7) {
            speedX = -speedX;
        }
    }

    private void checkPaddleCollision(Paddle paddle) {
        if (x + radius / 2 >= paddle.getX() && x + radius / 2 <= paddle.getX() + paddle.getWidth()) {
            if (y + radius >= paddle.getY()) {
                speedY = -speedY;
                paddleCooldown = 6;
            }
        } else if (y + radius / 2 >= paddle.getY() && y + radius <= paddle.getY() + paddle.getHeight()) {
            if (x <= paddle.getX() + paddle.getWidth() && x >= paddle.getX()) {
                if (speedX < 0) {
                    speedX = -speedX;
                }
                speedY = -speedY;
                paddleCooldown = 6;
            } else if (x + radius >= paddle.getX() && x + radius <= paddle.getX() + paddle.getWidth()) {
                if (speedX > 0) {
                    speedX = -speedX;
                }
                speedY = -speedY;
                paddleCooldown = 6;
            }
        }
    }

    private void checkBottomCollision() {
        if (y + radius >= brickBreaker.height - 30) {

        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, radius, radius);
    }

}
