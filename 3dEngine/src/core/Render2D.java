package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Render2D implements RenderObject {
	
	public void draw(Graphics2D render) {
		
	}

	public Bounds3D getBounds() {
		return null;
	}

	public int compareTo(RenderObject object) {
		return object instanceof Render2D ? 0 : -1;
	}

}
