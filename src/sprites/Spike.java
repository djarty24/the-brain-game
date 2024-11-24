package sprites;

import processing.core.PApplet;

public class Spike extends Platform {

	public Spike(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void draw(PApplet p) {
		p.push();
		
		p.fill(158, 244, 247);
		p.stroke(255, 255, 255);
		
		for (int i = 0; i < width / 20; i++) {
			p.triangle((float)x + i * 20, (float)(y + height), (float)x + 10 + i * 20, (float)y, (float)x + 20 + i * 20, (float)(y + height));
		}
		p.pop();
	}
}
