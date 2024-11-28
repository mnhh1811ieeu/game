package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitBox;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		initHitbox(x,y,width,height);
	}

	protected void initHitbox(float x, float y, int  width, int height) {
		// TODO Auto-generated method stub
		hitBox = new Rectangle2D.Float(x, y,width,height);
	}

//	public void updateHitbox() {
//		hitBox.x = (int) x;
//		hitBox.y = (int) y;
//	}

	public Rectangle2D.Float getHitbox() {
		return hitBox;
	}

	protected void drawHitbox(Graphics g) {
		// để debug hit box
		g.setColor(Color.ORANGE);
		g.drawRect((int)hitBox.x,(int) hitBox.y,(int) hitBox.width, (int)hitBox.height);

	}
}
