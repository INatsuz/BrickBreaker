package com.inatsuz.brickbreaker;

public class Brick {
    
    private int health;
    private int width, height;
    private int x, y;
    
    private BrickBreaker brickBreaker;

    public Brick(int health, int width, int height, int bricks, BrickBreaker brickBreaker){
        this.brickBreaker = brickBreaker;
        
        this.health = health;
        this.width = ((int)(width / 10)) * 10;
        this.height = height;
        
    }

}
