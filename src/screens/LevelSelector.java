package screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import core.DrawingSurface;
import processing.core.PImage;

public class LevelSelector extends Screen {
	
	private PImage bg, intro;
	private ArrayList<Rectangle> levels;
	private Rectangle button;
	private int focus;
	private boolean mouseSet, introOn;

	public LevelSelector(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		levels = new ArrayList<Rectangle>();
		// level1 
		levels.add(new Rectangle(DRAWING_WIDTH/4, DRAWING_HEIGHT/2 - 15, DRAWING_WIDTH/4, DRAWING_HEIGHT/8));
		// level2
		levels.add(new Rectangle(DRAWING_WIDTH/2 + 25, DRAWING_HEIGHT/2 - 15, DRAWING_WIDTH/4, DRAWING_HEIGHT/8));
		// level3
		levels.add(new Rectangle(DRAWING_WIDTH/4, DRAWING_HEIGHT/2 + DRAWING_HEIGHT/8 + 10, DRAWING_WIDTH/4, DRAWING_HEIGHT/8));
		// level4
		levels.add(new Rectangle(DRAWING_WIDTH/2 + 25, DRAWING_HEIGHT/2 + DRAWING_HEIGHT/8 + 10, DRAWING_WIDTH/4, DRAWING_HEIGHT/8));
		
		button = new Rectangle(DRAWING_WIDTH/2 - 100, 4*DRAWING_HEIGHT/5, 200,50);
		focus = 3;
		mouseSet = false;
		introOn = false;
	}
	
	public void setup() {
		bg = surface.loadImage("img/level-selector.png");
		intro = surface.loadImage("img/intro.png");

	}

	public void draw() {

		surface.image(bg, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(255);
		surface.strokeWeight(3);
		
		if(!mouseSet)
		{
			focus = surface.getProgress()+2;
		}

		if (focus == 3) {
			surface.stroke(255, 102, 196);
			surface.rect(levels.get(0).x, levels.get(0).y, levels.get(0).width, levels.get(0).height, 10, 10, 10, 10);
			surface.stroke(0);
			surface.rect(levels.get(1).x, levels.get(1).y, levels.get(1).width, levels.get(1).height, 10, 10, 10, 10);
			surface.rect(levels.get(2).x, levels.get(2).y, levels.get(2).width, levels.get(2).height, 10, 10, 10, 10);
			surface.rect(levels.get(3).x, levels.get(3).y, levels.get(3).width, levels.get(3).height, 10, 10, 10, 10);
		} else if (focus == 4) {
			surface.stroke(0);
			surface.rect(levels.get(0).x, levels.get(0).y, levels.get(0).width, levels.get(0).height, 10, 10, 10, 10);
			surface.stroke(255, 102, 196);
			surface.rect(levels.get(1).x, levels.get(1).y, levels.get(1).width, levels.get(1).height, 10, 10, 10, 10);
			surface.stroke(0);
			surface.rect(levels.get(2).x, levels.get(2).y, levels.get(2).width, levels.get(2).height, 10, 10, 10, 10);
			surface.rect(levels.get(3).x, levels.get(3).y, levels.get(3).width, levels.get(3).height, 10, 10, 10, 10);
		} else if (focus == 5) {
			surface.stroke(0);
			surface.rect(levels.get(0).x, levels.get(0).y, levels.get(0).width, levels.get(0).height, 10, 10, 10, 10);
			surface.rect(levels.get(1).x, levels.get(1).y, levels.get(1).width, levels.get(1).height, 10, 10, 10, 10);
			surface.rect(levels.get(3).x, levels.get(3).y, levels.get(3).width, levels.get(3).height, 10, 10, 10, 10);
			surface.stroke(255, 102, 196);
			surface.rect(levels.get(2).x, levels.get(2).y, levels.get(2).width, levels.get(2).height, 10, 10, 10, 10);
			surface.stroke(0);
		} else if (focus == 6) {
			surface.stroke(0);
			surface.rect(levels.get(0).x, levels.get(0).y, levels.get(0).width, levels.get(0).height, 10, 10, 10, 10);
			surface.rect(levels.get(1).x, levels.get(1).y, levels.get(1).width, levels.get(1).height, 10, 10, 10, 10);
			surface.rect(levels.get(2).x, levels.get(2).y, levels.get(2).width, levels.get(2).height, 10, 10, 10, 10);
			surface.stroke(255, 102, 196);
			surface.rect(levels.get(3).x, levels.get(3).y, levels.get(3).width, levels.get(3).height, 10, 10, 10, 10);
			surface.stroke(0);
		} else {
			surface.stroke(0);
			surface.rect(levels.get(0).x, levels.get(0).y, levels.get(0).width, levels.get(0).height, 10, 10, 10, 10);
			surface.rect(levels.get(1).x, levels.get(1).y, levels.get(1).width, levels.get(1).height, 10, 10, 10, 10);
			surface.rect(levels.get(2).x, levels.get(2).y, levels.get(2).width, levels.get(2).height, 10, 10, 10, 10);
			surface.rect(levels.get(3).x, levels.get(3).y, levels.get(3).width, levels.get(3).height, 10, 10, 10, 10);
		}
		
		surface.push();
		surface.fill(0);
		surface.textSize(23);
		for(int i = 0; i < levels.size(); i++)
		{
			if(surface.getProgress() > i+1 || surface.getProgress()==4)
			{
				long total = surface.getTime(i);
				long minutes = (int)(total / (1000 * 60));
				long seconds = (int)(total % (1000 * 60) / 1000);	
				
				if (minutes == 0 && seconds == 0) {
					surface.text("00:00", levels.get(i).x, levels.get(i).y - 4);
				} else if (minutes < 10 && seconds < 10){
					surface.text("0" + minutes + ":0" + seconds, levels.get(i).x, levels.get(i).y - 4);
				} else if (minutes < 10) {
					surface.text("0" + minutes + ":" + seconds, levels.get(i).x, levels.get(i).y - 4);
				} else if (seconds < 10){
					surface.text("" + minutes + ":0" + seconds, levels.get(i).x, levels.get(i).y - 4);
				} else {
					surface.text("" + minutes + ":" + seconds, levels.get(i).x, levels.get(i).y - 4);
				}
			}
		}
		surface.pop();

		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		
		surface.fill(255, 102, 196);
		surface.textSize(35);
		String str = "Level 1";
		float w = surface.textWidth(str);
		surface.text("Level 1", levels.get(0).x+levels.get(0).width/2-w/2, levels.get(0).y+levels.get(0).height - 25);
		surface.text("Level 2", levels.get(1).x+levels.get(1).width/2-w/2, levels.get(1).y+levels.get(1).height - 25);
		surface.text("Level 3", levels.get(2).x+levels.get(2).width/2-w/2, levels.get(2).y+levels.get(2).height - 25);
		surface.text("Level 4", levels.get(3).x+levels.get(3).width/2-w/2, levels.get(3).y+levels.get(3).height - 25);
		surface.text("Play!", button.x+button.width/2-surface.textWidth("Play!")/2, button.y+button.height - 15);
		
		for(int i = 0; i<levels.size(); i++)
		{
			if (surface.mouseX > levels.get(i).x && surface.mouseX < levels.get(i).x+levels.get(i).width && 
				surface.mouseY > levels.get(i).y && surface.mouseY < levels.get(i).y+levels.get(i).height) {
				if(surface.getProgress()<(i+1))
				{
					surface.push();
					surface.noStroke();
					surface.rect(levels.get(i).x+50, levels.get(i).y-15, 100, 30);
					surface.textSize(20);
					surface.fill(255);
					surface.text("locked", levels.get(i).x+72, levels.get(i).y-14, 100, 30);
					surface.pop();

				}
			 }
		}
					
		
		if(introOn)
		{
			surface.image(intro, 0, 0, 800, 600);
		}
		
	}



	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		//locking
		if(introOn) {
			surface.switchScreen(3);
			((Level)surface.getActiveScreen()).restart();
			mouseSet=false;
			introOn = false;
		}
		if (levels.get(0).contains(p) && surface.getProgress()>=1)
		{
			focus = 3;
			mouseSet=true;
		} else if (levels.get(1).contains(p) && surface.getProgress()>=2)
		{
			focus = 4;
			mouseSet=true;
		} else if (levels.get(2).contains(p) && surface.getProgress()>=3)	
		{
			focus = 5;
			mouseSet=true;
		} else if (levels.get(3).contains(p) && surface.getProgress()>=4)
		{
			focus = 6;
			mouseSet=true;
		}
		
		//*/
		
		if (button.contains(p))
		{
			surface.switchScreen(focus);
			((Level)surface.getActiveScreen()).restart();
			mouseSet=false;

		}
		
		// need this for timer
		Level.startTime = System.currentTimeMillis();
		
	}
	
	public void showIntro()
	{
		introOn = true;
	}


}

