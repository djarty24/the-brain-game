package screens.levels;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import core.DrawingSurface;
import screens.Level;
import screens.ScreenSwitcher;
import screens.Scrollable;
import sprites.Character;
import sprites.Enemy;
import sprites.Platform;
import sprites.Powerup;
import sprites.Rabbit;
import sprites.Spike;
import sprites.Sprite;

public class Level1 extends Level{
		
	public Level1(DrawingSurface surface) {
		super(surface);
		levelNum = 1;

		spikes = new ArrayList<Spike>();
		spikes.add(new Spike(-100, DRAWING_HEIGHT + 300, DRAWING_WIDTH + 1000, 30));
		setCheckpoint(1070, 288);

		obstacles = new ArrayList<Sprite>();
		obstacles.add(new Platform(32*17,  32*9, 32*3, 32*2));
		obstacles.add(new Platform(32*14, 32*15, 32*5, 32));
		obstacles.add(new Platform(32*23, 32*13, 32*3, 32));
		obstacles.add(new Platform(32*30, 32*11, 32*5, 32));
		obstacles.add(new Platform( 32*3, 32*17, 32*5, 32));
		obstacles.add(new Platform(32*14, 32*15, 32*5, 32));
		obstacles.add(new Platform(32*30,  32*4, 32*5, 32));
		obstacles.add(new Platform(32*33, 32*19, 32*5, 32));
		obstacles.add(new Platform(32*35,  32*4, 32, 32*8));
		obstacles.add(new Platform(32*14, 32*15, 32*5, 32));
		obstacles.add(new Platform(32*20, 32*18, 32*3, 32));
		obstacles.add(new Platform(32*14, 32*15, 32*5, 32));
		obstacles.add(new Platform(32*14, 32*15, 32*5, 32));
		obstacles.add(new Platform(32*20, 32*25, 32*4, 32));
		obstacles.add(new Platform(32*28, 32*22, 32*3, 32));
		obstacles.add(new Platform(32*40, 32*15, 32*5, 32));
		obstacles.add(new Platform(32*36,  32*8, 32*2, 32));
		obstacles.add(new Platform(32*43, 32*11, 32*2, 32));
		
		// Borders
		obstacles.add(new Platform(-400, -560, DRAWING_WIDTH*2 + 500, 360));
		obstacles.add(new Platform(-600, -400, 500, DRAWING_HEIGHT*2 + 400));
		obstacles.add(new Platform(DRAWING_WIDTH*2, -400, 400, DRAWING_HEIGHT*2 + 200));
		obstacles.add(new Platform(-400, DRAWING_HEIGHT + 330, DRAWING_WIDTH*2 + 900, 400));
		
		enemies = new ArrayList<Enemy>();

	}
	
	public void spawnNewCharacter() {
		super.spawnNewCharacter();
		r.moveToLocation(38*12, 0);
	}
	
	public void spawnEnemies() {
		ArrayList<Point> locs = new ArrayList<Point>();
		locs.add(new Point(32*2, 32*15));

		super.spawnEnemies(locs, Enemy.FORWARD);
		locs.set(0, new Point(32*30, 32*2));
		super.spawnEnemies(locs, Enemy.DOWN);


		//	enemies.add(new Enemy(surface.loadImage("img/cardFox.png"), 32*2, 32*15, 32*4));
	//	enemies.add(new Enemy(surface.loadImage("img/cardFox.png"), 32*30, 32*2, 32*4));
		
	}

	
	public void addPowerups() {
		powerups.add(new Powerup(surface.loadImage("img/speed-cake.png"), 32*25, 0, 35, 35, "speed"));
		powerups.add(new Powerup(surface.loadImage("img/jump-cake.png"), DRAWING_WIDTH/2+200, 100, 35, 35, "jump"));
		powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 300, 300, 35, 35, "invincibility"));
		
		// for testing purpose, comment out later
		//powerups.add(new Powerup(surface.loadImage("img/invincibility-cake.png"), 580, 400, 35, 35, "invincibility"));

	}
	
}
