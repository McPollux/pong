/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

/**
 *
 * @author enox4
 */
public class Pong extends Applet implements Runnable, KeyListener {
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    Palumano p1;
    PaloIA p2;
    Pelota pelota;
    boolean empezado;
    Graphics gfx;
    Image img;

    public void init(){
        this.resize(WIDTH, HEIGHT);

        empezado = false;
        this.addKeyListener(this);
        p1 = new Palumano(1);
        pelota = new Pelota();
        p2 = new PaloIA(2, pelota);
        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, WIDTH , HEIGHT);

        if (pelota.getX() < -10 || pelota.getX() >710){
            gfx.setColor(Color.RED);
            gfx.drawString("Game Over", 350 , 250);
        }else {
            p1.draw(gfx);
            p2.draw(gfx);
            pelota.draw(gfx);
        } if (!empezado){
            gfx.setColor(Color.WHITE);
            gfx.drawString("Pong PLUS", 340 , 100);
            gfx.drawString("Pulsa ENTER para empezar", 295, 130);
        }
        g.drawImage(img , 0 ,0, this);
    }

    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        for(;;){
            if (empezado) {
                p1.move();
                p2.move();
                pelota.move();
                pelota.confirmarChoque(p1, p2);
            }
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p1.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.setDownAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            empezado=true;
        } else if (e.getKeyCode() == KeyEvent.VK_P){
            empezado=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            p1.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            p1.setDownAccel(false);
        }
    }
}
