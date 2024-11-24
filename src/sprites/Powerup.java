package sprites;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

public class Powerup extends Sprite {

	private PImage img;
	private String type;
	public Powerup(PImage img, int x, int y, int width, int height, String t) {
		super(img, x, y, width, height);
		this.img = img;
		type = t;
	}
	
	public String getType() {
		return type;
	}
	
	

}