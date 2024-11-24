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

public class Level4 extends Level{
	
	public Level4(DrawingSurface surface) {
		super(surface);
		levelNum = 4;
		setCheckpoint(1245, 790);
		spikes = new ArrayList<Spike>();
		obstacles = new ArrayList<Sprite>();
		enemies = new ArrayList<Enemy>();
		
		spikes.add(new Spike(-100, DRAWING_HEIGHT + 300, DRAWING_WIDTH + 1000, 30));
		
		obstacles.add(new Platform(-400, -592, DRAWING_WIDTH*2 + 500, 400));
		obstacles.add(new Platform(-500, -400, 385, DRAWING_HEIGHT*2 + 200));
		obstacles.add(new Platform(DRAWING_WIDTH*2 + 100, -600, 400, DRAWING_HEIGHT*2 + 400));
		obstacles.add(new Platform(-500, DRAWING_HEIGHT + 330, DRAWING_WIDTH*2 + 1000, 300));
		
		obstacles.add(new Platform(-100, 100, 32 * 9, 32));
		spikes.add(new Spike(-80, 100 - 30, 20, 30));
		spikes.add(new Spike(140, 100 - 30, 40, 30));
		
		obstacles.add(new Platform(-100, 600, 32 * 2, 32 * 2));
		obstacles.add(new Platform(0, 400, 32 * 4, 32));
		obstacles.add(new Platform(300, -202, 32, 960));
		
		obstacles.add(new Platform(190, 726, 32 * 4, 32));
		spikes.add(new Spike(280, 726 - 30, 20, 30));
		
		obstacles.add(new Platform(140, 850, 32 * 15, 32));
		obstacles.add(new Platform(600, 700, 32 * 7, 32));
		obstacles.add(new Platform(970, 700, 32 * 2, 32));
		obstacles.add(new Platform(400, 550, 32 * 7, 32));
		obstacles.add(new Platform(600, 400, 32 * 7, 32));
		obstacles.add(new Platform(400, 250, 32 * 7, 32));
		obstacles.add(new Platform(600, 100, 32 * 20, 32));
		obstacles.add(new Platform(1400, 100, 32, 32));
		obstacles.add(new Platform(1600, 100, 32, 32 * 21));
		obstacles.add(new Platform(1500, 850, 32 * 8, 32));
		obstacles.add(new Platform(1200, 740, 32 * 13, 32));
		obstacles.add(new Platform(1200, 740, 32, 32 * 8));
		obstacles.add(new Platform(1200, 850, 32 * 4, 32 * 3));

	}
	
	public void spawnEnemies() {
		ArrayList<Point> locs = new ArrayList<Point>();
		locs.add(new Point(630, 640));
		locs.add(new Point(430, 490));
		locs.add(new Point(630, 340));
		locs.add(new Point(430, 190));
		locs.add(new Point(700, 40));
		locs.add(new Point(900, 40));

		//super.spawnEnemies(locs);
		super.spawnEnemies(locs, Enemy.UP);
			
	}
	
	public void addPowerups() {
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 40, 150, 35, 35, "invincibility"));
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), -70, 540, 35, 35, "invincibility"));
		powerups.add(new Powerup(surface.loadImage("img/jump-cake.png"), 260, 600, 35, 35, "jump"));
		powerups.add(new Powerup(surface.loadImage("img/speed-cake.png"), 350, 450, 35, 35, "speed"));
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 1550, 800, 35, 35, "invincibility"));
		
	}
}
