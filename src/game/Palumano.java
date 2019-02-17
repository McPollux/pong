package game;

import java.awt.*;

public class Palumano implements Pala {
    double y, yVel;
    boolean upAccel, downAccel;
    final double GRAVITY = 0.94;
    int player, x;

    public Palumano(int player) {
        upAccel = false;
        downAccel = false;
        y = 210;
        yVel = 0;
        if (player == 1){
            x = 20;
        } else {
            x = 660;
        }
        this.player = player;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, (int)y, 20 , 80);
    }

    @Override
    public void move() {
        if(upAccel){
            yVel -= 2;
        } else if (downAccel){
            yVel += 2;
        } else {
            yVel *= GRAVITY;
        }

        if(yVel>5){
            yVel=5;
        }else if (yVel<-5){
            yVel=-5;
        }

        y += yVel;
        if (y <0){
            y = 0;
        } else if (y >420){
            y = 420;
        }
    }

    public void setUpAccel(boolean b){
        upAccel = b;
    }

    public void setDownAccel(boolean b){
        downAccel = b;
    }

    @Override
    public int getY() {
        return (int)y;
    }
}
