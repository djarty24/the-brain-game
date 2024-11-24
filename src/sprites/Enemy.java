package sprites;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends Character {

	private double range;
	private int pace;
	private ArrayList<Projectile> icecreams;
	private PImage projectile;
	private double walkEnd; // 0 is false
	private String type;
	public static final String FORWARD = "forward";
	public static final String UP = "up";
	public static final String DOWN = "down";

	public Enemy(PImage img, int x, int y, double r, PImage p, String type) {
		super(img, x, y, (int)(65*1.6), (int)(38*1.6));
		range=r;
		walkEnd = 0;
		pace = 1;
		icecreams = new ArrayList<Projectile>();
		projectile = p;
		this.type = type;
		
	}
	
	public Enemy(PImage img, int x, int y, double r, PImage p) {
		super(img, x, y, (int)(65*1.6), (int)(38*1.6));
		type = Enemy.FORWARD;
		range=r;
		walkEnd = 0;
		pace = 1;
		icecreams = new ArrayList<Projectile>();
		projectile = p;
		
	}
	
	public Enemy(ArrayList<PImage> imgs, int x, int y, PImage p) {
		super(imgs, x, y, (int)(65*1.6), (int)(38*1.6));
		type = Enemy.FORWARD;
		range=32*3;
		walkEnd = 0;
		pace = 1;
		icecreams = new ArrayList<Projectile>();
		projectile = p;
		
	}
	
	public Enemy(ArrayList<PImage> imgs, int x, int y, PImage p, String type) {
		super(imgs, x, y, (int)(65*1.6), (int)(38*1.6));
		this.type = type;
		range=32*3;
		walkEnd = 0;
		pace = 1;
		icecreams = new ArrayList<Projectile>();
		projectile = p;
		
	}
	
	public Enemy(ArrayList<PImage> imgs, int x, int y, double r, PImage p) {
		super(imgs, x, y, (int)(65*1.6), (int)(38*1.6));
		type = Enemy.FORWARD;
		range=r;
		walkEnd = 0;
		pace = 1;
		icecreams = new ArrayList<Projectile>();
		projectile = p;

		
	}
	
	public void act(List<Sprite> obstacles) {
		super.act(obstacles);
		
		if(walkEnd==0)
		{
			walkEnd=x+range*(pace/Math.abs(pace));
		}
		if(x!=walkEnd)
		{
			walk(pace);
		} else {
			walkEnd=0;
			pace*=-1;
		}
		
		if((int)x%40==0)
		{
			if(type.equals(Enemy.FORWARD))
			{
				icecreams.add(new Projectile(projectile, (int)(this.getCenterX()+this.width/2*pace), (int)this.getCenterY(), 38, 19, pace));

			} else if (type.equals(Enemy.UP))
			{
				icecreams.add(new Projectile(projectile, (int)(this.getCenterX()), (int)this.getCenterY() - this.width/2, 38, 19, pace));

			} else if (type.equals(Enemy.DOWN)) {
				icecreams.add(new Projectile(projectile, (int)(this.getCenterX()), (int)this.getCenterY() + this.width/2, 38, 19, pace));
			}
		}

	}
	
	public void setRange(double r)
	{
		range = r;
	}
	public ArrayList<Projectile> getIceCreams() {
		return icecreams;
	}
	
	public void draw(PApplet g) {
		super.draw(g);
		for(int i = icecreams.size()-1; i >= 0; i --){
			if(type.equals(Enemy.FORWARD))
			{
				icecreams.get(i).moveByAmount(10*icecreams.get(i).getDirection(),0);
				icecreams.get(i).draw(g);
				if(Math.abs(icecreams.get(i).getCenterX()-this.getCenterX()) >= 400)
				{
					icecreams.remove(i);
				}
			} else if (type.equals(Enemy.UP))
			{
				icecreams.get(i).moveByAmount(0,-10);
				icecreams.get(i).draw(g);
				if(Math.abs(icecreams.get(i).getCenterY()-this.getCenterY()) >= 400)
				{
					icecreams.remove(i);
				}
			} else if (type.equals(Enemy.DOWN))
			{
				icecreams.get(i).moveByAmount(0,10);
				icecreams.get(i).draw(g);
				if(Math.abs(icecreams.get(i).getCenterY()-this.getCenterY()) >= 400)
				{
					icecreams.remove(i);
				}
			}
		}
	}
	
	
}
