package sprites;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class Inventory extends Sprite {

	private ArrayList<Powerup> inventory = new ArrayList<Powerup>();
	private final int MAX = 5;
	
	public Inventory(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public ArrayList<Powerup> getInventory() {
		return inventory;
	}
	
	/**
	 * Adds a powerup to the inventory
	 * @param p the powerup to add
	 * @return true if the powerup was added, false if the inventory is full
	 */
	public boolean addToInventory(Powerup p) {
		if (inventory.size() < 5) {
			inventory.add(p);
			p.moveToLocation(x + 5 + inventory.indexOf(p) * 40, y + 5);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a powerup from the inventory
	 * @param index the index of the powerup to remove
	 * @return the powerup that was removed
	 */
	public Powerup removeFromInventory(int index) {
		Powerup p = inventory.remove(index);
		for (int i = 0; i < inventory.size(); i++) {
			inventory.get(i).moveToLocation(x + 5 + i * 40, y + 5);
		}
		return p;
	}
	
	/**
	 * Empties the inventory of all powerups
	 */
	public void clearInventory() {
		int j = inventory.size();
		for (int i = 0; i < j; i++ ) {
			this.removeFromInventory(0);
		}
	}
	
	
	public void draw(PApplet p) {
		
		p.push();
		
		p.stroke(255);
		//p.strokeWeight(2);
		p.fill(new Color(0xfaa1ae).getRGB());
		
		p.rect((float)x, (float)y, (float)(width), (float)(height));
		
		p.fill(255, 255, 255);
		p.textSize(11);
		
		p.text(1, (float)(x + 20), (float)(y + height - 2));
		p.text(2, (float)(x + 60), (float)(y + height - 2));
		p.text(3, (float)(x + 100), (float)(y + height - 2));
		p.text(4, (float)(x + 140), (float)(y + height - 2));
		p.text(5, (float)(x + 180), (float)(y + height - 2));
		
		for (int i = 0; i < inventory.size(); i++) {
			inventory.get(i).draw(p);
		}
		
		p.pop();
	}
	
}
