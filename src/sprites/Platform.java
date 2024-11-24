package sprites;

public class Platform extends Sprite {
	
	public Platform(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
