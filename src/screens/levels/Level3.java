package screens.levels;

import java.awt.Point;
import java.util.ArrayList;

import core.DrawingSurface;
import screens.Level;
import sprites.Character;
import sprites.Enemy;
import sprites.Platform;
import sprites.Powerup;
import sprites.Rabbit;
import sprites.Spike;
import sprites.Sprite;

public class Level3 extends Level{
	
	public Level3(DrawingSurface surface) {
		super(surface);
		levelNum = 3;

		setCheckpoint(1160, -180);
		obstacles = new ArrayList<Sprite>();
		spikes = new ArrayList<Spike>();
		enemies = new ArrayList<Enemy>();
		
		spikes.add(new Spike(-100, DRAWING_HEIGHT + 300, DRAWING_WIDTH + 1000, 30));
		
		obstacles.add(new Platform(-400, -592, DRAWING_WIDTH*2 + 500, 400));
		obstacles.add(new Platform(-500, -400, 385, DRAWING_HEIGHT*2 + 200));
		obstacles.add(new Platform(DRAWING_WIDTH*2 + 100, -600, 400, DRAWING_HEIGHT*2 + 400));
		obstacles.add(new Platform(-500, DRAWING_HEIGHT + 330, DRAWING_WIDTH*2 + 800, 300));
		
		obstacles.add(new Platform(0, 100, 32 * 9, 32));
		spikes.add(new Spike(100, 100 - 30, 80, 30));
		
		obstacles.add(new Platform(200, 300, 32 * 4, 32));
		obstacles.add(new Platform(500, 300, 32 * 4, 32));
		obstacles.add(new Platform(600, 150, 32 * 2, 32));
		obstacles.add(new Platform(800 - 32, 300, 32 * 11, 32));
		obstacles.add(new Platform(800, 200, 32 * 10, 32));
		obstacles.add(new Platform(1120, -216, 32, 432));
		obstacles.add(new Platform(1120, 300, 32, 700));
		obstacles.add(new Platform(1000, 100, 32, 32 * 2));
		obstacles.add(new Platform(900, -20, 32, 32 * 2));
		obstacles.add(new Platform(600, 450, 32 * 3, 32));
		obstacles.add(new Platform(800, 560, 32 * 3, 32));
		obstacles.add(new Platform(1120 - 64, 630, 32 * 2, 32));
		
		obstacles.add(new Platform(1120 + 32, 800, 32 * 10, 32));
		spikes.add(new Spike(1300, 800 - 30, 20, 30));
		
		obstacles.add(new Platform(1400, 700, 32 * 10, 32));
		obstacles.add(new Platform(1600, 560, 32, 32 * 2));
		obstacles.add(new Platform(1500, 420, 32, 32 * 2));
		obstacles.add(new Platform(1600, 280, 32, 32 * 2));
		obstacles.add(new Platform(1500, 140, 32, 32 * 2));
		obstacles.add(new Platform(1600, 0, 32, 32 * 2));
		obstacles.add(new Platform(1120 + 32, -115, 32 * 12, 32));
		
	}
	
	public void spawnEnemies() {
		ArrayList<Point> locs = new ArrayList<Point>();
		locs.add(new Point(160, 240));
		locs.add(new Point(900, 240));
		locs.add(new Point(1500, 640));

		super.spawnEnemies(locs);
			
	}
	
	public void addPowerups() {
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 200, 180, 35, 35, "invincibility")); 
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 1000, -170, 35, 35, "invincibility")); 
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 1070, 580, 35, 35, "invincibility"));
		
	}
	
}