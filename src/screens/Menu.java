package screens;

import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.PImage;

public class Menu extends Screen {
	
	private Rectangle start;
	private Rectangle instructions;
	private PImage bg;

	public Menu(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		start = new Rectangle(DRAWING_WIDTH/4, DRAWING_HEIGHT/2, DRAWING_WIDTH/2, DRAWING_HEIGHT/8);
		instructions = new Rectangle(DRAWING_WIDTH/4, DRAWING_HEIGHT/2 + DRAWING_HEIGHT/8 + 25, DRAWING_WIDTH/2, DRAWING_HEIGHT/8);
	}
	
	public void setup() {
		bg = surface.loadImage("img/menu-bg.png");
	}


	public void draw() {
		surface.image(bg, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		
		surface.fill(255);
		surface.strokeWeight(3);
		surface.stroke(255, 102, 196);
		surface.rect((float) start.x, (float) start.y, (float) start.width, (float) start.height, 20);
		surface.rect((float) instructions.x, (float) instructions.y, (float) instructions.width, (float) instructions.height, 20);
		surface.fill(255, 102, 196);
		surface.textSize(40);
		String str = "Start";
		String str2 = "Instructions";
		float w1 = surface.textWidth(str);
		float w2 = surface.textWidth(str2);
		surface.text(str, start.x+start.width/2-w1/2, start.y+start.height-25);
		surface.text(str2, instructions.x+instructions.width/2-w2/2, instructions.y+instructions.height-25);
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (start.contains(p))
		{
			surface.switchScreen(2);
			((LevelSelector)surface.getActiveScreen()).showIntro();
		}else if (instructions.contains(p))
			surface.switchScreen(1);

	}
	

}