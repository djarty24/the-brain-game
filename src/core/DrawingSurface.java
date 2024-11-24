package core;




import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComboBox;

import jay.jaysound.JayLayer;
import jay.jaysound.JayLayerListener;
import processing.core.PApplet;
import screens.LevelSelector;
import screens.Menu;
import screens.Screen;
import screens.ScreenSwitcher;
import screens.WinScreen;
import screens.levels.Level1;
import screens.levels.Level2;
import screens.levels.Level3;
import screens.levels.Level4;
import screens.GameOverScreen;
import screens.Instructions;
import screens.Level;

public class DrawingSurface extends PApplet implements ScreenSwitcher, JayLayerListener {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	private int progress;

	private JComboBox<String> effects;
	private JayLayer sound;
	String[] songs = new String[]{"bg-music.mp3"};
	private long[] times;
	
	public DrawingSurface() {
		
		sound=new JayLayer("audio/","audio/",false);
		sound.addPlayList();
		sound.addSongs(0,songs);
		sound.changePlayList(0);
		sound.addJayLayerListener(this);
		sound.nextSong();
		times = new long[4];

		progress = 1;
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		Menu screen0 = new Menu(this);
		screens.add(screen0);

		Instructions screen1 = new Instructions(this);
		screens.add(screen1);

		LevelSelector screen2 = new LevelSelector(this);
		screens.add(screen2);
		
		Level1 level1 = new Level1(this);
		screens.add(level1);
		
		Level2 level2 = new Level2(this);
		screens.add(level2);
		
		Level3 level3 = new Level3(this);
		screens.add(level3);
		
		Level4 level4 = new Level4(this);
		screens.add(level4);
		
		WinScreen winscreen = new WinScreen(this);
		screens.add(winscreen);
		
		GameOverScreen gameOver = new GameOverScreen(this);
		screens.add(gameOver);
		
		activeScreen = screens.get(0);
	}
	public void setTime(int index, long time)
	{
		times[index] = time;
	}
	
	public long getTime(int index)
	{
		return times[index];
	}
	
	public long getTotalTime() {
		long sum = 0;
		for(long l : times) {
			long minutes = (int)(l / (1000 * 60));
			long seconds = (int)(l % (1000 * 60) / 1000);
			sum+=minutes*60000;
			sum+=seconds*1000;

		}
		return sum;
	}
	public void settings() {
		setSize(800,600);
	}
	
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
	}
	
	public void keyPressed() {
		if (!keys.contains(keyCode))
			keys.add(keyCode);
		if (key == ESC)  // This prevents a processing program from closing on escape key
			key = 0;
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(Integer.valueOf(keyCode));
		
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
		
	}
	
	public Screen getActiveScreen() {
		return activeScreen;
	}
	
	/** Returns the game's progress
	 * 
	 * @return progress
	 */
	public int getProgress() {
		return progress;
	}
	
	/** Increases progress by one.
	 *
	 */
	public void increaseProgress() {
		progress++;
	}

	@Override
	public void musicStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void musicStopped() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playlistEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void songEnded() {
		// TODO Auto-generated method stub
		
	}
}
