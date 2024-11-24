package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.PImage;


public class Instructions extends Screen {	
	
	private PImage bg, buttonImg;
	private Rectangle button;

	public Instructions(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		button = new Rectangle(DRAWING_WIDTH - 75, 40, 30, 30);
	}

	public void setup() {
		bg = surface.loadImage("img/instructions.png");
		buttonImg = surface.loadImage("img/rules-on.png");
	}

	public void draw() {
		surface.image(bg, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.image(buttonImg, (float) button.getX(), (float) button.getY(), (float) button.getWidth(), (float) button.getHeight());
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(button.contains(p)) {
			surface.switchScreen(0);
		}
	}
	

}

