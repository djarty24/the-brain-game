package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import jay.jaysound.JayLayer;
import jay.jaysound.JayLayerListener;
import processing.core.PApplet;
import processing.core.PImage;

public class Rabbit extends Character implements JayLayerListener {

	private double isJumping; // 0 false, anything else true
	private Color poweredUp;
	private boolean powerJump;
	private boolean invincible;
	private boolean speed;
	private boolean soundPlayed; // Track if the sound has been played

	private JComboBox<String> effects;
	private JayLayer sound = new JayLayer("audio/","audio/",false);

	public static final Color SPEED = new Color(255, 156, 171); // pink
	public static final Color JUMP= new Color(178, 255, 156); // green 
	public static final Color INVINCIBILITY = new Color(196, 161, 255); // purple

	public Rabbit(PImage img, int x, int y) {
		super(img, x, y);
		isJumping = 0;
		poweredUp = null;
		powerJump = false;
		soundPlayed = false;
	}
	
	public Rabbit(ArrayList<PImage> imgs, int x, int y) {
		super(imgs, x, y);
		isJumping = 0;
		poweredUp = null;
		powerJump = false;
		soundPlayed = false;
		String[] soundEffects = new String[]{"boing.mp3"};
		sound.addSoundEffects(soundEffects);
		sound.addJayLayerListener(this);
	}

	public void jump() {
		if (isOn != null || isJumping != 0) {
			if (powerJump) isJumping -= 6;

			isJumping += 8;
			yVel = -isJumping;
			isOn = null;
			
			// Reset soundPlayed at the start of the jump
			if (isJumping == 8) {
				soundPlayed = false;
			}
		}

		// Check if the sound has already been played for this jump
		if (!soundPlayed) {
			sound.playSoundEffect(0);
			soundPlayed = true; // Set soundPlayed to true after playing the sound
		}

		if (powerJump && isJumping == 64) {
			isJumping = 0;
			soundPlayed = false; // Reset soundPlayed when jump ends
		} else if (isJumping == 32) {
			isJumping = 0;
			soundPlayed = false; // Reset soundPlayed when jump ends
		}
	}
	
	public boolean inDanger(ArrayList<Enemy> enemies) {
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (this.intersects(enemies.get(i))) {
				if (!((enemies.get(i).getCenterY() - 20) > this.getCenterY())) {
					return true;
				} else {
					enemies.remove(i);
				}
			}
		}
		return false;
	}

	public void draw(PApplet g) {
		g.push();
		if (poweredUp != null) {
			g.tint(poweredUp.getRGB());
		}
		if (costumes != null) {
			PImage p = costumes.get(0);
			if (isOn == null) {
				p = costumes.get(2);
			}
			if (!facesRight) {
				p = costumes.get(1);
				if (isOn == null) {
					p = costumes.get(3);
				}
			}
			g.image(p, (float)x, (float)y, (float)width, (float)height);
		} else {
			super.draw(g);
		}
		g.pop();
	}
	
	public void glow(Color c) {
		poweredUp = c;
		if (c == Rabbit.JUMP) {
			powerJump = true;
		}
	}
	
	public void unglow(Color c) {
		poweredUp = null;
		if (c == Rabbit.JUMP) {
			powerJump = false;
		}
		if (c == Rabbit.SPEED) {
			speed = false;
		}
		if (c == Rabbit.INVINCIBILITY) {
			invincible = false;
		}
		if (invincible) {
			poweredUp = Rabbit.INVINCIBILITY;
		}
		if (speed) {
			poweredUp = Rabbit.SPEED;
		}
		if (powerJump) {
			poweredUp = Rabbit.JUMP;
		}
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
