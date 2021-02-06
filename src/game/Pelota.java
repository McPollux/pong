package game;

import java.awt.*;

public class Pelota {
    double xVel, yVel, x, y;
    
    private int height = 20;
    private int width = 20;

    public Pelota() {
        this.xVel = getRandomSpeed() * getRandomDirection();
        this.yVel = getRandomSpeed() * getRandomDirection();
        this.x = 350;
        this.y = 250;
    }

    public double getRandomSpeed() {
        return (Math.random() * 3 + 2);
    }

    public int getRandomDirection() {
        int random = (int) (Math.random() * 2);
        if (random == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public void move() {
        x += xVel;
        y += yVel;

        //Rebote vertical
        if (y < 10 || y > 490) {
            yVel = -yVel;
        }

    }

	public void confirmarChoque(Pala p1, Pala p2) { //Rebote horizontal

		if (checkCollision(p1, this) || checkCollision(p2, this)) {
			xVel = -xVel;
		}

		if ((x <= 50 || x >= 650) && y >= p1.getY() + 30 && y <= p2.getY() - 30) {
			yVel = -yVel;
		}

	}
	
	private boolean checkCollision(Pala p1, Pelota p2) {
		return checkCollision(p1, p2, 0);
	}

	private boolean checkCollision(Pala p1, Pelota p2, float slack) {

		double d1x = p2.getX() - (p1.getX() + p1.getWidth() + slack);	// d1x = b->min.x - a->max.x;
		double d1y = p2.getY() - (p1.getY() + p1.getHeight() + slack);	// d1y = b->min.y - a->max.y;
		double d2x = p1.getX() - (p2.getX() + p2.getWidth() + slack);	// d2x = a->min.x - b->max.x;
		double d2y = p1.getY() - (p2.getY() + p2.getWidth() + slack);	// d2y = a->min.y - b->max.y;

        return !(d1x > 0.0f) && !(d1y > 0.0f) && !(d2x > 0.0f) && !(d2y > 0.0f);
    }

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) x, (int) y, width, height);
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
        return (int) x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return (int) y;
    }

    public void setY(double y) {
        this.y = y;
    }

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}
    
}
