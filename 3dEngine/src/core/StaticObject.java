package core;

import java.awt.Graphics2D;


public class StaticObject implements GameObject {

	//TODO Write more constructors
	//TODO Create rendering methods
	public Bounds3D body;
	
	public StaticObject() {
		this(new Bounds3D(0,0,0,0,0,0));
	}
	
	public StaticObject(Bounds3D poly) {
		this.body = poly;
	}

	public void draw(Graphics2D render) {

	}

	public Bounds3D getBounds() {
		return body;
	}

	public int compareTo(GameObject object) {
		return 0;
	}

	public void tick() {
		
	}

}
