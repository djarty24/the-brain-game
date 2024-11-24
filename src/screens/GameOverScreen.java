package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.PImage;


public class GameOverScreen extends Screen {	
	
	private PImage bg;
	private Rectangle button;

	public GameOverScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100, 600/2 + 50,200,70);
	}

	public void setup() {
		bg = surface.loadImage("img/game-over.png");
	}

	public void draw() {
		surface.push();
		surface.image(bg, (float) 0, (float) 0, (float) DRAWING_WIDTH, (float) DRAWING_HEIGHT);
		surface.noStroke();
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		String str = "Replay";
		surface.fill(255);
		float w = surface.textWidth(str);
		surface.fill(255, 102, 196);
		surface.textSize(40);
		surface.text(str, button.x+button.width/2-w/2, button.y+45);
		surface.pop();
		
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_SELECTOR);
	}
	

}

