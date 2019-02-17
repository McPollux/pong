package game;

import java.awt.*;

public class PaloIA implements Pala {
    double y, yVel;
    boolean upAccel, downAccel;
    final double GRAVITY = 0.94;
    int player, x;
    Pelota pelota;

    public PaloIA(int player, Pelota pelota) {
        upAccel = false;
        downAccel = false;
        this.pelota = pelota;
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
       y = pelota.getY() - 40;
    }

    @Override
    public int getY() {
        return (int)y;
    }
}
