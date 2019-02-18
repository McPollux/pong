package game;

import java.awt.*;

public interface Pala {
    public void draw(Graphics g);
    public void move();
    public int getY();
	public int getX();
	public int getWidth();
	public int getHeight();
}
