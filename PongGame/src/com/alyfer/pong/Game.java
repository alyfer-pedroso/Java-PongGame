package com.alyfer.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int width = 160;
    public static int height = 120;
    public static int scale = 3;

    public BufferedImage layer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(width * scale, height * scale));
        this.addKeyListener(this);
        player = new Player(100, height - 5);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, height / 2 - 1);
    }

    public static void main(String[] args) {
        //Janela
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    //Renderização
    public void render () {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        player.render(g);
        enemy.render(g);
        ball.render(g);
        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, width * scale, height * scale, null);
        bs.show();
    }

    //Game Loop
    @Override
    public void run() {
        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
    }
}
