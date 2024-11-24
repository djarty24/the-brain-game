package screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import core.DrawingSurface;
import processing.core.PImage;
import sprites.Character;
import sprites.Enemy;
import sprites.Health;
import sprites.Inventory;
import sprites.Powerup;
import sprites.Projectile;
import sprites.Rabbit;
import sprites.Spike;
import sprites.Sprite;


public class Level extends Screen implements Scrollable {
	
	/**
	 * Keeps track of when the player started the current level. 
	 */
	public static long startTime;
	
	/**
	 * Keep track of the current minutes and seconds since the player started the level
	 */
	public static int minutes = 0, seconds = 0;
	
	protected Rectangle2D.Double screenRect, levelBorder, rulesPopup, returnToMenuButton;
	private boolean rulesPopupVisible = false;

	protected Rabbit r;
	protected Health hp;
	protected Inventory stuff ;
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Sprite> obstacles;
	protected ArrayList<Powerup> powerups;
	protected ArrayList<Spike> spikes;
	private PImage background, platform, menu, ruleson, rulesoff, rules;
	private static long levelTime;
	private Sprite checkpoint;
	protected boolean completed;
	protected long invincible; // -1 means false 
	protected long jump; // -1 means false
	protected long speed; // -1 means false
	protected boolean powerupActive = false;
	protected int levelNum;

	public Level(DrawingSurface surface) {
		super(800,600);
		levelNum = 0;
		invincible = -1;
		speed = -1;
		jump = -1;
		completed = false;
		this.surface = surface;
		stuff = new Inventory(290, 540, 205, 50);
		checkpoint = new Sprite(400,400,40,60);
		rulesPopup = new Rectangle2D.Double(DRAWING_WIDTH - 80, 10, 30, 30);
		returnToMenuButton = new Rectangle2D.Double(DRAWING_WIDTH - 40, 10, 30, 30);
		screenRect = new Rectangle2D.Double(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		levelBorder = new Rectangle2D.Double(-300, -300, DRAWING_WIDTH + 2000, DRAWING_HEIGHT + 500); // change if necessary
		enemies = new ArrayList<Enemy>();
		powerups = new ArrayList<Powerup>();
	}
	

	public void spawnNewCharacter() {
		ArrayList<PImage> imgs = new ArrayList<PImage>();
		imgs.add(surface.loadImage("img/rabbit-right.png"));
		imgs.add(surface.loadImage("img/rabbit-left.png"));
		imgs.add(surface.loadImage("img/rabbit-right-jump.png"));
		imgs.add(surface.loadImage("img/rabbit-left-jump.png"));
		r = new Rabbit(imgs, 0, 0);
		if(invincible!=-1) {
			r.glow(Rabbit.INVINCIBILITY);
		} else if(speed!=-1) {
			r.glow(Rabbit.SPEED);

		} else if(jump!=-1) {
			r.glow(Rabbit.JUMP);

		}
			
	}
	
	public void spawnEnemies()
	{
		
	}
	
	// should be able to customize these in the constructor with ArrayList parameters;
	public void spawnEnemies(ArrayList<Point> locs) {
		if(locs==null) return;
		ArrayList<PImage> imgs = new ArrayList<PImage>();
		imgs.add(surface.loadImage("img/cardFox-right.png"));
		imgs.add(surface.loadImage("img/cardFox-left.png"));

		PImage projectile = surface.loadImage("img/icecream-right.png");
		for(int i = 0; i < locs.size(); i++)
		{
			enemies.add(new Enemy(imgs, (int)locs.get(i).getX(), (int)locs.get(i).getY(), projectile));
		}
	}
	
	public void spawnEnemies(ArrayList<Point> locs, String type) {
		if(locs==null) return;
		PImage projectile = surface.loadImage("img/icecream-right.png");

		ArrayList<PImage> imgs = new ArrayList<PImage>();
		if(type.equals(Enemy.FORWARD))
		{
			imgs.add(surface.loadImage("img/cardFox-right.png"));
			imgs.add(surface.loadImage("img/cardFox-left.png"));
		} else if(type.equals(Enemy.UP))
		{
			imgs.add(surface.loadImage("img/cardBadger-right.png"));
			imgs.add(surface.loadImage("img/cardBadger-left.png"));
			//projectile = surface.loadImage("img/icecream-down.png");
		} else if(type.equals(Enemy.DOWN)) {
			imgs.add(surface.loadImage("img/cardBadger-right.png"));
			imgs.add(surface.loadImage("img/cardBadger-left.png"));
			//projectile = surface.loadImage("img/icecream-down.png");
		}

		for(int i = 0; i < locs.size(); i++)
		{
			enemies.add(new Enemy(imgs, (int)locs.get(i).getX(), (int)locs.get(i).getY(), projectile, type));
		}
	}
	
	public void addPowerups() {
		
	}
	
	public void setCheckpoint(int x, int y) {
		checkpoint = new Sprite(x,y,40,66);
		//checkpoint = new Sprite(400,300,40,66);
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		spawnNewCharacter();
		spawnEnemies();
		addPowerups();
		checkpoint.setImage(surface.loadImage("img/checkpoint.png"));
		background = surface.loadImage("img/background.png");
		platform = surface.loadImage("img/regular-platform.png");
		menu = surface.loadImage("img/menu-button.png");
		ruleson = surface.loadImage("img/rules-off.png");
		rulesoff = surface.loadImage("img/rules-on.png");
		rules = surface.loadImage("img/rules.png");
		hp = new Health(10, 10, 28, 25, surface.loadImage("img/heart.png"), surface.loadImage("img/half-heart.png")); // does this work?
		startTime = System.currentTimeMillis();
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		
		// drawing stuff
		surface.image(background,0,0,800,600);
		
		// everything between push and pop will scroll
		surface.push();
		
		surface.translate((float)-screenRect.getX(),(float)-screenRect.getY());
		
		for (Spike s : spikes) {
			s.draw(surface);
		}
		
		for (Sprite s : obstacles) {
			for(int i = 0; i < (s.getMaxX()-s.getMinX())/32; i ++)
			{
				for(int j = 0; j < (s.getMaxY()-s.getMinY())/32; j ++)
				{
					surface.image(platform, (float)(s.getMinX()+32*i), (float)(s.getMinY()+32*j));
				}
			}
		}
		
		r.draw(surface);
		
		for(Enemy e : enemies)
		{
			e.draw(surface);
		}
		
		for(Powerup p : powerups)
		{
			p.draw(surface);
		}
		
		checkpoint.draw(surface);
				
		// modifying stuff	
		int amp = 1;
		if(speed!=-1)
		{
			amp = 2;
		}
		
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			spawnNewCharacter();
			hp.restoreHealth();
			surface.switchScreen(ScreenSwitcher.LEVEL_SELECTOR);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT) || surface.isPressed(KeyEvent.VK_A))
		{
			r.walk(-4.5*amp);
		}
		
		if (surface.isPressed(KeyEvent.VK_RIGHT) || surface.isPressed(KeyEvent.VK_D))
		{
			r.walk(4.5*amp);
		
		}
		if (surface.isPressed(KeyEvent.VK_UP) || surface.isPressed(KeyEvent.VK_W)) {
			r.jump();
			
		}
		
		r.act(obstacles);
		
		if(r.intersects(checkpoint)) {
			endLevel();
		}
		
		for(int i = powerups.size()-1; i>= 0; i--)
		{
			if(r.intersects(powerups.get(i))) {
				// put the powerup in the inventory (once the class exists)
				if (stuff.addToInventory(powerups.get(i))) {
					// line of code ^
					powerups.remove(i);
				}
				
			}
		}
		
		if (surface.isPressed(KeyEvent.VK_1)) activatePowerup(0);
		if (surface.isPressed(KeyEvent.VK_2)) activatePowerup(1);
		if (surface.isPressed(KeyEvent.VK_3)) activatePowerup(2);
		if (surface.isPressed(KeyEvent.VK_4)) activatePowerup(3);
		if (surface.isPressed(KeyEvent.VK_5)) activatePowerup(4);

		if(invincible==-1)
		{
			boolean die = r.inDanger(enemies);
			boolean impaled = false;
			for (Spike s : spikes) {
				if (s.intersects(r)) {
					impaled = true;
				}
			}
			// might change this all enemies need to do is pace, don't really use gravity
			for(Enemy e : enemies)
			{
				e.act(obstacles);
				ArrayList<Projectile> icecreams = e.getIceCreams();
				for (Projectile  p : icecreams)
				{
					if(r.intersects(p))
					{
						spawnNewCharacter();
						hp.reduceHealth();
					}
				}
			}
			
			if (!levelBorder.intersects(r) || die || impaled) {
				spawnNewCharacter();
				hp.reduceHealth();
			}
		} else
		{
			for(int i = enemies.size()-1; i>=0; i--)
			{
				enemies.get(i).act(obstacles);
				if(r.intersects(enemies.get(i)) ) 
				{
					enemies.remove(i);
				}
			}
		}
		
		// game over
		if (hp.getHealth() == 0) {
			stuff.clearInventory();
			hp.restoreHealth();
			surface.switchScreen(ScreenSwitcher.GAME_OVER_SCREEN);
		}
		
		surface.pop();
		
		hp.draw(surface);
		stuff.draw(surface);
		
		if(invincible!=-1 && levelTime >= invincible + 5000)
		{
			invincible = -1;
			r.unglow(Rabbit.INVINCIBILITY);
			powerupActive = false;
		}
		
		if(speed!=-1 && levelTime >= speed + 5000)
		{
			speed = -1;
			r.unglow(Rabbit.SPEED);
			powerupActive = false;
		}
		
		if(jump!=-1 && levelTime >= jump + 5000)
		{
			jump = -1;
			r.unglow(Rabbit.JUMP);
			powerupActive = false;
		}

		surface.push();
		
		surface.fill(0, 0, 0);
		surface.textSize(30);
		
		levelTime = System.currentTimeMillis() - startTime;
		minutes = (int)(levelTime / (1000 * 60));
		seconds = (int)(levelTime % (1000 * 60) / 1000);
		
		if (minutes == 0 && seconds == 0) {
			surface.text("00:00", 10, 70);
		} else if (minutes < 10 && seconds < 10){
			surface.text("0" + minutes + ":0" + seconds, 10, 70);
		} else if (minutes < 10) {
			surface.text("0" + minutes + ":" + seconds, 10, 70);
		} else if (seconds < 10){
			surface.text(minutes + ":0" + seconds, 10, 70);
		} else {
			surface.text(minutes + ":" + seconds, 10, 70);
		}
		
		surface.pop();
		
        surface.image(menu, (float) returnToMenuButton.getX(), (float) returnToMenuButton.getY(), (float) returnToMenuButton.getWidth(), (float) returnToMenuButton.getHeight());
        if (rulesPopupVisible) {
            surface.image(rules, 75, 50, DRAWING_WIDTH - 125, DRAWING_HEIGHT - 125);
            surface.image(rulesoff, (float) rulesPopup.getX(), (float) rulesPopup.getY(), (float) rulesPopup.getWidth(), (float) rulesPopup.getHeight());
        } else
            surface.image(ruleson, (float) rulesPopup.getX(), (float) rulesPopup.getY(), (float) rulesPopup.getWidth(), (float) rulesPopup.getHeight());

		
		// update screenRect (part of scrolling)
		screenRect = Scrollable.slideWorldToImage(r, this);
	}
	
	public double getWidth() {
		return 800;
	}
	
	public double getHeight() {
		return 600;
	}
		
    public void mousePressed() {
        Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX, surface.mouseY));
        if (rulesPopup.contains(p))
            rulesPopupVisible = !rulesPopupVisible;
        else if (returnToMenuButton.contains(p)) {
        	spawnNewCharacter();
			hp.restoreHealth();
			stuff.clearInventory();
        	surface.switchScreen(ScreenSwitcher.LEVEL_SELECTOR);
        }
    }
    
    private void endLevel() {
    	spawnNewCharacter();
		hp.restoreHealth();
		stuff.clearInventory();

		surface.setTime(levelNum-1, levelTime);
		if(!completed && surface.getProgress()==4)
		{
			surface.switchScreen(ScreenSwitcher.WIN_SCREEN);
			return;
		}
		
		if(!completed) surface.increaseProgress();
		completed = true;
		surface.switchScreen(ScreenSwitcher.LEVEL_SELECTOR);
    }
    
    private void activatePowerup(int i)
    {
    	if(stuff.getInventory().size()>i && !powerupActive) {
    		powerupActive = true;
			Powerup p = stuff.removeFromInventory(i);
			if (p.getType().equals("invincibility"))
			{
				invincible = System.currentTimeMillis() - startTime;
				r.glow(Rabbit.INVINCIBILITY);

			} else if(p.getType().equals("speed"))
			{
				speed = System.currentTimeMillis() - startTime;
				r.glow(Rabbit.SPEED);

			} else
			{
				jump = System.currentTimeMillis() - startTime;
				r.glow(Rabbit.JUMP);

			}
		}
    }
    
    public void restart()
    {
    	invincible = -1;
		speed = -1;
		jump = -1;
		powerupActive = false;
		spawnNewCharacter();
    	enemies = new ArrayList<Enemy>();
		powerups = new ArrayList<Powerup>();
		stuff.clearInventory();
    	spawnEnemies();
    	addPowerups();
    }


}