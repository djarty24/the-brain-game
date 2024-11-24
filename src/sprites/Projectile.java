package sprites;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;


 
public class Projectile extends Sprite {
	private int dir; // 1 for right, -1 for left
	public Projectile(PImage img, int x, int y, int w, int h, int d) {
		super(img, x,y,w,h);
		dir = d;
	}
	
	public int getDirection()
	{
		return dir;
	}
	
	
	
}










