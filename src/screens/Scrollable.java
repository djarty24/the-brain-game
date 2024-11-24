package screens;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import sprites.*;

public interface Scrollable {
		
	// Shelby demo code - modified
	/**
	 * Does math that gets scrolling to happen
	 * @param img - the center of the screen; the screen scrolls when it moves
	 * @param thisLevel - the level that is scrolling
	 * @return the new area that is on screen
	 */
	public static Rectangle2D.Double slideWorldToImage(Sprite img, Level thisLevel) {
		
		double characterFractionOfWindow = 0;  // Bigger = panning happens closer to the edge of window. 1 = right at edge, 0 = panning happens always
		double panningLag = 1; // Bigger = follow char more slowly. 1 = immediately pan
		
		Rectangle2D.Double visibleSpace = thisLevel.screenRect;
		// Area of the level that we can see
		
		Rectangle2D.Double characterSpace = new Rectangle2D.Double(visibleSpace.getX()+visibleSpace.getWidth()*(1-characterFractionOfWindow)*0.5,visibleSpace.getY()+visibleSpace.getHeight()*(1-characterFractionOfWindow)*0.5,visibleSpace.getWidth()*characterFractionOfWindow,visibleSpace.getHeight()*characterFractionOfWindow);
		// Area of the window that the character can move freely in
		
		Point2D.Double center = new Point2D.Double(img.getCenterX(), img.getCenterY());
		
		if (!characterSpace.contains(center)) {
			double newX = visibleSpace.getX();
			double newY = visibleSpace.getY();

			if (center.getX() < characterSpace.getX()) {
				newX -= (characterSpace.getX() - center.getX()) / panningLag;
			} else if (center.getX() > characterSpace.getX() + characterSpace.getWidth()) {
				newX += (center.getX() - (characterSpace.getX() + characterSpace.getWidth())) / panningLag;
			}

			if (center.getY() < characterSpace.getY()) {
				newY -= (characterSpace.getY() - center.getY()) / panningLag;
			} else if (center.getY() > characterSpace.getY() + characterSpace.getHeight()) {
				newY += (center.getY() - characterSpace.getY() - characterSpace.getHeight()) / panningLag;
			}
		
			// These lines cause scrolling to not work for some reason
			//newX = Math.max(newX,0);
			//newY = Math.max(newY,0);
			//newX = Math.min(newX,thisLevel.getWidth()-visibleSpace.getWidth());
			//newY = Math.min(newY,thisLevel.getHeight()-visibleSpace.getHeight());

			visibleSpace.setRect(newX,newY,visibleSpace.getWidth(),visibleSpace.getHeight());
			characterSpace.setRect(visibleSpace.getX()+visibleSpace.getWidth()*(1-characterFractionOfWindow)*0.5,visibleSpace.getY()+visibleSpace.getHeight()*(1-characterFractionOfWindow)*0.5,visibleSpace.getWidth()*characterFractionOfWindow,visibleSpace.getHeight()*characterFractionOfWindow);
		}
		return visibleSpace;
		
	}

}
