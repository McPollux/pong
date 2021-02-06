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
import java.io.IOException;

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
    boolean empezado, pausado;
    Graphics gfx;
    Image img;
    int derrotas = 0;

    public void init(){
        this.resize(WIDTH, HEIGHT);

        empezado = false;
        pausado = false;
        this.addKeyListener(this);
        pelota = new Pelota();
        p1 = new Palumano(1, pelota);
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
            derrotas++;
            if (derrotas<3) {
                empezado = false;
                pelota = new Pelota();
                p1 = new Palumano(1, pelota);
                p2 = new PaloIA(2, pelota);
                p1.draw(gfx);
                p2.draw(gfx);
                pelota.draw(gfx);
            } else {
                gfx.setColor(Color.RED);
                gfx.drawString("Game Over", 340 , 100);
                gfx.drawString("Procedo a apagarte el sistema", 290, 130);
            }
        } else {
            p1.draw(gfx);
            p2.draw(gfx);
            pelota.draw(gfx);
            gfx.setColor(Color.WHITE);
            gfx.drawString("Derrotas:", 5 , 20);
            gfx.setColor(Color.RED);
            gfx.drawString(String.valueOf(derrotas), 60, 20);
        } if (!empezado){
            gfx.setColor(Color.WHITE);
            gfx.drawString("Pong PLUS", 340 , 100);
            gfx.drawString("Pulsa ENTER para empezar", 295, 130);
        } if (pausado){
            gfx.setColor(Color.WHITE);
            gfx.drawString("Juego pausado", 320 , 100);
            gfx.drawString("Pulsa P para reanudar", 300, 130);
        }
        g.drawImage(img , 0 ,0, this);
        if (derrotas>2){
            try {
                Thread.sleep(4000);
                shutdown();
                System.exit(0);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void run() {
        while (true) {
            if (empezado) {
                if (!pausado) {
                	pelota.confirmarChoque(p1, p2);
                    p1.move();
                    p2.move();
                    pelota.move();
                }
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
            pausado=!pausado;
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
    public static void shutdown() throws RuntimeException, IOException {
        String shutdownCommand;
        String operatingSystem = System.getProperty("os.name");

        if (null == operatingSystem) {
            throw new RuntimeException("Unsupported operating system.");
        } else {
            switch (operatingSystem) {
                case "Linux":
                case "Mac OS X":
                    shutdownCommand = "shutdown -h now";
                    break;
                case "Windows":
                case "Windows 10":
                    shutdownCommand = "shutdown.exe -s -t 10";
                    break;
                default:
                    throw new RuntimeException("Unsupported operating system.");
            }
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }
}
