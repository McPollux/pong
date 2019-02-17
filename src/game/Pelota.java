package game;

import java.awt.*;

public class Pelota {
    double xVel, yVel, x,y;

    public Pelota() {
        this.xVel = getRandomSpeed() * getRandomDirection();
        this.yVel = getRandomSpeed() * getRandomDirection();
        this.x = 350;
        this.y = 250;
    }

    public double getRandomSpeed(){
        return (Math.random()*3 +2);
    }

    public int getRandomDirection(){
        int random = (int)(Math.random()*2);
        if (random == 1){
            return 1;
        } else {
            return -1;
        }
    }

    public void move(){
        x += xVel;
        y += yVel;

        //Rebote vertical
        if (y < 10 || y > 490) {
            yVel = -yVel;
        }

    }

    public void confirmarChoque(Pala p1, Pala p2){ //Rebote horizontal
        if (x <=50) {
            if (y >= p1.getY()-10 && y <= p1.getY() + 80 + 10){
                xVel = -xVel;
            }
        } else if( x>=650){
            if (y >= p2.getY()-10 && y <= p2.getY() + 80 + 10){
                xVel = -xVel;
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int)x-10, (int)y-10 , 20 ,20);
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public double getX() {
        return (int)x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return (int)y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
