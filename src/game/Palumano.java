package game;

import java.awt.*;

public class Palumano implements Pala {
    double y, yVel;
    boolean upAccel, downAccel;
    final double GRAVITY = 0.94;
    int player, x;
    Pelota pelota;

    private int width = 20;
	private int height = 80;

    public Palumano(int player, Pelota pelota) {
        this.pelota = pelota;
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
        g.fillRect(x, (int)y, width , height);
    }

    @Override
    public void move() {
        if(upAccel){
            if (pelota.getX() < 50) {
                if ((y >= pelota.getY() && y <= pelota.getY() - 40)) {
                    yVel = 0;
                } else {
                    yVel -= 2;
                }
            } else {
                yVel -= 2;
            }
        } else if (downAccel){
            if (pelota.getX() < 50) {
                if ((y >= pelota.getY() && y <= pelota.getY() - 40)) {
                    yVel = 0;
                } else {
                    yVel += 2;
                }
            } else {
                yVel += 2;
            }
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

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
    
}
