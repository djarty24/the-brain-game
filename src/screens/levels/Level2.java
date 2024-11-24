package screens.levels;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import core.DrawingSurface;
import screens.Level;
import sprites.Enemy;
import sprites.Platform;
import sprites.Powerup;
import sprites.Spike;
import sprites.Sprite;

public class Level2 extends Level{
		
	public Level2(DrawingSurface surface) {
		super(surface);
		levelNum = 2;

		levelBorder = new Rectangle2D.Double(-400, -400, DRAWING_WIDTH*2 + 500, DRAWING_HEIGHT*2 + 500); // change if necessary
		spikes = new ArrayList<Spike>();

		obstacles = new ArrayList<Sprite>();
		obstacles.add(new Platform(60, 100, 50, 50));
		obstacles.add(new Platform(32*10, 140, 32, 32));
		obstacles.add(new Platform(32*18 - 15, 100, 50, 32*3));
		obstacles.add(new Platform(32*15, 350, 32, 32*4));
		obstacles.add(new Platform(32*25, 350, 32*5, 50));
		obstacles.add(new Platform(32*39, 325, 32*2, 32));
		obstacles.add(new Platform(32*39, 450, 32*2, 32));
		obstacles.add(new Platform(32*48 + 15, 450, 32, 50));
		obstacles.add(new Platform(32*48 + 15, 325, 32, 32));
		obstacles.add(new Platform(32*30, 650, 32*3, 32));
		obstacles.add(new Platform(32*13, 500, 32*5, 32));
		obstacles.add(new Platform(32*25, 600, 32, 32));
		obstacles.add(new Platform(32*45, 800, 32*5, 32*5));
		
		// Borders
		obstacles.add(new Platform(-400, -560, DRAWING_WIDTH*2 + 500, 360));
		obstacles.add(new Platform(-600, -400, 600, DRAWING_HEIGHT*2 + 400));
		obstacles.add(new Platform(-400, DRAWING_HEIGHT*2, DRAWING_WIDTH*2 + 500, 400));
		obstacles.add(new Platform(DRAWING_WIDTH*2, -400, 600, DRAWING_HEIGHT*2 + 800));
		
		spikes.add(new Spike(0, DRAWING_HEIGHT*2 - 33, DRAWING_WIDTH*2, 32));
		spikes.add(new Spike(32*31, 650-33, 60, 32));

		enemies = new ArrayList<Enemy>();
		setCheckpoint(32*48, 730);

	}
	
	public void spawnNewCharacter() {
		super.spawnNewCharacter();
		r.moveToLocation(75, 0);
	}
	
	public void spawnEnemies() {
		ArrayList<Point> locs = new ArrayList<Point>();
		locs.add(new Point(32*25, 250));
		super.spawnEnemies(locs);
		enemies.get(0).setRange(50);
	}
	
	public void addPowerups() {
		powerups.add(new Powerup(surface.loadImage("img/speed-cake.png"), 32*40 - 15, 250, 35, 35, "speed"));
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 32*10 - 15, 300, 35, 35, "invincibility"));
		powerups.add(new Powerup(surface.loadImage("img/jump-cake.png"), 33*30, 525, 35, 35, "jump"));
	}
	
}
