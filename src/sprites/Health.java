package sprites;

import processing.core.PApplet;
import processing.core.PImage;

public class Health extends Sprite {

	private final int MAX_HEALTH = 6;
	private int health;
	private PImage heart, halfHeart;
	
	public Health(int x, int y, int w, int h, PImage h1, PImage h2) {
		super(x, y, w, h);
		health = 6;
		heart = h1;
		halfHeart = h2;
	}
	
	public int getHealth() {
		return health;
	}
	
	/**
	 * Reduces the player's health by 1 (half a heart)
	 */
	public void reduceHealth() {
		if (!isHealthZero()) {
			health --;
		}
	}
	
	/**
	 * Restores health to the max value (3 hearts)
	 */
	public void restoreHealth() {
		health = MAX_HEALTH;
	}
	
	/**
	 * Checks if health is zero
	 * @return true if health is zero, false otherwise
	 */
	public boolean isHealthZero() {
		if (health == 0) {
			return true;
		}
		return false;
	}
	
	public void draw(PApplet g) {
		g.push();
		
		g.fill(245, 66, 66);
		
		for (int i = 0; i < health / 2; i++) {
			g.image(heart,(float)x + i * 35,(float)y,(float)width,(float)height);
			
			if (i == health / 2 - 1 && health % 2 == 1) {
				g.image(halfHeart,(float)x + (i + 1) * 35,(float)y,(float)width / 2,(float)height);
			}
		}
		
		if (health == 1) {
			g.image(halfHeart,(float)x,(float)y,(float)width / 2,(float)height);
		}
				
		g.pop();
	}
	
}
