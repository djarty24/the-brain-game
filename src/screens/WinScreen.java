package screens;



import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.PImage;


public class WinScreen extends Screen {	
	private Rectangle button;
	private PImage img;

	public WinScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		button = new Rectangle(800/2-75,600/2-25,150,50);
	}
	
	public void setup() {
		img = surface.loadImage("img/win-screen.png");
	}



	public void draw() {

		surface.push();
		surface.image(img, 0,0, 800, 600);
		surface.textSize(60);
		surface.noStroke();
		surface.fill(255);
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(246, 113, 165);
		
		long total = surface.getTotalTime();
		long minutes = (int)(total / (1000 * 60));
		long seconds = (int)(total % (1000 * 60) / 1000);	
		
		if (minutes == 0 && seconds == 0) {
			surface.text("00:00", 340, 160);
		} else if (minutes < 10 && seconds < 10){
			surface.text("0" + minutes + ":0" + seconds, 340, 160);
		} else if (minutes < 10) {
			surface.text("0" + minutes + ":" + seconds, 340, 160);
		} else if (seconds < 10){
			surface.text("" + minutes + ":0" + seconds, 340, 160);
		} else {
			surface.text("" + minutes + ":" + seconds, 340, 160);
		}
		
		surface.textSize(40);
		float w = surface.textWidth("Menu");
		surface.text("Menu", button.x+button.width/2-w/2, button.y+button.height/2+13);
		surface.pop();
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL_SELECTOR);
	}
	

}

