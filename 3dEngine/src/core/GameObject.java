package core;

import java.awt.Graphics2D;

public interface GameObject extends Comparable<GameObject>{
	public void draw(Graphics2D render);
	public Bounds3D getBounds(); // Because everything is a rectangle
	public void tick();
}
