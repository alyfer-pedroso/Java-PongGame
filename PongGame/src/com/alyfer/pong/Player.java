package com.alyfer.pong;

import java.awt.*;

public class Player {

    public boolean right, left;
    public int x,y;
    public int width, height;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }
    public void tick() {
        if(right) {
            x++;
        } else if (left) {
            x--;
        }

        if(x + width > Game.width) {
            x = Game.width - width;
        } else if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(x, y, width, height);
    }
}
