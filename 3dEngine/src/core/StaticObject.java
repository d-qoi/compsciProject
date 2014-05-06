package core;

import java.awt.Graphics2D;

import engine3D.RenderPolygon;

public class StaticObject implements GameObject {

	//TODO Write more constructors
	//TODO Create rendering methods
	public RenderPolygon poly;
	
	public StaticObject() {
		this.poly = new RenderPolygon();
	}
	
	public StaticObject(RenderPolygon poly) {
		this.poly = poly;
	}

	public void draw(Graphics2D render) {

	}

	public Bounds3D getBounds() {
		return null;
	}

	public int compareTo(GameObject object) {
		return 0;
	}

	public void tick() {
		
	}

}
