package core;

import java.awt.Graphics2D;

public interface RenderObject extends Comparable<RenderObject>{
	public void draw(Graphics2D render);
	public Bounds3D getBounds(); // Because everything is a rectangle
}
