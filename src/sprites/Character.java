package sprites;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

public class Character extends Sprite {

	public int width;
	public int height;

	protected double xVel, yVel;
	protected Sprite isOn, isAgainst;
	protected ArrayList<PImage> costumes;
	protected boolean facesRight;

	public Character(PImage img, int x, int y) {
		super(img, x, y, 63, 87);
		costumes = null;
		width = 40;
		height = 60;
		xVel = 0;
		yVel = 0;
		isOn = null;
		isAgainst = null;
		facesRight = true;
	}
	
	
	
	public Character(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		costumes = null;
		width = w;
		height = h;
		xVel = 0;
		yVel = 0;
		isOn = null;
		isAgainst = null;
		facesRight = true;
	}
	
	// imgs must have at least two images
	// at position 0 should be facing right
	// at position 1 should be facing left
	public Character(ArrayList<PImage> imgs, int x, int y) {
		super(imgs.get(0), x, y, 42, 58);
		costumes = imgs;
		width = 40;
		height = 60;
		xVel = 0;
		yVel = 0;
		isOn = null;
		isAgainst = null;
		facesRight = true;
	}
	
	public Character(ArrayList<PImage> imgs, int x, int y, int w, int h) {
		super(imgs.get(0), x, y, w, h);
		costumes = imgs;
		width = w;
		height = h;
		xVel = 0;
		yVel = 0;
		isOn = null;
		isAgainst = null;
		facesRight = true;
	}


	// METHODS
	public void walk(double dir) {
		xVel = dir;
		if(dir!=Math.abs(dir))
		{
			facesRight= false;
		} else
		{
			facesRight = true;
		}

	}

	public void act(List<Sprite> obstacles) {
		
		y += yVel;
		
		for(int i = 0; i < obstacles.size(); i++)
		{
			
			if(this.getFrame().intersects(obstacles.get(i))) {
				y -= yVel;
				if(obstacles.get(i).getCenterY()>this.getCenterY())
					isOn = obstacles.get(i);
					yVel = 0;
			}

		}
		
		x += xVel;
		
		for(int i = 0; i < obstacles.size(); i++)
		{
			
			if(this.getFrame().intersects(obstacles.get(i))) {
				x -= xVel;
				isAgainst = obstacles.get(i);
				xVel = 0;
			}
		}
		// forces
		yVel+=1.5+yVel*0.1;
		xVel*=0.5;
		yVel*=0.82;
	}

	public void draw(PApplet g) {
		if(costumes!=null)
		{
			PImage p = costumes.get(0);
			if(!facesRight) p = costumes.get(1);
			g.image(p, (float)x,(float)y,(float)width,(float)height);
		} else super.draw(g);

	}
		

}
